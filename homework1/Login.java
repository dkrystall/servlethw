package cs320stu10.homework1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by dkrystall on 10/23/15.
 */
@WebServlet(name = "Login", urlPatterns = {"/Homework1/Login"})
public class Login extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean login = false;
        try{
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            ArrayList<HW1User> hw1Users = (ArrayList<HW1User>) getServletContext().getAttribute("HW1Users");
            for (HW1User u : hw1Users){
                if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                    login = true;
                    HttpSession CurrentUser = request.getSession();
                    CurrentUser.setAttribute("HW1User", u);
                    response.sendRedirect("/cs320stu10/Homework2/MyNotes");

                }
            }
        }catch(Exception u){}
        finally{
            if (login == false){
                getServletContext().setAttribute("login_error","Invalid Username and/or Password");
                response.sendRedirect("Login");
                return;
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String error = "";
        if(getServletContext().getAttribute("login_error") != null){
            error = (String) getServletContext().getAttribute("login_error");
        }

        out.println("\n" +
                "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "\t<meta charset=\"UTF-8\">\n" +
                "\t<title></title>\n" +
                "\t<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"container\">\n" +
                "\t<div class=\"jumbotron\">\n" +
                "\t  <h1>Jot! <small>Cloud Based Notes</small></h1>\t\t  \n" +
                "\t</div>\n" +
                "\t\t<!-- \n" +
                "\t\t\tThe following form was taken from http://getbootstrap.com/examples/signin/\n" +
                "\t\t\tYou do not need to include this comment in your Servlet \n" +
                "\t\t-->\n" +
                "\t\t<div class=\"row\">\n" +
                "\t\t\t<div class=\"col-sm-offset-3 col-sm-6\">\n" +
                "\t\t\t\t<p class=\"text-danger text-center\">"+error+"</p>\n" +
                "\t\t\t    <form class=\"form-signin\" method=\"post\">\n" +
                "\t\t\t      <h2 class=\"form-signin-heading\">Please Sign In</h2>\n" +
                "\t\t\t      <label for=\"email\" class=\"sr-only\">Email address</label>\n" +
                "\t\t\t      <input type=\"email\" name=\"email\" id=\"email\" class=\"form-control\" placeholder=\"Email Address\" />\n" +
                "\t\t\t      <label for=\"password\" class=\"sr-only\">Password</label>\n" +
                "\t\t\t      <input type=\"password\" name=\"password\" id=\"password\" class=\"form-control\" placeholder=\"Password\" />\n" +
                "\t\t\t      <div class=\"checkbox\">\n" +
                "\t\t\t        <label>\n" +
                "\t\t\t          <input type=\"checkbox\" name=\"remember\" value=\"yes\"> Remember Me\n" +
                "\t\t\t        </label>\n" +
                "\t\t\t      </div>\n" +
                "\t\t\t      <button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\">Sign in</button>\n" +
                "\t\t\t    </form>\n" +
                "\t    \t\t</div>\n" +
                "\t    </div>\n" +
                "\n" +
                "    </div>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }
}
