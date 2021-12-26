package kr.co.rfy.adminRecipeBoard.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.rfy.adminRecipeBoard.model.vo.AdminProductBig;
import kr.co.rfy.adminRecipeBoard.model.vo.AdminProductIngredient;
import kr.co.rfy.adminRecipeBoard.model.vo.AdminProductMiddle;
import kr.co.rfy.adminRecipeBoard.model.vo.AdminRecipeBoard;
import kr.co.rfy.adminRecipeBoard.model.vo.RecipeContent;
import kr.co.rfy.adminRecipeBoard.model.vo.RecipeImage;
import kr.co.rfy.adminRecipeBoard.model.vo.RecipeIngredient;
import kr.co.rfy.common.JDBCTemplate;

public class AdminRecipeBoardDAO {

	public ArrayList<AdminRecipeBoard> selectAllPostPageList(Connection conn, int currentPage, int recordCountPerPage) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<AdminRecipeBoard> list = new ArrayList<AdminRecipeBoard>();
		
		/*
		--TOP-N 분석
		--전체적인 순위를 만들고, 그 순위에서 가장 최상위에서 몇번째까지

		--1Page => NUM이 1~5까지의 글을 가져오면 됨
		--2Page => NUM이 6~10까지의 글을 가져오면 됨
		--3Page => NUM이 11~15까지의 글을 가져오면 됨

		--공식
		--START = 현재 페이지 * 목록 개수 - (목록 개수 - 1)
		--END = 현재 페이지 * 목록 개수

		--ex) 만약 1 페이지라면(목록 개수 5개)
		--START = 1*5 - (5-1) => 1
		--END = 1*5 => 5
		*/
		
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		int end = currentPage * recordCountPerPage;
		
		String query = "SELECT * " + 
						"FROM(SELECT ROW_NUMBER() OVER(order BY BOARD_NO DESC) AS NUM, rb.*, "+
						"rc.RECIPE_NAME, m.USER_NAME " + 
						"FROM(RECIPE_BOARD rb JOIN MEMBER m ON(rb.user_id = m.user_id)) "+ 
						"JOIN RECIPE_CLASS rc ON(rb.recipe_code = rc.recipe_code) " + 
						"WHERE rb.END_YN='N') " + 
						"WHERE NUM BETWEEN ? AND ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				AdminRecipeBoard recipeBoard = new AdminRecipeBoard();
				
				recipeBoard.setBoardCode(rset.getString("board_code"));
				recipeBoard.setBoardNo(rset.getInt("board_no"));
				recipeBoard.setRecipeCode(rset.getString("recipe_code"));
				recipeBoard.setLevelCode(rset.getString("level_code"));
				recipeBoard.setTimeCode(rset.getString("time_code"));
				recipeBoard.setTitle(rset.getString("title"));
				recipeBoard.setSubTitle(rset.getString("subtitle"));
				recipeBoard.setUserId(rset.getString("user_id"));
				recipeBoard.setRegDate(rset.getDate("regdate"));
				recipeBoard.setLikeNum(rset.getInt("like_num"));
				recipeBoard.setViewCount(rset.getInt("view_count"));
				recipeBoard.setEndYN(rset.getString("end_yn").charAt(0));
				recipeBoard.setRecipeName(rset.getString("recipe_name"));
				recipeBoard.setUserName(rset.getString("user_name"));
				
				list.add(recipeBoard);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public String getPageNavi(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage) {
		
		int recordTotalCount = totalCount(conn); //전체 글 개수
		
		int pageTotalCount = 0; //전체 페이지 개수
		
		//현재 한 페이지당 보여주는 글(post) 개수가 5개이므로, post 수가 5개라면 1page
		//post 수가 6개라면 2page
		//post 수가 105개라면 21page
		//post 수가 108개라면 22page
		
		/*
		if((recordTotalCount % recordCountPerPage) > 0)
		{	
			//나머지가 있다면 페이지를 1개 더 만들어라
			pageTotalCount = (recordTotalCount / recordCountPerPage)+1;
		}else
		{
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		*/
		
		pageTotalCount = (int)Math.ceil(recordTotalCount/(double)recordCountPerPage);
		
		/*
		 현재 내가 요청한 Page에 따라 startNavi 값과 endNavi 값을 구할 수 있어야한다.
		 
		 ex1)현재 page가 1이라면
		 	 startNavi: 1, endNavi: 5
		 	 
		 ex2)현재 page가 3이라면
		 	 startNavi: 1, endNavi: 5
		 
		 ex3)현재 page가 7이라면
		 	 startNavi: 6, endNavi: 10
		 	 
		 startNavi 공식
		 startNavi = (((현재 페이지 - 1) / Navi 당 보여질 개수) * Navi 당 보여질 개수) + 1
		 
		 코드로 표현
		 startNavi = (((currentPage - 1) / naviCountPerPage) * naviCountPerPage) + 1
		 
		 endNavi 공식
		 endNavi = 시작 Navi 값 +(Navi 당 보여질 개수 - 1)
		 
		 코드로 표현
		 endNavi = startNavi + (naviCountPerPage - 1)
		 
		 */
		
		int startNavi = (((currentPage - 1) / naviCountPerPage) * naviCountPerPage) + 1; 
		int endNavi = startNavi + (naviCountPerPage - 1);
		
		//단, 상기 공식에는 문제가 없지만, 예외 상황이 한가지 있다.
		//만약 현재 페이지가 21페이지라면? < 21 22
		//startNavi = (((21-1) / 5)*5)+1 -> 21
		//endNavi = 21+(5-1) -> 25
		
		//만약 공식으로 구한 endNavi가 총 Page 수보다 크다면 총 Page 수로 셋팅
		if(endNavi > pageTotalCount)
		{
			endNavi = pageTotalCount;
		}
		
		//PageNavi 모양 만들기
		StringBuilder sb = new StringBuilder();
		
		if(startNavi==1) {
			
			sb.append("<li class='page-item disabled'><span class='page-link'" + 
					"aria-label=\"Previous\"> <span aria-hidden='true'>&laquo;</span>" + 
					"</span></li>");
			
		}else
		{
		sb.append("<li class='page-item'><a class='page-link text-dark'" + 
				"href='/recipeBoard/recipeBoardAllSelect.do?currentPage="+(startNavi-1)+"' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span>" + 
				"</a></li>");
		}
		
		for(int i=startNavi; i<=endNavi; i++)
		{	
			if(i==currentPage)
			{
				sb.append("<li class='page-item active' aria-current='page'>" + 
						"<a class='page-link' href='/recipeBoard/recipeBoardAllSelect.do?currentPage="+i+"'>"+i+"</a></li>");
			}else
			{
				sb.append("<li class='page-item'><a class='page-link text-dark' href='/recipeBoard/recipeBoardAllSelect.do?currentPage="+i+"'>"+i+"</a></li>");
			}
			
		}
		
		if(endNavi==pageTotalCount)
		{
			sb.append("<li class='page-item disabled'><span class='page-link'" + 
					"aria-label='Next'> <span aria-hidden='true'>&raquo;</span>" + 
					"</span></li>");
		}else
		{
			sb.append("<li class='page-item'><a class='page-link text-dark'" + 
				"href='/recipeBoard/recipeBoardAllSelect.do?currentPage="+(endNavi+1)+"' aria-label=\"Next\"> <span aria-hidden='true'>&raquo;</span>" + 
				"</a></li>");
		}
		
		return sb.toString();
	}

	
	public int totalCount(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count = 0;
		
		String query = "SELECT COUNT(*) AS COUNT FROM RECIPE_BOARD WHERE END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				count = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
		
	}
	
	//recipe_board에서 해당 boardNo의 게시글 가져오기
	public AdminRecipeBoard selectOneRecipePost(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		AdminRecipeBoard recipeBoard = null;
		
		String query = "SELECT rb.*, rc.RECIPE_NAME, m.USER_NAME FROM (RECIPE_BOARD rb JOIN RECIPE_CLASS rc " + 
					"ON (rb.recipe_code = rc.recipe_code)) JOIN MEMBER m ON (rb.user_id = m.user_id) " + 
					"WHERE rb.BOARD_NO=? AND rb.END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				recipeBoard = new AdminRecipeBoard();
				
				recipeBoard.setBoardCode(rset.getString("board_code"));
				recipeBoard.setBoardNo(rset.getInt("board_no"));
				recipeBoard.setRecipeCode(rset.getString("recipe_code"));
				recipeBoard.setLevelCode(rset.getString("level_code"));
				recipeBoard.setTimeCode(rset.getString("time_code"));
				recipeBoard.setTitle(rset.getString("title"));
				recipeBoard.setSubTitle(rset.getString("subtitle"));
				recipeBoard.setUserId(rset.getString("user_id"));
				recipeBoard.setRegDate(rset.getDate("regdate"));
				recipeBoard.setLikeNum(rset.getInt("like_num"));
				recipeBoard.setViewCount(rset.getInt("view_count"));
				recipeBoard.setEndYN(rset.getString("end_yn").charAt(0));
				recipeBoard.setRecipeName(rset.getString("recipe_name"));
				recipeBoard.setUserName(rset.getString("user_name"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return recipeBoard;
	}
	
	public int selectOneRecipePost(Connection conn, String title, String writer) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int boardNo = 0;
		
		String query = "SELECT BOARD_NO FROM RECIPE_BOARD WHERE TITLE=? AND USER_ID=? ORDER BY 1 DESC";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				boardNo = rset.getInt("board_no");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return boardNo;
	}
	
	//recipe_content에서 해당 boardNo을 가진 recipe의 content 내용 가져오기
	public ArrayList<RecipeContent> selectOneRecipePostContent(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<RecipeContent> contentList = new ArrayList<RecipeContent>();
		
		String query = "SELECT * FROM RECIPE_CONTENT WHERE BOARD_NO=? AND END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				RecipeContent content = new RecipeContent();
				
				content.setBoardNo(rset.getInt("board_no"));
				content.setContentNo(rset.getInt("content_no"));
				content.setContent(rset.getString("content"));
				content.setEndYN(rset.getString("end_YN").charAt(0));
				
				contentList.add(content);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return contentList;
	}
	
	//recipe_management에서 해당 boardNo을 가진 recipe의 재료 목록 가져오기
	public ArrayList<RecipeIngredient> selectOneRecipePostIngredient(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<RecipeIngredient> ingredientList = new ArrayList<RecipeIngredient>();
		
		String query = "SELECT * FROM RECIPE_MANAGEMENT WHERE BOARD_NO=? AND END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				RecipeIngredient ingredient = new RecipeIngredient();
				
				ingredient.setBoardNo(rset.getInt("board_no"));
				ingredient.setIngredientName(rset.getString("ingredient_name"));
				ingredient.setIngredientNum(rset.getString("ingredient_num"));
				ingredient.setEndYN(rset.getString("end_yn").charAt(0));
				
				ingredientList.add(ingredient);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return ingredientList;
	}
	
	//recipe_file에서 해당 boardNo을 가진 recipe의 이미지 목록 가져오기
	public ArrayList<RecipeImage> selectOneRecipePostImage(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<RecipeImage> imageList = new ArrayList<RecipeImage>();
		
		String query = "SELECT * FROM RECIPE_FILE WHERE BOARD_NO=? AND END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				RecipeImage image = new RecipeImage();
				
				image.setBoardNo(rset.getInt("board_no"));
				image.setFileNo(rset.getInt("file_no"));
				image.setFileName(rset.getString("file_name"));
				image.setFilePath(rset.getString("file_path"));
				image.setEndYN(rset.getString("end_yn").charAt(0));
				
				imageList.add(image);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return imageList;
	}

	public ArrayList<AdminRecipeBoard> selectSerachPostList(Connection conn, int currentPage, int recordCountPerPage,
			String keyword, String type) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AdminRecipeBoard> list = new ArrayList<AdminRecipeBoard>();
	
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		int end = currentPage * recordCountPerPage;
		
		String query = "";
		
		switch(type) {
		
		case "writer" :
						query = "SELECT * " + 
								"FROM(SELECT ROW_NUMBER() OVER(order BY BOARD_NO DESC) AS NUM, rb.*, "+
								"rc.RECIPE_NAME, m.USER_NAME " + 
								"FROM(RECIPE_BOARD rb JOIN MEMBER m ON(rb.user_id = m.user_id)) "+ 
								"JOIN RECIPE_CLASS rc ON(rb.recipe_code = rc.recipe_code) " + 
								"WHERE rb.END_YN='N' AND m.user_name like ?) " + 
								"WHERE NUM BETWEEN ? AND ?";
								break;
		case "subject" :
						query = "SELECT * " + 
								"FROM(SELECT ROW_NUMBER() OVER(order BY BOARD_NO DESC) AS NUM, rb.*, "+
								"rc.RECIPE_NAME, m.USER_NAME " + 
								"FROM(RECIPE_BOARD rb JOIN MEMBER m ON(rb.user_id = m.user_id)) "+ 
								"JOIN RECIPE_CLASS rc ON(rb.recipe_code = rc.recipe_code) " + 
								"WHERE rb.END_YN='N' AND rb.title like ?) " + 
								"WHERE NUM BETWEEN ? AND ?";
								break;
		
						}
	
		
		try {
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				AdminRecipeBoard recipeBoard = new AdminRecipeBoard();
				
				recipeBoard.setBoardCode(rset.getString("board_code"));
				recipeBoard.setBoardNo(rset.getInt("board_no"));
				recipeBoard.setRecipeCode(rset.getString("recipe_code"));
				recipeBoard.setLevelCode(rset.getString("level_code"));
				recipeBoard.setTimeCode(rset.getString("time_code"));
				recipeBoard.setTitle(rset.getString("title"));
				recipeBoard.setSubTitle(rset.getString("subtitle"));
				recipeBoard.setUserId(rset.getString("user_id"));
				recipeBoard.setRegDate(rset.getDate("regdate"));
				recipeBoard.setLikeNum(rset.getInt("like_num"));
				recipeBoard.setViewCount(rset.getInt("view_count"));
				recipeBoard.setEndYN(rset.getString("end_yn").charAt(0));
				recipeBoard.setRecipeName(rset.getString("recipe_name"));
				recipeBoard.setUserName(rset.getString("user_name"));
				
				list.add(recipeBoard);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
		
	}

	public String getSearchPageNavi(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage,
			String keyword, String type) {

		int recordTotalCount = totalSearchCount(conn, keyword, type); //검색된 글의 전체 개수
		
		int pageTotalCount = 0; //전체 페이지 개수
		
		pageTotalCount = (int)Math.ceil(recordTotalCount/(double)recordCountPerPage);
		
		int startNavi = (((currentPage - 1) / naviCountPerPage) * naviCountPerPage) + 1; 
		int endNavi = startNavi + (naviCountPerPage - 1);
		
		//만약 공식으로 구한 endNavi가 총 Page 수보다 크다면 총 Page 수로 셋팅
		if(endNavi > pageTotalCount)
		{
			endNavi = pageTotalCount;
		}
		
		//PageNavi 모양 만들기
		StringBuilder sb = new StringBuilder();
		
		if(startNavi==1) {
			
			sb.append("<li class='page-item disabled'><span class='page-link'" + 
					"aria-label=\"Previous\"> <span aria-hidden='true'>&laquo;</span>" + 
					"</span></li>");
			
		}else
		{
		sb.append("<li class='page-item'><a class='page-link text-dark'" + 
				"href='/recipeBoard/recipeBoardPostSearch.do?currentPage="+(startNavi-1)+"&keyword="+keyword+"&type="+type+"' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span>" + 
				"</a></li>");
		}
		
		for(int i=startNavi; i<=endNavi; i++)
		{	
			if(i==currentPage)
			{
				sb.append("<li class='page-item active' aria-current='page'>" + 
						"<a class='page-link' href='/recipeBoard/recipeBoardPostSearch.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a></li>");
			}else
			{
				sb.append("<li class='page-item'><a class='page-link text-dark' href='/recipeBoard/recipeBoardPostSearch.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a></li>");
			}
			
		}
		
		if(endNavi==pageTotalCount)
		{
			sb.append("<li class='page-item disabled'><span class='page-link'" + 
					"aria-label='Next'> <span aria-hidden='true'>&raquo;</span>" + 
					"</span></li>");
		}else
		{
			sb.append("<li class='page-item'><a class='page-link text-dark'" + 
				"href='/recipeBoard/recipeBoardPostSearch.do?currentPage="+(endNavi+1)+"&keyword="+keyword+"&type="+type+"' aria-label=\"Next\"> <span aria-hidden='true'>&raquo;</span>" + 
				"</a></li>");
		}
		
		return sb.toString();
	}

	private int totalSearchCount(Connection conn, String keyword, String type) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count = 0;
		String query = "";
		
		switch(type)
		{
			case "writer":
				
				query = "SELECT count(*) as count " + 
						"FROM(RECIPE_BOARD rb JOIN MEMBER m ON(rb.user_id = m.user_id)) " + 
						"JOIN RECIPE_CLASS rc ON(rb.recipe_code = rc.recipe_code) " + 
						"WHERE rb.END_YN='N' AND m.user_name like ?";
						break;
				
			case "subject":
				
				query = "SELECT count(*) as count " + 
						"FROM(RECIPE_BOARD rb JOIN MEMBER m ON(rb.user_id = m.user_id)) " + 
						"JOIN RECIPE_CLASS rc ON(rb.recipe_code = rc.recipe_code) " + 
						"WHERE rb.END_YN='N' AND rb.title like ?";
						break;
		}
		
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				count = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
	}

	public int updateRecipePostLike(Connection conn, int boardNo, int likeNum) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE RECIPE_BOARD SET LIKE_NUM=? WHERE BOARD_NO=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, likeNum+1);
			pstmt.setInt(2, boardNo);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

	public int deletePost(Connection conn, int boardNo, String writer) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query = "UPDATE RECIPE_BOARD SET END_YN='Y' WHERE BOARD_NO=? AND USER_ID=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, writer);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		System.out.println(result);
		return result;
	}

	public int deleteAdminPost(Connection conn, String[] recipeBoardNoValues) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String values = String.join(",", recipeBoardNoValues);
		//[0] = 100 / [1] = 101 / [2] 102
		//100,101,102
		System.out.println(values);
		String query = "UPDATE RECIPE_BOARD SET END_YN='Y' WHERE BOARD_NO IN("+values+")";

		try {
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int recipeBoardMemberBlack(Connection conn, String[] recipePostWriterIdValues) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String values = String.join(",", recipePostWriterIdValues);
		System.out.println(values);
		//[0] = 100 / [1] = 101 / [2] 102
		//100,101,102
		String query = "UPDATE MEMBER SET BLACK_YN='Y' WHERE USER_ID IN("+values+")";

		try {
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

	public ArrayList<AdminProductBig> selectProductBig(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AdminProductBig> list = new ArrayList<AdminProductBig>();
		
		String query = "SELECT * FROM PRODUCT_BIG";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				AdminProductBig pb = new AdminProductBig();
				
				pb.setBigCode(rset.getString("BIG_CODE"));
				pb.setBigName(rset.getString("BIG_NAME"));
				
				list.add(pb);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public ArrayList<AdminProductMiddle> selectProductMiddle(Connection conn, String bigCode) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AdminProductMiddle> list = new ArrayList<AdminProductMiddle>();
		
		String query = "SELECT * FROM PRODUCT_MIDDLE WHERE BIG_CODE=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, bigCode);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				AdminProductMiddle pm = new AdminProductMiddle();
				
				pm.setBigCode(rset.getString("BIG_CODE"));
				pm.setMiddleCode(rset.getString("MIDDLE_CODE"));
				pm.setMiddleName(rset.getString("MIDDLE_NAME"));
				
				list.add(pm);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
		
	}

	public ArrayList<AdminProductIngredient> selectProductIngredient(Connection conn, String middleCode) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<AdminProductIngredient> list = new ArrayList<AdminProductIngredient>();
		
		String query = "SELECT * FROM INGREDIENT WHERE MIDDLE_CODE=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, middleCode);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				AdminProductIngredient pi = new AdminProductIngredient();
				
				pi.setMiddleCode(rset.getString("MIDDLE_CODE"));
				pi.setIngredientCode(rset.getString("INGREDIENT_CODE"));
				pi.setIngredientName(rset.getString("INGREDIENT_NAME"));
				
				list.add(pi);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public int insertAdminRecipeBoard(Connection conn, AdminRecipeBoard arb) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO RECIPE_BOARD VALUES('BOARD-2',RECIPE_SEQ.NEXTVAL,?,?,?,?,?,?,SYSDATE,0,0,'N')";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, arb.getRecipeCode());
			pstmt.setString(2, arb.getLevelCode());
			pstmt.setString(3, arb.getTimeCode());
			pstmt.setString(4, arb.getTitle());
			pstmt.setString(5, arb.getSubTitle());
			pstmt.setString(6, arb.getUserId());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
		
	}

	public int insertRecipePostContent(Connection conn, int boardNo, String[] recipeContent) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		StringBuilder query = new StringBuilder();
		
		query.append("INSERT ALL");
		
		for(int i=0; i<recipeContent.length; i++) {
			
			query.append(" ");
			query.append("INTO RECIPE_CONTENT VALUES("+boardNo+","+(i+1)+",'"+recipeContent[i]+"', 'N')");
			query.append(" ");
		}
		
		query.append("SELECT * FROM DUAL");
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

	public int insertRecipePostIngredient(Connection conn, int boardNo, String[] ingredientNameValues,
			String[] ingredientNum) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		StringBuilder query = new StringBuilder();
		
		query.append("INSERT ALL");
		
		for(int i=0; i<ingredientNameValues.length; i++) {
			
			query.append(" ");
			query.append("INTO RECIPE_MANAGEMENT VALUES("+boardNo+", '"+
						ingredientNameValues[i]+"', '"+ingredientNum[i]+"', 'N')");
			query.append(" ");
		}
		
		query.append("SELECT * FROM DUAL");
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertRecipePostImage(Connection conn, int boardNo, String[] uploadImageNameValues,
			String[] uploadImagePathValues) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		StringBuilder query = new StringBuilder();
		
		query.append("INSERT ALL");
		
		for(int i=0; i<uploadImageNameValues.length; i++) {
			
			query.append(" ");
			query.append("INTO RECIPE_FILE VALUES("+boardNo+","+(i+1)+",'"+uploadImageNameValues[i]+"', '"+
						  uploadImagePathValues[i]+"', 'N')");
			query.append(" ");
		}
		
		query.append("SELECT * FROM DUAL");
		
		try {
			pstmt = conn.prepareStatement(query.toString());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

}
