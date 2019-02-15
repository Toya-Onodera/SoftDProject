/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LastTask;

import static LastTask.LoginServlet.hash;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
            
/**
 *
 * @author O.Toya
 */
public class MyPageDataLoadServlet extends HttpServlet {
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
            String user_id = request.getParameter("id");
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/softd?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "", "");
            
            // メモ一覧を取得
            String memo_sql = "select * from memo left outer join memo_for_text on memo.id = memo_for_text.memo_id left outer join memo_for_media on memo.id = memo_for_media.memo_id where `account_id` = ? order by memo.id asc;";
            PreparedStatement memo_stmt = conn.prepareStatement(memo_sql);
            memo_stmt.setString(1, user_id);
            
            ResultSet memo_rs = memo_stmt.executeQuery();
            
            String memoJsonString = "\"memo\": [";
            
            while (memo_rs.next()) {
                memoJsonString += "{";
                memoJsonString += "\"id\": \"" + memo_rs.getString("id") + "\",";
                memoJsonString += "\"room_id\": \"" + memo_rs.getString("room_id") + "\",";
                memoJsonString += "\"created_at\": \"" + memo_rs.getString("created_at") + "\",";
                memoJsonString += "\"updated_at\": \"" + memo_rs.getString("updated_at") + "\",";
                memoJsonString += "\"text\": \"" + memo_rs.getString("text") + "\",";
                memoJsonString += "\"media\": \"" + memo_rs.getString("media");
                memoJsonString += "\"}";
                
                if (!memo_rs.isLast()) {
                    memoJsonString += ",";
                }
            }
            
            memoJsonString += "],";
            
            // 部屋一覧を取得
            String room_sql = "select id, title, account_id, (select account.name from account where account.id = room.account_id) as name from room where account_id = ? or is_participation = 1";
            PreparedStatement room_stmt = conn.prepareStatement(room_sql);
            room_stmt.setString(1, user_id);
            
            ResultSet room_rs = room_stmt.executeQuery();
            
            String roomJsonString = "\"room\": [";
            
            while (room_rs.next()) {
                roomJsonString += "{";
                roomJsonString += "\"id\": \"" + room_rs.getString("id") + "\",";
                roomJsonString += "\"account_id\": \"" + room_rs.getString("account_id") + "\",";
                roomJsonString += "\"title\": \"" + room_rs.getString("title") + "\",";
                roomJsonString += "\"name\": \"" + room_rs.getString("name");
                roomJsonString += "\"}";
                
                if (!room_rs.isLast()) {
                    roomJsonString += ",";
                }
            }
            
            roomJsonString += "]";
            
            response.setContentType("text/html; charset=utf8");
            String responseText = "{" + memoJsonString + roomJsonString +"}";
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
            String myId = "";
            String roomName = "";
            String isParticipation = "";
            
            if (request.getParameter("my_id") != null) {
                myId = request.getParameter("my_id");
            }
            
            if (request.getParameter("room_name") != null) {
                roomName = request.getParameter("room_name");
            }
            
            if (request.getParameter("is_participation") != null) {
                isParticipation = request.getParameter("is_participation");
            }
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/softd?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "", "");
            
            String sql = "insert into `room`(`account_id`, `title`, `is_participation`) values(?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, myId);
            stmt.setString(2, roomName);
            stmt.setString(3, isParticipation);
            
            int count = stmt.executeUpdate();
            System.out.println(count);
            String responseText = (count > 0) ? "success" : "failure";
            
            response.setContentType("text/html; charset=utf8");
            PrintWriter out = response.getWriter();
            out.print(responseText);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
