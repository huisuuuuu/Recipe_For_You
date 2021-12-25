package kr.co.rfy.mybox.cotroller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.rfy.mybox.model.service.MyboxService;
import kr.co.rfy.mybox.model.service.MyboxServiceImpl;
import kr.co.rfy.mybox.model.vo.Mybox;

/**
 * Servlet implementation class myboxCreate
 */
@WebServlet("/mybox/myboxCreate.do")
public class MyboxCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyboxCreate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); 
		
		MyboxService myboxService = new MyboxServiceImpl();
		
		// 대분류
		String[] productBigList = request.getParameterValues("product_big");
		
		// 중분류
		String[] productMidList = request.getParameterValues("product_mid");
		
		// 제품명
		String[] ingredientCodeList = request.getParameterValues("ingredient");
//		String[] ingredientNameList = request.getParameterValues("ingredient_name");
		
		// 유통기한
		String[] endDateList = request.getParameterValues("end_date");
		
		// 메모
		String[] memoList = request.getParameterValues("memo");
		
		// USER_ID
		HttpSession session = request.getSession();
//		String userId = (String) session.getAttribute("user_id");
		String userId = "test1000";
		
		System.out.println("---------------------------------------------------");
		System.out.println("productBigList 개수 " +  productBigList.length);
		System.out.println("productMidList 개수 " +  productMidList.length);
		System.out.println("ingredientCodeList 개수 " +  ingredientCodeList.length);
		System.out.println("endDateList 개수 " +  endDateList.length);
		System.out.println("memoList 개수 " +  memoList.length);
		
		List<Mybox> myBoxList = new LinkedList<Mybox>();
		
		for (int i = 0; i < ingredientCodeList.length; i++) {
			
			String ingredientCode = ingredientCodeList[i];
//			String ingredientName = ingredientNameList[i];
			String endDateStr = endDateList[i];
//			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
//			Date endDate = null;
//			try {
//				endDate = transFormat.parse(endDateStr);
//			} catch (ParseException e) {
//				e.printStackTrace();
//			}
			String memo  = memoList[i];
			
			Mybox mybox = new Mybox(userId, ingredientCode, "test", endDateStr, memo);
			
			myBoxList.add(mybox);
		}
		
		int result = 0;
		try {
			result = myboxService.myboxCreate(myBoxList, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 성공
		if( result > 0 ) {
			
		} 
		// 반영된 데이터 없음
		else {
			
		}

		String path = "/mybox/myboxList.do";
		response.sendRedirect(path);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}















