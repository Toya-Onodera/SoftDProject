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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author g031p024
 */
@WebServlet(name = "MemoDataServlet", urlPatterns = {"/MemoData"})
public class MemoDataServlet extends HttpServlet {
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
            String memo_id = request.getParameter("id");
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/softd?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "", "");
            
            // メモ一覧を取得
            String memo_sql = "select * from memo left outer join memo_for_text on memo.id = memo_for_text.memo_id left outer join memo_for_media on memo.id = memo_for_media.memo_id where memo.id = ? order by memo.id asc";
            PreparedStatement memo_stmt = conn.prepareStatement(memo_sql);
            memo_stmt.setString(1, memo_id);
            
            ResultSet memo_rs = memo_stmt.executeQuery();
            
            String memoJsonString = "[";
            
            while (memo_rs.next()) {
                memoJsonString += "{";
                memoJsonString += "\"text\": \"" + memo_rs.getString("text") + "\",";
                memoJsonString += "\"media\": \"" + memo_rs.getString("media");
                memoJsonString += "\"}";
                
                if (!memo_rs.isLast()) {
                    memoJsonString += ",";
                }
            }
            
            memoJsonString += "]";
            
            response.setContentType("text/html; charset=utf8");
            String responseText = memoJsonString;
            PrintWriter out = response.getWriter();
            out.print(responseText);
        } catch (Exception e) {
            System.out.println(e);
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
            String id = "";
            String content = "";
            
            if (request.getParameter("id") != null) {
                id = request.getParameter("id");
            }
            
            if (request.getParameter("content") != null) {
                content = request.getParameter("content");
            }
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/softd?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "", "");
            
            // メディアデータかテキストデータかによって SQL 文が変わる
            String sql = (content.contains("base64")) ? "update `memo_for_media` set `media` = ? where `memo_id` = ?" : "update `memo_for_text` set `text` = ? where `memo_id` = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, content);
            stmt.setString(2, id);
            
            //content = new String(content.getBytes("iso-8859-1"), "UTF-8");
            //id = new String(id.getBytes("iso-8859-1"), "UTF-8");
            
            int count = stmt.executeUpdate();
            String responseText = (count > 0) ? "success" : "failure";
            
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(responseText);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
