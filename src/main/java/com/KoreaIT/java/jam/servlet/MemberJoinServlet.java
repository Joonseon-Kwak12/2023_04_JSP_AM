package com.KoreaIT.java.jam.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/join")
public class MemberJoinServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		if (request.getSession().getAttribute("loginedMemberId") != null) {
			response.getWriter()
			.append(String.format("<script>alert('로그아웃 후 가능합니다.'); location.replace('../home/main');</script>"));
			return; // return 없으면 경고창도 안 뜸
		}
		
		request.getRequestDispatcher("/jsp/member/join.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
