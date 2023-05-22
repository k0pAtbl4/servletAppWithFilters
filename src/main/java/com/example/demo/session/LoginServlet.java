package com.example.demo.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HashMap<String, String> userLoginData = new HashMap<>();

        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        userLoginData.put(user, pwd);

        // Это значение наших параметров
        String userID = "admin";
        String password = "password";

        if (userLoginData.containsKey(userID)) {
            if (userLoginData.get(userID).equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", "user");
                //setting session to expiry in 30 mins
                session.setMaxInactiveInterval(30 * 60);
                Cookie userName = new Cookie("user", user);
                userName.setMaxAge(30 * 60);
                response.addCookie(userName);
                PrintWriter out = response.getWriter();
                out.println("Welcome back to the team, " + user + "!");
            } else {
                PrintWriter out = response.getWriter();
                out.println("Either user name or password is wrong!");
            }
        }
    }
}