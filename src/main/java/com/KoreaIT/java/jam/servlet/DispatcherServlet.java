package com.KoreaIT.java.jam.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.KoreaIT.java.jam.config.Config;
import com.KoreaIT.java.jam.exception.SQLErrorException;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String requestURI = request.getRequestURI();
		
		System.out.println(requestURI);
		
		String[] requestURIBits = requestURI.split("/");
		// ~~/s/article/list
		// [0]/[1]/[2]/[3]
		
		if (requestURIBits.length < 5) {
			response.getWriter().append("올바른 요청이 아닙니다.");
			return;
		}
		
		String controllerName = requestURIBits[3];
		String actionMethodName = requestURIBits[4];
		
		if(controllerName.equals("article")) {
			ArticleController articleController = new ArticleController();
		}

		// DB 연결
		Connection conn = null;

		try {
			Class.forName(Config.getDBDriverClassName());
		} catch (ClassNotFoundException e) {
			System.out.println("예외: 클래스가 없습니다.");
			System.out.println("프로그램을 종료합니다.");
			return;
		}

		try {
			conn = DriverManager.getConnection(Config.getDBUrl(), Config.getDBUser(), Config.getDBPassword());

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (SQLErrorException e) {
			e.getOrigin().printStackTrace();
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}