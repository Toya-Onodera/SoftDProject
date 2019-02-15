package LastTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static LastTask.LoginServlet.hash;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author O.Toya
 */
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String email = "";
            String password = "";
            String username = "";

            if (request.getParameter("email") != null) {
                email = request.getParameter("email");
            }

            // パスワードなのでハッシュ化を行う
            // SQL の SHA256 でもいいけどセッションの保持を考えるとこっちのほうが楽
            if (request.getParameter("password") != null) {
                password = hash(request.getParameter("password"));
            }
            
            if (request.getParameter("username") != null) {
                username = request.getParameter("username");
            }

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //String url = "jdbc:mysql://localhost:3306/softd4?zeroDateTimeBehavior=convertToNull";
            //String url = "jdbc:mysql://localhost/softd4?characterEncoding=UTF-8";
            String url = "jdbc:mysql://localhost:3306/softd?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8";
            //Connection conn = DriverManager.getConnection(url, "softd", "softd");
            Connection conn = DriverManager.getConnection(url, "root", "");
            
            String sql = "insert into account(name, password, email) values(?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            
            System.out.println(sql);

            int count = stmt.executeUpdate();
            String responseURL = (count > 0) ? "/Softd_LastTask/LastTask/MyPage.jsp" : "failure";

            // ログイン情報を保持する
            Map<String, String> map = new HashMap<String, String>();
            map.put("Email", email);
            map.put("Password", password);

            // ログイン情報をセッションに保存.
            HttpSession session = request.getSession(true);
            session.setAttribute("login_user", map);

            String responseText = "{ \"result\": \"" + responseURL + "\"}";
            
            response.setContentType("text/html; charset=utf8");
            PrintWriter out = response.getWriter();
            out.print(responseText);

        } catch (Exception e) {
            System.out.println(e);
            
            String responseText = "{ \"result\": -1 }";
            
            response.setContentType("text/html; charset=utf8");
            PrintWriter out = response.getWriter();
            out.print(responseText);
        }
    }

    protected static String hash(String text) {
        byte[] cipher_byte;
        StringBuilder sb = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes());
            cipher_byte = md.digest();
            sb = new StringBuilder(2 * cipher_byte.length);

            for (byte b : cipher_byte) {
                sb.append(String.format("%02x", b & 0xff));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
