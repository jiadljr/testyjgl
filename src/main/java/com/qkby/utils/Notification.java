package com.qkby.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getNextTime")
public class Notification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Thread.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ServletContext context = request.getServletContext();
		String apply = (String) context.getAttribute("apply");
		if (apply != null) {
			out.print(apply);

			out.flush();

			out.close();
		}
		context.removeAttribute("apply");
		String accept = (String) context.getAttribute("accept");
		if (accept != null) {
			out.print(accept);

			out.flush();

			out.close();
		}
		context.removeAttribute("accept");
		String check = (String) context.getAttribute("check");
		if (check != null) {
			out.print(check);

			out.flush();

			out.close();
		}
		context.removeAttribute("check");
		String redeploy = (String) context.getAttribute("redeploy");
		if (redeploy != null) {
			out.print(redeploy);

			out.flush();

			out.close();
		}
		context.removeAttribute("redeploy");
		String addUser = (String) context.getAttribute("addUser");
		if (addUser != null) {
			out.print(addUser);

			out.flush();

			out.close();
		}
		context.removeAttribute("addUser");
		String affirm = (String) context.getAttribute("affirm");
		if (affirm != null) {
			out.print(affirm);

			out.flush();

			out.close();
		}
		context.removeAttribute("affirm");
	}
}
