package kr.co.rfy.adminRecipeBoard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardService;
import kr.co.rfy.adminRecipeBoard.model.service.AdminRecipeBoardServiceImpl;
import kr.co.rfy.adminRecipeBoard.model.vo.AdminRecipeBoard;
import kr.co.rfy.common.MemberAuthorityCheck;
import kr.co.rfy.member.model.vo.Member;

@MultipartConfig(
//		location = "/",
//		fileSizeThreshold = 1024 * 1024, // 이 크기 이상의 파일을 가져올 때 위 localhost 경로에 임시 저장
		maxFileSize = 1024 * 1024 * 100, // file 하나당 최대 크기 지정 100MB
		maxRequestSize = 1024 * 1024 * 100 * 10 // 전체 request 크기 100MB 10개 
		)

/**
 * Servlet implementation class AdminRecipeBoardPostUpdateCompleteServlet
 */
@WebServlet("/recipeBoard/adminRecipePostUpdateComplete.do")
public class AdminRecipeBoardPostUpdateCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRecipeBoardPostUpdateCompleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.setCharacterEncoding("UTF-8");
		
		String roll = MemberAuthorityCheck.authorityCheck(request, response);

		// 권한 검증 작업(권한을 확인하여 관리자 또는 운영자가 아니라면 error 페이지로 안내)
		if (roll == null) {
			response.sendRedirect("/views/common/error.jsp");
			return;
		}
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String title = request.getParameter("title");
		String subTitle = request.getParameter("subTitle");
		String recipeCode = request.getParameter("recipe_Code");
		String levelCode = request.getParameter("level_Code");
		String timeCode = request.getParameter("time_Code");
		String writer = ((Member) request.getSession().getAttribute("member")).getUserId();
		
		AdminRecipeBoard arb = new AdminRecipeBoard(boardNo, recipeCode, levelCode, timeCode, title, subTitle, writer);
		
		String[] ingredientNameValues = request.getParameterValues("ingredientName");
		String[] ingredientNumValues = request.getParameterValues("ingredientNum");
		String[] recipeContentValues = request.getParameterValues("recipeContent");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		AdminRecipeBoardService rbService = new AdminRecipeBoardServiceImpl();
		
		boolean result = rbService.updateAdminRecipePost(arb,ingredientNameValues,ingredientNumValues,recipeContentValues);
		
		if (result) {
			response.sendRedirect("/recipeBoard/recipeBoardSelectContent.do?boardNo="+boardNo);
		} else {
			response.sendRedirect("/views/common/error.jsp");
		};
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
