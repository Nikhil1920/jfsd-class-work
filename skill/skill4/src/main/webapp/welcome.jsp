<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@ page import="dao.LoginDao" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="ISO-8859-1">
            <title>Home page</title>
        </head>

        <body>

            <% response.setHeader("Cache-Control", "no-store, max-age=0" ); if(session.getAttribute("username")==null)
                { response.sendRedirect("index.html"); } %>
                <h1>
                    Welcome
                    <% if(session.getAttribute("username") !=null) { String
                    	username=session.getAttribute("username").toString(); LoginDao logindao=new LoginDao();
                        out.print(logindao.getUserRole(username)); } %>
                </h1>
                <br>
                <h2>You have logged in successfully</h2>
                <br>
                <br>
                <a href="LogoutServlet">Logout</a>
        </body>

        </html>