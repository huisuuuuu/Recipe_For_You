package kr.co.rfy.mybox.cotroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.mybox.service.MyboxService;
import kr.co.rfy.mybox.service.MyboxServiceImpl;

/**
 * Servlet implementation class DeleteMyBox
 */
@WebServlet("/mybox/DeleteMyBox.do")
public class DeleteMyBox extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMyBox() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		
		MyboxService myboxService = new MyboxServiceImpl();
		
		String my_box_no = request.getParameter("my_box_no");
		
		System.out.println("@/mybox/DeleteMyBox.do - my_box_no : " +  my_box_no);
		
		// 삭제 요청
		int result =  0;
		
		try {
			result = myboxService.deleteMyBox(my_box_no);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter out = response.getWriter();
		out.print(result);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
