package com.KoreaIT.java.jam;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/printDan")
public class HomeMainServlet3 extends HttpServlet {
    public HomeMainServlet3() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html; charset=UTF-8");
    	
    	String inputtedDan = request.getParameter("dan");
    	String inputtedLimit = request.getParameter("limit");
    	String inputtedColor = request.getParameter("color");
    	
    	if (inputtedDan == null) {
    		inputtedDan = "1";
    	}
    	if (inputtedLimit == null) {
    		inputtedLimit = "9";
    	}
    	if (inputtedColor == null) {
    		inputtedColor = "black";
    	}
    	
    	int dan = Integer.parseInt(inputtedDan);
    	int limit = Integer.parseInt(inputtedLimit);
    	
		response.getWriter().append(String.format("<div style = \"color: %s;\">==%d단==</div>", inputtedColor, dan));
		
		for(int i = 1; i <= limit; i++) {
			response.getWriter().append(String.format("<div style = \"color: %s;\">%d * %d = %d</div>", inputtedColor, dan, i, dan * i));
		}
	}
}
