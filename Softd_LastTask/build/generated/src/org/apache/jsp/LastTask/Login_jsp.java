package org.apache.jsp.LastTask;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>ログイン - MemoShare</title>\n");
      out.write("        \n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=M+PLUS+1p\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.7.0/css/all.css\" integrity=\"sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ\" crossorigin=\"anonymous\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./stylesheets/common.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"./stylesheets/login.css\">\n");
      out.write("        \n");
      out.write("        <script src=\"./javascripts/login.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"warpper\">\n");
      out.write("            <div class=\"form-wrapper\">\n");
      out.write("                <h1>ログイン</h1>\n");
      out.write("                \n");
      out.write("                <form id=\"login-form\" method=\"POST\" action=\"#\" accept-charset=\"UTF-8\">\n");
      out.write("                    <div class=\"form-item\">\n");
      out.write("                        <label for=\"email\"></label>\n");
      out.write("                        <input id=\"email\" type=\"email\" name=\"email\" required=\"required\" placeholder=\"メールアドレス\"></input>\n");
      out.write("                    </div>\n");
      out.write("                \n");
      out.write("                    <div class=\"form-item\">\n");
      out.write("                        <label for=\"password\"></label>\n");
      out.write("                        <input id=\"password\" type=\"password\" name=\"password\" required=\"required\" placeholder=\"パスワード\"></input>\n");
      out.write("                    </div>\n");
      out.write("                \n");
      out.write("                    <div class=\"button-panel\">\n");
      out.write("                        <input type=\"submit\" class=\"button\" title=\"ログインする\" value=\"ログインする\"></input>\n");
      out.write("                    </div>\n");
      out.write("                </form>\n");
      out.write("                \n");
      out.write("                <div class=\"form-footer\">\n");
      out.write("                    <p><a href=\"./Register.jsp\">アカウントを作成する</a></p>\n");
      out.write("                    <p><a href=\"#\">パスワードを忘れてしまった場合はこちら</a></p>\n");
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
