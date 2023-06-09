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
import javax.servlet.http.HttpSession;

import com.KoreaIT.java.jam.config.Config;
import com.KoreaIT.java.jam.exception.SQLErrorException;
import com.KoreaIT.java.jam.util.DBUtil;
import com.KoreaIT.java.jam.util.SecSql;

@WebServlet("/article/modify")
public class ArticleModifyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		if (session.getAttribute("loginedMemberId") == null) {
			response.getWriter()
			.append(String.format("<script>alert('로그인 후 이용 가능합니다.'); location.replace('list');</script>"));
			return; // return 없으면 경고창도 안 뜸
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

			int id = Integer.parseInt(request.getParameter("id"));

			SecSql sql = SecSql.from("SELECT *");
			sql.append("FROM article");
			sql.append("WHERE id = ? ;", id);

			Map<String, Object> articleRow = DBUtil.selectRow(conn, sql);

			response.getWriter().append(articleRow.toString());

			request.setAttribute("articleRow", articleRow);
			
			if ((int)session.getAttribute("loginedMemberId") != (int)articleRow.get("memberId")) {
				response.getWriter()
				.append(String.format("<script>alert('해당 게시글에 대한 권한이 없습니다.'); location.replace('list');</script>"));
				return;
			}
			
			request.getRequestDispatcher("/jsp/article/modify.jsp").forward(request, response);

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

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}