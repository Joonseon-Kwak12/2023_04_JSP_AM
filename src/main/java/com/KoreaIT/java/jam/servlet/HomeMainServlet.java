package com.KoreaIT.java.jam.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/home/main")
public class HomeMainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		// topbar.jspf 필요 부분 시작
		HttpSession session = request.getSession();
		
//		boolean isLogined = false;
//		int loginedMemberId = -1;
//		Map<String, Object> loginedMemberRow = null;
//		
//		if(session.getAttribute("loginedMemberId") != null) {
//			isLogined = true;
//			loginedMemberId = (int) session.getAttribute("loginedMemberId");
//			loginedMemberRow = (Map<String, Object>) session.getAttribute("loginedMemberRow");
//		}
		
//		if((boolean)session.getAttribute("isLogined") == true) {
//		request.setAttribute("isLogined", (boolean)session.getAttribute("isLogined"));
//		request.setAttribute("loginedMemberId", (int)session.getAttribute("loginedMemberId"));
//		request.setAttribute("loginedMemberName", (String)session.getAttribute("loginedMemberName"));
//		}
//		// topbar.jspf 필요 부분 끝
		
		request.getRequestDispatcher("/jsp/home/main.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}