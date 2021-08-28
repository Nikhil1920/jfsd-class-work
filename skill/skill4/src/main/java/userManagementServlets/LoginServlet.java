package userManagementServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + " " + password);
		try {

			LoginDao loginDao = new LoginDao();
			if (loginDao.check(username, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("useremail", username);
				response.sendRedirect("welcome.jsp");
			} else {
				out.print("Sorry, username or password error!");
				RequestDispatcher rd = request.getRequestDispatcher("/index.html");
				rd.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
