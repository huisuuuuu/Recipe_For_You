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
import kr.co.rfy.adminRecipeBoard.model.vo.AdminProductIngredient;

/**
 * Servlet implementation class AdminProductIngredientServlet
 */
@WebServlet("/admin/productIngredient.do")
public class AdminProductIngredientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductIngredientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String middleCode = request.getParameter("middleCode");
		
		AdminRecipeBoardService rbService = new AdminRecipeBoardServiceImpl();
		ArrayList<AdminProductIngredient> list = rbService.selectProductIngredient(middleCode);
		
		JSONArray jsonArray = new JSONArray();
		  
			for(AdminProductIngredient pi : list) {
			  
			  JSONObject json = new JSONObject();
			  json.put("ingredientCode", pi.getIngredientCode());
			  json.put("ingredientName", pi.getIngredientName());
			  
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
