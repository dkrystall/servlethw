package cs320stu10.homework2.servlets;

import cs320stu10.homework1.HW1User;
import cs320stu10.homework2.model.Note;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidkrystall on 12/9/15.
 */
@WebServlet(name = "MyNotes", urlPatterns = {"/Homework2/MyNotes"})
public class MyNotes extends HttpServlet {

    public void init() throws ServletException{
        List<Note> myNotes = new ArrayList<Note>();
        myNotes.add(new Note("this note is the cool one","Note One",0));
        myNotes.add(new Note("This is the second note in the list", "Note Two", 1));
        getServletContext().setAttribute("myNotes", myNotes);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        //Makes sure a session is made in case someone accesses link directly
        if(request.getSession().getAttribute("HW1User") != null) {
            HttpSession session = request.getSession(true);
            HW1User CurrentUser = (HW1User) session.getAttribute("HW1User");
            if(CurrentUser.getName() != null) {
                request.setAttribute("CurrentUser", CurrentUser.getName());
                request.getRequestDispatcher("/WEB-INF/Homework2/MyNotes.jsp").forward(request,response);
            }
        } else{
            response.sendRedirect("../Homework1/Login");
        }


    }
}
