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
import kr.co.rfy.mybox.model.vo.Ingredient;
import kr.co.rfy.mybox.model.vo.ProductMiddle;

/**
 * Servlet implementation class ProductBigList
 */
@WebServlet("/mybox/IngredientList.do")
public class IngredientList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IngredientList() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String middle_code = request.getParameter("middle_code");
		
		//System.out.println("@/mybox/IngredientList.do - middle_code : " + middle_code);
		
		MyboxService myboxService = new MyboxServiceImpl();
		
		List<Ingredient> ingredientList = null;
		try {
			ingredientList = myboxService.ingredientList(middle_code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Ingredient ingredient : ingredientList) {
			System.out.println(ingredient.toString());
		}
		
		request.setAttribute("ingredientList", ingredientList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mybox/ingredientList.jsp");
		dispatcher.forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

















