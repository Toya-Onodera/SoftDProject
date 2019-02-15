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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author O.Toya
 */
public class RoomMemoDataServlet extends HttpServlet {/**
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
            String room_id = "";
            
            if (request.getParameter("room_id") != "") {
                room_id = request.getParameter("room_id");
            }
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/softd?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "", "");
            
            String title_sql = "select `title` from `room` where id = ?";
            PreparedStatement title_stmt = conn.prepareStatement(title_sql);
            title_stmt.setString(1, room_id);
            
            ResultSet title_rs = title_stmt.executeQuery();
            String title = (title_rs.next()) ? title_rs.getString("title") : "タイトル取得エラー";
            
            // メモ一覧を取得
            String memo_sql = "select * from memo left outer join memo_for_text on memo.id = memo_for_text.memo_id left outer join memo_for_media on memo.id = memo_for_media.memo_id where `room_id` = ? order by memo.id asc;";
            PreparedStatement memo_stmt = conn.prepareStatement(memo_sql);
            memo_stmt.setString(1, room_id);
            
            ResultSet memo_rs = memo_stmt.executeQuery();
            
            String memoJsonString = "{\"title\": \"" + title + "\",";
            memoJsonString +=  "\"memo\": [";
            
            while (memo_rs.next()) {
                memoJsonString += "{";
                memoJsonString += "\"id\": \"" + memo_rs.getString("id") + "\",";
                memoJsonString += "\"account_id\": \"" + memo_rs.getString("account_id") + "\",";
                memoJsonString += "\"created_at\": \"" + memo_rs.getString("created_at") + "\",";
                memoJsonString += "\"updated_at\": \"" + memo_rs.getString("updated_at") + "\",";
                memoJsonString += "\"text\": \"" + memo_rs.getString("text") + "\",";
                memoJsonString += "\"media\": \"" + memo_rs.getString("media");
                memoJsonString += "\"}";
                
                if (!memo_rs.isLast()) {
                    memoJsonString += ",";
                }
            }
            
            memoJsonString += "]}";
            
            response.setContentType("text/html; charset=utf8");
            String responseText = memoJsonString;
            PrintWriter out = response.getWriter();
            out.print(responseText);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
