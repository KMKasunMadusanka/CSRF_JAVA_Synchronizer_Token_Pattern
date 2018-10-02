package servlet;

import Models.Token;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CsrfIssuer", urlPatterns = {"/CsrfIssuer"})
public class CsrfIssuer extends HttpServlet {

    Token tkn = new Token();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //get current session id
        String currentSID = tkn.getSesssion();
        
        tkn.getSessionMap().entrySet().forEach((m) -> {
            if(m.getKey().equals(currentSID)){
               response.setContentType("text/json;charset=UTF-8");
                try {
                    PrintWriter writer = response.getWriter();
                    writer.append(m.getValue());
                } catch (IOException ex) {
                    Logger.getLogger(CsrfIssuer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println(m.getKey()+ " - " + m.getValue());
        });
       
    }

   
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
