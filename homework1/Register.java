package cs320stu10.homework1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(
        name = "Register",
        urlPatterns = {
                "/Homework1/Register"
        }
)
public class Register extends HttpServlet {
    public void init() throws ServletException {
        ArrayList<HW1User> users = new ArrayList<HW1User>();
        //prepop the list
        users.add(new HW1User("John Doe", "John@doe.com", "1!"));
        users.add(new HW1User("Mary Doe", "mary@doe.com", "2@"));
        users.add(new HW1User("Joe Boxer", "John@doe.com", "3#"));
        String empty = "";
        //add array of users to global scope
        this.getServletContext().setAttribute("HW1Users", users);

        this.getServletContext().setAttribute("password1", empty);
        this.getServletContext().setAttribute("password2", empty);
        this.getServletContext().setAttribute("name", empty);
        this.getServletContext().setAttribute("email", empty);
        this.getServletContext().setAttribute("password1_error", empty);
        this.getServletContext().setAttribute("password2_error", empty);
        this.getServletContext().setAttribute("name_error", empty);
        this.getServletContext().setAttribute("email_error", empty);

    }

/*
    String name_error = "Error: You must specify your full name.";
    String password1_error = "Error: Your password must contain at least one number and one special character.";
    String password2_error = "Error: Your passwords don't match.";
    String email_error = "Error: You must privde a valid e-mail address.";
*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String password1 = (String) getServletContext().getAttribute("password1");
        String password2 = (String) getServletContext().getAttribute("password2");
        String name = (String) getServletContext().getAttribute("name");
        String email = (String) getServletContext().getAttribute("email");
        String name_error = (String) getServletContext().getAttribute("name_error");
        String password1_error = (String) getServletContext().getAttribute("password1_error");
        String password2_error = (String) getServletContext().getAttribute("password2_error");
        String email_error = (String) getServletContext().getAttribute("email_error");

        out.println("<html>");
        out.println("<head>");
        out.println("    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css\">");
        out.println("    <title>Registration Page</title>");
        out.println("</head>");
        out.println("<body class = \"container\">");
        /* Show logged in users
        /*
        out.println("<h1>Existing Users</h1>");
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>Name</th><th>eEmail</th><th>Passwords</th>");
        out.println("</tr>");

        ArrayList<HW1User> users = (ArrayList<HW1User>) getServletContext().getAttribute("HW1Users");
        for (HW1User user : users){
            out.println("<tr>");
            out.println("<td>"+user.getName()+"</td>");
            out.println("<td>"+user.getEmail()+"</td>");
        }
        out.println("</tr>");
        out.println("</table>");
        */
        out.println("<div class=\"jumbotron\">");
        out.println("<h1>Welcome to Jot!</h1>");
        out.println("<p>Jot! is a cloud-based note taking app that allows you to create, store, edit, and share notes that you create directly in your browser!</p>");
        out.println("<p>To begin, register below.</p>");
        out.println("</div>");
        out.println("<div class=\"panel panel-primary\">");
        out.println("<div class=\"panel-heading\">");
        out.println("<h3 class=\"panel-title\">New User Registration</h3>");
        out.println("</div>");
        out.println("<div class=\"panel-body\">");
        out.println("<form class = \"\" action=\"Register\" method=\"post\">");
        out.println("    <div class=\"form-group\">");
        out.println("        <label for=\"name\">Full Name</label>");
        out.println("        <input type=\"text\" class=\"form-control\" placeholder=\"Full Name\" id=\"name\" name=\"name\" value=\""+  name + "\"/>");
        out.println("        <div>"+ name_error+"</div>");
        out.println("    </div>");
        out.println("    <div class=\"form-group\">");
        out.println("        <label for=\"email\">E-Mail</label>");
        out.println("        <input type=\"text\" class=\"form-control\" id=\"email\" placeholder=\"e-mail\" name = \"email\" value=\""+ email + "\">");
        out.println("        <div>"+ email_error+"</div>");
        out.println("    </div>");
        out.println("    <div class=\"form-group\"");
        out.println("        <label for=\"password1\">Password</label>");
        out.println("        <input type=\"password\" class=\"form-control\" id=\"password1\" name = \"password1\" placeholder=\"Enter your password\" value=\""+ password1+"\">");
        out.println("        <div>"+ password1_error+"</div>");
        out.println("    </div>");
        out.println("    <div class=\"form-group\">");
        out.println("        <label for=\"password2\">Re-Enter Password</label>");
        out.println("        <input type=\"password\" class=\"form-control\" id=\"password2\" name = \"password2\" placeholder=\"Re-Enter your password\" value=\""+password2+"\">");
        out.println("        <div>"+ password2_error+"</div>");
        out.println("    </div>");
        out.println("    <input type=\"submit\" class=\"btn btn-default\" value = \"Post\"></input>");
        out.println("    </form>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String name_error = "";
        //String password_error = "";
        String email_error = "";

        boolean hasErrors = false;

        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        if(!name.matches("^[a-zA-Z\\s]+") || !name.contains(" ")){
            name_error = "Error: You must specify your full name.";
            getServletContext().setAttribute("name_error", name_error);
            name = "";
            hasErrors = true;
        } else{
            getServletContext().setAttribute("name", name);
            getServletContext().setAttribute("name_error", "");
        }

        //regex from http://stackoverflow.com/questions/8204680/java-regex-email by User: Maksim
        if(!email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") ){
            email_error = "Error: You must provide a valid e-mail address.";
            getServletContext().setAttribute("email_error", email_error);
            email = "";
            hasErrors = true;
        } else {
            getServletContext().setAttribute("email", email);
            getServletContext().setAttribute("email_error", "");
        }
        if(!password1.matches("^(?=(.*\\d){1})(?=(.*[!@#$%^&*()]){1})[0-9a-zA-Z!@#$%^&*()]{0,}") || !password1.matches(password2)){
            //password_error= "Your passwords is invalid";
            getServletContext().setAttribute("password1_error", "Error: Your password must contain at least one number and one special character.");
            getServletContext().setAttribute("password2_error", "Error: Your passwords don't match.");
            password1 = "";
            password2 = "";
            hasErrors = true;
        } else{
            getServletContext().setAttribute("password1", password1);
            getServletContext().setAttribute("password2", password2);
            getServletContext().setAttribute("password1_error", "");
            getServletContext().setAttribute("password2_error", "");
        }
        if (hasErrors == false) {
            ArrayList<HW1User> users = (ArrayList<HW1User>) getServletContext().getAttribute("HW1Users");
            HW1User newUser = new HW1User(name,email,password1);
            users.add(newUser);
            getServletContext().setAttribute("name", "");
            getServletContext().setAttribute("email", "");
            getServletContext().setAttribute("password1", "");
            getServletContext().setAttribute("password2", "");
            response.sendRedirect("Login");
        }
        if(hasErrors) {
            response.sendRedirect("Register");
        }
    }
}