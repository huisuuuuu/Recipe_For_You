package kr.co.rfy.mybox.cotroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.mybox.model.service.MyboxService;
import kr.co.rfy.mybox.model.service.MyboxServiceImpl;
import kr.co.rfy.mybox.model.vo.ProductMiddle;

/**
 * Servlet implementation class ProductBigList
 */
@WebServlet("/mybox/ProductMiddleList.do")
public class ProductMiddleList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductMiddleList() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String big_code = request.getParameter("big_code");
		
		//System.out.println("@/mybox/ProductMiddleList.do - big_code : " + big_code);
		
		MyboxService myboxService = new MyboxServiceImpl();
		
		List<ProductMiddle> productMiddleList = null;
		try {
			productMiddleList = myboxService.productMiddleList(big_code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (ProductMiddle productMiddle : productMiddleList) {
			System.out.println(productMiddle.toString());
		}
		
		request.setAttribute("productMiddleList", productMiddleList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mybox/productMiddleList.jsp");
		dispatcher.forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

















