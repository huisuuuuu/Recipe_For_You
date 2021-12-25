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

import org.apache.jasper.tagplugins.jstl.core.ForEach;

import kr.co.rfy.mybox.service.MyboxService;
import kr.co.rfy.mybox.service.MyboxServiceImpl;
import kr.co.rfy.mybox.vo.Mybox;
import kr.co.rfy.mybox.vo.ProductBig;

/**
 * Servlet implementation class myboxCreate
 */
@WebServlet("/mybox/myboxList.do")
public class MyboxList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyboxList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		MyboxService myboxService = new MyboxServiceImpl();
		
		// USER_ID
		HttpSession session = request.getSession();
//		String userId = (String) session.getAttribute("user_id");
		String userId = "test1000";
		List<Mybox> myboxList = null;
		
		// 나의 재료 목록 요청
		try {
			myboxList = myboxService.myboxList(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 대분류 요청
		List<ProductBig> productBigList = null;
		
		try {
			productBigList = myboxService.productBigList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		for (Mybox mybox : myboxList) {
			System.out.println(mybox.toString());
		}
		
		request.setAttribute("myboxList", myboxList);
		request.setAttribute("productBigList", productBigList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mybox/myboxList.jsp");
		dispatcher.forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}














