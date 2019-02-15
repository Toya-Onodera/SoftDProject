/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LastTask;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author g031p024
 */
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
    }

    // ログインチェックを行う
    // ログインに成功するとユーザー情報の一部を返す
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            HttpSession session = request.getSession(false);
            Map<String, String> map = (Map<String, String>) session.getAttribute("login_user");
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/softd?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "", "");
            
            String sql = "select * from `account` where `email` = ? and `password` = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, map.get("Email"));
            stmt.setString(2, map.get("Password"));
            
            ResultSet rs = stmt.executeQuery();
            String responseId = (rs.next()) ? rs.getString("id") : "failure";
            String responseText = "{ \"result\": " + responseId + ", \"user_name\": \""+ rs.getString("name") + "\" }";
            
            response.setContentType("text/html; charset=utf8");
            PrintWriter out = response.getWriter();
            out.print(responseText);
            
        } catch (Exception e) {
            // 一応ログを残しておく
            System.out.println(e);
            
            // ログインページに飛ばせるようにデータを返す
            String responseText = "{ \"result\": \"failure\" }";
            
            response.setContentType("text/html; charset=utf8");
            PrintWriter out = response.getWriter();
            out.print(responseText);
        }
    }

    // ログイン画面に使用する
    // セッションを保持して以後のページでもログイン状態が続くようにする
    // ログインに成功したかしないかのテキストを返す
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String email = "";
            String password = "";

            if (request.getParameter("email") != null) {
                email = request.getParameter("email");
            }

            // パスワードなのでハッシュ化を行う
            if (request.getParameter("password") != null) {
                password = hash(request.getParameter("password"));
            }

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/softd?zeroDateTimeBehavior=convertToNull&characterEncoding=UTF-8";
            Connection conn = DriverManager.getConnection(url, "", "");
            
            String sql = "select * from `account` where `email` = ? and `password` = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            String responseURL = (rs.next()) ? "/Softd_LastTask/LastTask/MyPage.jsp" : "failure";

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
