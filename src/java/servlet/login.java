package servlet;

import Models.Token;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    Token tkn = new Token();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equals("abc") && password.equals("123")) {

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("JSESSIONID")) {

                        // set session id
                        tkn.setSesssion(cookie.getValue());

                    }
                }
            }
            // set session cookie
            Cookie sessionCookie = new Cookie("sessionID", tkn.getSesssion());
            response.addCookie(sessionCookie);

            // set cookie to username
            Cookie usernameCookie = new Cookie("username", username);
            response.addCookie(usernameCookie);
     
                saveSession();
                response.sendRedirect("welcome.jsp");

        } else {
            response.sendRedirect("login.jsp");
        }

    }

    private void saveSession() {
        // create csrf token
        tkn.setCsrf();
        //set session id , csrf as a hashmap
        tkn.setSessionMap();
    }
}
