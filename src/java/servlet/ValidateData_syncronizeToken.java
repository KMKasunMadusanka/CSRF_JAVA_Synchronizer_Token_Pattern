package servlet;
import Models.Token;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ValidateData_syncronizeToken", urlPatterns = {"/ValidateData_syncronizeToken"})
public class ValidateData_syncronizeToken extends HttpServlet {

    Token tkn = new Token();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get current session id
        String currentSID = tkn.getSesssion();
        
        HttpSession session = request.getSession();
        
        tkn.getSessionMap().entrySet().forEach((m) -> {
            if (m.getKey().equals(currentSID)) {

                if (request.getParameter("csrf").equals(m.getValue())) { 
                    session.setAttribute("result_value", "Your response has been ensured with CSRF synchronize token pattern!");

                } else {
                    session.setAttribute("result_value", "Your responce is recoded but not ensured with CSRF");
                }
            }
            System.out.println(m.getKey() + " - " + m.getValue());
        });

        response.sendRedirect("result.jsp");

    }
}
