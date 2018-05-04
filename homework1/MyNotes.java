package cs320stu10.homework1;

import org.omg.CORBA.Current;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by dkrystall on 10/23/15.
 */
@WebServlet(name = "MyNotes", urlPatterns = {"/Homework2/MyNotes"})
public class MyNotes extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        //Makes sure a session is made in case someone accesses link directly
        if(request.getSession().getAttribute("HW1User") != null) {
            HttpSession session = request.getSession(true);
            HW1User CurrentUser = (HW1User) session.getAttribute("HW1User");
            if(CurrentUser.getName() != null) {
                out.println("<h1><span id=\"name\">" + CurrentUser.getName() + "</span></h1>");
            }
        } else{
            out.println("<h1>Please <a href=\"http://cs3.calstatela.edu:8080/cs320stu10/Homework1/Register\">login</a> first</h1>");
        }
    }
}
