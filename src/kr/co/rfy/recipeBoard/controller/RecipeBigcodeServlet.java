package kr.co.rfy.recipeBoard.controller;

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

import kr.co.rfy.recipeBoard.model.service.RecipeService;
import kr.co.rfy.recipeBoard.model.service.RecipeServiceImpl;
import kr.co.rfy.recipeBoard.model.vo.MiddleCode;

/**
 * Servlet implementation class RecipeBigcodeServlet
 */			
@WebServlet("/recipe/recipeBigcodeSelect.do")
public class RecipeBigcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeBigcodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
			String bigCode=request.getParameter("bigCode");
			
			RecipeService rService = new RecipeServiceImpl();
			ArrayList<MiddleCode> mList	=rService.getBigCode(bigCode);
			
			
			JSONArray jsonArray = new JSONArray();
			
			for(MiddleCode mCode : mList)
			{
				JSONObject json  = new JSONObject();
				json.put("mCode",mCode.getmCode());
				json.put("mName",mCode.getmName());
				
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
