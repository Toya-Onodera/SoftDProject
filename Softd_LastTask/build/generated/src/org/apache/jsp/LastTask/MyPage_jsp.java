package org.apache.jsp.LastTask;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class MyPage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"ja\">\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>マイページ - MemoShare</title>\n");
      out.write("        \n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=M+PLUS+1p\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.7.0/css/all.css\" integrity=\"sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ\" crossorigin=\"anonymous\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./stylesheets/common.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./stylesheets/mypage.css\">\n");
      out.write("\n");
      out.write("        <script src=\"./javascripts/mypage.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"warpper\">\n");
      out.write("            <div class=\"menu\">\n");
      out.write("                <span class=\"username\">\n");
      out.write("                    こんにちは、テストアカウントさん\n");
      out.write("                </span>\n");
      out.write("\n");
      out.write("                <div class=\"create_newroom_button\">\n");
      out.write("                    <span>新規メモ部屋作成</span>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"content\">\n");
      out.write("                <h1>マイページ</h1>\n");
      out.write("\n");
      out.write("                <div class=\"memo_list_views\">\n");
      out.write("                    <div class=\"myselfmemo_list\">\n");
      out.write("                        <!-- メモデータが入る -->\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"room_list\">\n");
      out.write("                        <h3>使用できるメモ部屋</h3>\n");
      out.write("\n");
      out.write("                        <ul>\n");
      out.write("                            <li>\n");
      out.write("                                <span class=\"icon-users\"><i class=\"fas fa-users\"></i></span>\n");
      out.write("                                <span>あなたがリーダーの部屋です</span>\n");
      out.write("                            </li>\n");
      out.write("                            \n");
      out.write("                            <li>\n");
      out.write("                                <span class=\"icon-link\"><i class=\"fas fa-link\"></i></span>\n");
      out.write("                                <span>他のユーザーが作成した部屋です</span>\n");
      out.write("                            </li>\n");
      out.write("                        </ul>\n");
      out.write("\n");
      out.write("                        <div>\n");
      out.write("                            <!-- 部屋データが入る -->\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"create_room_layer\">\n");
      out.write("                <div class=\"create_room_view\">\n");
      out.write("                    <h3><i class=\"fas fa-plus-square\"></i> 新規メモ部屋作成</h3>\n");
      out.write("\n");
      out.write("                    <div>\n");
      out.write("                        <div>\n");
      out.write("                            <span><i class=\"fas fa-angle-right\"></i> 部屋名</span>\n");
      out.write("                            <input id=\"room_name\" type=\"text\" placeholder=\"部屋名を入力してください。\">\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div>\n");
      out.write("                            <span><i class=\"fas fa-angle-right\"></i> 部屋の共有</span>\n");
      out.write("                            <select id=\"is_participation\">\n");
      out.write("                                <option value=\"0\" selected>しない</option>\n");
      out.write("                                <option value=\"1\">する</option>\n");
      out.write("                            </select>\n");
      out.write("                        </div>                        \n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <div class=\"create_room_submit_button\">\n");
      out.write("                        <div class=\"loading\">\n");
      out.write("                            <i class=\"fas fa-spinner fa-spin\"></i>                            \n");
      out.write("                        </div>\n");
      out.write("                        \n");
      out.write("                        <span>登録</span>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
