/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LastTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.dbcp.dbcp2.DelegatingPreparedStatement;

/**
 *
 * @author g031p024
 */
public class MemoDataChangeServlet extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String memoId = "";
            String responseText = "failure";
            
            if (request.getParameter("memo_id") != null) {
                memoId = request.getParameter("memo_id");
            }
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/softd?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "", "");
            
            String memo_for_text_sql = "delete from memo_for_text WHERE memo_id= ?";
            PreparedStatement memo_for_text_stmt = conn.prepareStatement(memo_for_text_sql);
            memo_for_text_stmt.setString(1, memoId);

            int memo_for_count = memo_for_text_stmt.executeUpdate();
            System.out.println(memo_for_count);

            if (memo_for_count == 0) {
                String memo_for_media_sql = "delete from memo_for_media WHERE memo_id= ?";
                PreparedStatement memo_for_media_stmt = conn.prepareStatement(memo_for_media_sql);
                memo_for_media_stmt.setString(1, memoId);

                memo_for_count = memo_for_text_stmt.executeUpdate();
            }
            
            if (memo_for_count > 0) {
                String memo_sql = "delete from memo WHERE id= ?";
                PreparedStatement memo_stmt = conn.prepareStatement(memo_sql);
                memo_stmt.setString(1, memoId);
            
                int memo_count = memo_stmt.executeUpdate();
                responseText = (memo_count > 0) ? "success" : "failure";
            }
            
            response.setContentType("text/html; charset=utf8");
            PrintWriter out = response.getWriter();
            out.print(responseText);
        } catch (Exception e) {
            System.out.println(e);
            
            String responseText = "failure";
            
            response.setContentType("text/html; charset=utf8");
            PrintWriter out = response.getWriter();
            out.print(responseText);
        }
    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String roomId = "";
            String accountId = "";
            String content = "";
            String responseText = "";
            
            if (request.getParameter("room_id") != null) {
                roomId = request.getParameter("room_id");
            }
            
            if (request.getParameter("account_id") != null) {
                accountId = request.getParameter("account_id");
            }
            
            if (request.getParameter("content") != null) {
                content = request.getParameter("content");
            }
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/softd?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "", "");
            
            String memo_sql = "insert into memo(account_id, room_id, created_at) values(?, ?, now())";
            PreparedStatement memo_stmt = conn.prepareStatement(memo_sql);
            memo_stmt.setString(1, accountId);
            memo_stmt.setString(2, roomId);
            int memo_stmt_count = memo_stmt.executeUpdate();
            
            if (memo_stmt_count > 0) {
                // auto_increment の値を取得する (memo_idになる部分)
                ResultSet rs = memo_stmt.executeQuery("SELECT LAST_INSERT_ID()");

                if (rs.next()) {
                    int memoId = rs.getInt(1);
                    System.out.println(memoId);

                    String memo_for_text_sql = "insert into memo_for_text(memo_id, text) values(?, ?)";
                    PreparedStatement memo_for_text_stmt = conn.prepareStatement(memo_for_text_sql);
                    memo_for_text_stmt.setInt(1, memoId);
                    memo_for_text_stmt.setString(2, content);

                    int memo_for_text_count = memo_for_text_stmt.executeUpdate();
                    responseText = (memo_for_text_count > 0) ? "success" : "failure";
                }
            }            
            
            response.setContentType("text/html; charset=utf8");
            PrintWriter out = response.getWriter();
            out.print(responseText);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

