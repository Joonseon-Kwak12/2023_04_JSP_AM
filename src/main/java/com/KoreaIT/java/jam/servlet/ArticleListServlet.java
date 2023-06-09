package com.KoreaIT.java.jam.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
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

@WebServlet("/article/list")
public class ArticleListServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");

		//DB 연결
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
			
			int page = 1;
			
			if(request.getParameter("page") != null && request.getParameter("page").length() != 0) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			
			int itemsInAPage = 10;
			
			int limitFrom = (page - 1) * itemsInAPage;
			
			SecSql sql = SecSql.from("SELECT COUNT(*) AS cnt");
			sql.append("FROM article");
			
			int totalCnt = DBUtil.selectRowIntValue(conn, sql);
			int totalPage = (int) Math.ceil((double)totalCnt / itemsInAPage);
			
			sql = SecSql.from("SELECT a.*, m.name AS writer");
			sql.append("FROM article AS a");
			sql.append("INNER JOIN `member` AS m");
			sql.append("ON a.memberId =  m.id");
			sql.append("ORDER BY id DESC");
			sql.append("LIMIT ?, ?;", limitFrom, itemsInAPage);
			
			List<Map<String, Object>> articleRows = DBUtil.selectRows(conn, sql);
			
			
			response.getWriter().append(articleRows.toString());
 
			request.setAttribute("page", page);
			request.setAttribute("totalPage", totalPage);
			request.setAttribute("articleRows", articleRows);
			
			// topbar.jspf 필요 부분 시작
			HttpSession session = request.getSession();

			boolean isLogined = false;
			int loginedMemberId = -1;
			Map<String, Object> loginedMemberRow = null;

			if (session.getAttribute("loginedMemberId") != null) {
				isLogined = true;
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
				loginedMemberRow = (Map<String, Object>) session.getAttribute("loginedMemberRow");
			}

			request.setAttribute("isLogined", isLogined);
			request.setAttribute("loginedMemberId", loginedMemberId);
			request.setAttribute("loginedMemberRow", loginedMemberRow);
			// topbar.jspf 필요 부분 끝
			
			request.getRequestDispatcher("/jsp/article/list.jsp").forward(request, response);
			
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
}