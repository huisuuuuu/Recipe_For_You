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

import kr.co.rfy.mybox.model.service.MyboxService;
import kr.co.rfy.mybox.model.service.MyboxServiceImpl;
import kr.co.rfy.mybox.model.vo.Mybox;
import kr.co.rfy.mybox.model.vo.ProductBig;
import kr.co.rfy.mybox.model.vo.RecipeWithFile;

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
		String userId = (String) session.getAttribute("user_id");
		//String userId = "test1000";
		System.out.println("======== userId: " + userId);
		
		List<Mybox> myboxList = null;
		
		// ���� �щ� 紐⑸� ��泥�
		try {
			myboxList = myboxService.myboxList(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(myboxList == null) {
			myboxList = new LinkedList<Mybox>();
		}
		
		// ��遺�瑜� ��泥�
		List<ProductBig> productBigList = null;
		
		try {
			productBigList = myboxService.productBigList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		List<RecipeWithFile> recipeWithFileList = null;
		try {
			recipeWithFileList = myboxService.topMatchedRecipes(userId);
			for (RecipeWithFile recipe : recipeWithFileList) {
				System.out.println(recipe);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(recipeWithFileList == null) {
			recipeWithFileList = new LinkedList<RecipeWithFile>();
		}
		
		for (Mybox mybox : myboxList) {
			System.out.println(mybox.toString());
		}
		
		request.setAttribute("myboxList", myboxList);
		request.setAttribute("productBigList", productBigList);
		request.setAttribute("recipeWithFileList", recipeWithFileList);
		
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















