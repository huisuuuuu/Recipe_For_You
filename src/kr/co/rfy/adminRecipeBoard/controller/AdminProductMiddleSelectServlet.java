package kr.co.rfy.adminRecipeBoard.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardService;
import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardServiceImpl;
import kr.co.rfy.adminRecipeBoard.model.vo.AdminProductBig;
import kr.co.rfy.adminRecipeBoard.model.vo.AdminProductMiddle;

/**
 * Servlet implementation class AdminProductMiddleSelectServlet
 */
@WebServlet("/admin/productMiddle.do")
public class AdminProductMiddleSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductMiddleSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String bigCode = request.getParameter("bigCode");
		
		AdminRecipeBoardService rbService = new AdminRecipeBoardServiceImpl();
		ArrayList<AdminProductMiddle> list = rbService.selectProductMiddle(bigCode);
		
		  JSONArray jsonArray = new JSONArray();
		  
		  
		  for(AdminProductMiddle pm : list) {
			  
			  JSONObject json = new JSONObject();
			  json.put("middleCode", pm.getMiddleCode());
			  json.put("middleName", pm.getMiddleName());
			  
			  jsonArray.add(json);
		  }
		  
		  response.setCharacterEncoding("UTF-8");
		  PrintWriter out = response.getWriter();
		  out.print(jsonArray);
		  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
