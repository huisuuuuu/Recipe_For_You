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

/**
 * Servlet implementation class AdminProductBigSelectServlet
 */
@WebServlet("/admin/productBig.do")
public class AdminProductBigSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductBigSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  AdminRecipeBoardService rbService = new AdminRecipeBoardServiceImpl();
		  ArrayList<AdminProductBig> list = rbService.selectProductBig();
		
		  JSONArray jsonArray = new JSONArray();
		  
		  for(AdminProductBig pb : list) {
			  
			  JSONObject json = new JSONObject();
			  json.put("bigCode", pb.getBigCode());
			  json.put("bigName", pb.getBigName());
			  
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
