package kr.co.rfy.recipeBoard.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.member.model.vo.Member;
import kr.co.rfy.recipeBoard.model.service.RecipeService;
import kr.co.rfy.recipeBoard.model.service.RecipeServiceImpl;
import kr.co.rfy.recipeBoard.model.vo.UserRecipeBoard;

/**
 * Servlet implementation class UserRecipeBoardPostUploadServlet
 */
@WebServlet("/recipeBoard/UserRecipePostUpload.do")
public class UserRecipeBoardPostUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRecipeBoardPostUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		request.setCharacterEncoding("UTF-8");
		
		
		String title = request.getParameter("title");
		String subTitle = request.getParameter("subTitle");
		String recipeCode = request.getParameter("recipe_Code");
		String levelCode = request.getParameter("level_Code");
		String timeCode = request.getParameter("time_Code");
		String writer = ((Member) request.getSession().getAttribute("member")).getUserId();
		
		UserRecipeBoard arb = new UserRecipeBoard(recipeCode, levelCode, timeCode, title, subTitle, writer);
		
		Collection<javax.servlet.http.Part> parts = request.getParts();
		//배열로 받아서 하나씩 꺼내기
		StringBuilder fileNameBuilder = new StringBuilder();
		StringBuilder filePathBuilder = new StringBuilder();
		
		for(javax.servlet.http.Part p : parts) {
			//값이 없을 경우 리턴
			
			if(!p.getName().equals("recipeImage")) continue;
			
			javax.servlet.http.Part filePart = p;
			String fileName = filePart.getSubmittedFileName();
			
			fileNameBuilder.append(fileName);
			//구분자 지정
			fileNameBuilder.append(",");
			
			//바이너리를 받기 위해서는 스트림 사용,
			InputStream fis = filePart.getInputStream();

			//실제 업로드 되어야 하는 경로 셋팅(imageUploadFilePath)
			String realPath = request.getServletContext().getRealPath("/upload");
			
			//File.separator 경로 구분 방법을 문자로 제공함
			String filePath = realPath + File.separator + fileName;
			
			filePathBuilder.append("/upload/"+fileName);
			//구분자 지정
			filePathBuilder.append(",");
			
			FileOutputStream fos = new FileOutputStream(filePath);
			
			byte[] buf = new byte[1024];
			int size = 0;
			while((size=fis.read(buf)) !=-1)
				fos.write(buf,0,size);
			
			fos.close();
			fis.close();
		}
		
		// ?이상~?미만 -- 마지막 파일에 "," 구분자 없애주기
		fileNameBuilder.delete(fileNameBuilder.length()-1, fileNameBuilder.length());
		String [] uploadImageNameValues =  fileNameBuilder.toString().split(",");
		
		filePathBuilder.delete(filePathBuilder.length()-1, filePathBuilder.length());
		String [] uploadImagePathValues =  filePathBuilder.toString().split(",");
		
		String[] ingredientNameValues = request.getParameterValues("ingredientName");

		String[] ingredientNum = request.getParameterValues("ingredientNum");

		String[] recipeContent = request.getParameterValues("recipeContent");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		RecipeService rbService = new RecipeServiceImpl();
		int result = rbService.insertUserRecipePost(arb,uploadImageNameValues,uploadImagePathValues,
										ingredientNameValues,ingredientNum,recipeContent);
		
		int insertResultNum = 1+uploadImageNameValues.length+ingredientNameValues.length+recipeContent.length;
		
		if (result==insertResultNum) {
			response.sendRedirect("/recipeBoard/recipeBoardAllSelect.do");
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
