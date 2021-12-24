package kr.co.rfy.admin.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.rfy.admin.model.service.AdminMemberService;
import kr.co.rfy.admin.model.service.AdminMemberServiceImpl;


/**
 * Servlet implementation class MemberSearchServlet
 */
@WebServlet("/admin/MemberSearch.do")
public class MemberSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int currentPage;
		
		if(request.getParameter("currentPage")==null)
		{
			//즉, index.jsp에서 해당 게시판으로 이동하는 경우에는 가장 첫페이지만 1page로 셋팅 
			currentPage=1;
		}else {
		
	       currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		//키워드를 가져와야한다.
		
		//인코딩
		request.setCharacterEncoding("UTF-8");
		
		String keyword = request.getParameter("keyword");
		String type = request.getParameter("type");
		
		AdminMemberService adService = new AdminMemberServiceImpl();
		HashMap<String,Object> map = adService.SearchMember(currentPage,keyword,type); 
		
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/memberAllList.jsp");
		
		request.setAttribute("pageDataMap", map);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("keyword", keyword);
		
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
