package cs320stu10.homework2.servlets;

import cs320stu10.homework1.HW1User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by davidkrystall on 12/9/15.
 */
@WebServlet(name = "NoteDetails", urlPatterns = {"/Homework2/NoteDetails"})
public class NoteDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        //Makes sure a session is made in case someone accesses link directly
        if(request.getSession().getAttribute("HW1User") != null) {
            HttpSession session = request.getSession(true);
            HW1User CurrentUser = (HW1User) session.getAttribute("HW1User");
            System.out.print(CurrentUser.getName());
            if(CurrentUser.getName() != null) {
                request.setAttribute("CurrentUser", CurrentUser.getName());
                request.getRequestDispatcher("/WEB-INF/Homework2/NoteDetails.jsp").forward(request,response);
            }
        } else{
            response.sendRedirect("../Homework1/Login");
        }

    }

}
