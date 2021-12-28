package kr.co.rfy.recipeBoard.model.doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.recipeBoard.model.vo.Content;
import kr.co.rfy.recipeBoard.model.vo.File;
import kr.co.rfy.recipeBoard.model.vo.Ingredient;
import kr.co.rfy.recipeBoard.model.vo.MiddleCode;
import kr.co.rfy.recipeBoard.model.vo.MyboxIngredient;
import kr.co.rfy.recipeBoard.model.vo.OurRecipe;
import kr.co.rfy.recipeBoard.model.vo.RecipeDetail;

public class RecipeDAO {

public ArrayList<OurRecipe> selectAllPostPageList(Connection conn, int currentPage, int recordCountPerPage,String type) {
		
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	ArrayList<OurRecipe> list = new ArrayList<OurRecipe>();
	
	//글번호의 시작과 끝번호
	 int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
	 int end  = currentPage * recordCountPerPage;
	 
	 String query="";
	 
	 
		switch(type)
		
		{
		case "latest_desc":
			query="SELECT * " + 
			 		"FROM(SELECT ROW_NUMBER()OVER(ORDER BY R.BOARD_NO DESC) AS NUM,R.USER_ID,R.BOARD_NO,R.SUBTITLE,R.TITLE,L.LEVEL_NAME,C.TIME_NAME,F.FILE_PATH,R.LIKE_NUM ,L.LEVEL_CODE,C.TIME_CODE " + 
			 		"FROM RECIPE_BOARD R " + 
			 		"LEFT JOIN COOK_TIME C ON(C.TIME_CODE=R.TIME_CODE) " + 
			 		"LEFT JOIN RECIPE_LEVEL L ON(L.LEVEL_CODE=R.LEVEL_CODE) " + 
			 		"LEFT JOIN RECIPE_FILE F ON(F.BOARD_NO=R.BOARD_NO) " + 
			 		"WHERE R.END_YN='N' AND F.FILE_NO=1) " + 
			 		"WHERE NUM BETWEEN ? AND ?";
						break;
			
		case "like_desc": 
					query="SELECT * FROM(SELECT ROW_NUMBER()OVER(ORDER BY R.BOARD_NO DESC) AS NUM,R.USER_ID,R.BOARD_NO,R.SUBTITLE,R.TITLE,L.LEVEL_NAME,C.TIME_NAME,F.FILE_PATH,R.LIKE_NUM ,L.LEVEL_CODE,C.TIME_CODE " + 
							"    FROM RECIPE_BOARD R\r\n" + 
							"    LEFT JOIN COOK_TIME C ON(C.TIME_CODE=R.TIME_CODE) " + 
							"    LEFT JOIN RECIPE_LEVEL L ON(L.LEVEL_CODE=R.LEVEL_CODE) " + 
							"    LEFT JOIN RECIPE_FILE F ON(F.BOARD_NO=R.BOARD_NO) " + 
							"    WHERE R.END_YN='N' AND F.FILE_NO=1) " + 
							" WHERE NUM BETWEEN ? AND ? " + 
							" ORDER BY LIKE_NUM DESC";
						break;
			
		case "level_asc":
					query="SELECT * " + 
							"FROM(SELECT ROW_NUMBER()OVER(ORDER BY R.BOARD_NO DESC) AS NUM,R.USER_ID,R.BOARD_NO,R.SUBTITLE,R.TITLE,L.LEVEL_NAME,C.TIME_NAME,F.FILE_PATH,R.LIKE_NUM " + 
							",L.LEVEL_CODE,C.TIME_CODE " + 
							"FROM RECIPE_BOARD R  " + 
							"LEFT JOIN COOK_TIME C ON(C.TIME_CODE=R.TIME_CODE) " + 
							"LEFT JOIN RECIPE_LEVEL L ON(L.LEVEL_CODE=R.LEVEL_CODE) " + 
							"LEFT JOIN RECIPE_FILE F ON(F.BOARD_NO=R.BOARD_NO) " + 
							"WHERE R.END_YN='N' AND F.FILE_NO=1) " + 
							"WHERE NUM BETWEEN ? AND ? " + 
							"ORDER BY LEVEL_CODE ASC";
						break;
			
			
		case "time_asc":
					query="SELECT * " + 
							"FROM(SELECT ROW_NUMBER()OVER(ORDER BY R.BOARD_NO DESC) AS NUM,R.USER_ID,R.BOARD_NO,R.SUBTITLE,R.TITLE,L.LEVEL_NAME,C.TIME_NAME,F.FILE_PATH,R.LIKE_NUM " + 
							",L.LEVEL_CODE,C.TIME_CODE " + 
							"FROM RECIPE_BOARD R " + 
							"LEFT JOIN COOK_TIME C ON(C.TIME_CODE=R.TIME_CODE) " + 
							"LEFT JOIN RECIPE_LEVEL L ON(L.LEVEL_CODE=R.LEVEL_CODE) " + 
							"LEFT JOIN RECIPE_FILE F ON(F.BOARD_NO=R.BOARD_NO) " + 
							"WHERE R.END_YN='N' AND F.FILE_NO=1) " + 
							"WHERE NUM BETWEEN ? AND ? " + 
							"ORDER BY TIME_CODE ASC";
						break;
			
		
		}
	try {
		pstmt = conn.prepareStatement(query);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		rset = pstmt.executeQuery();
		
		while(rset.next())
		{
			OurRecipe o = new OurRecipe();
			o.setUserId(rset.getString("user_Id"));
			o.setBoardNo(rset.getInt("board_no"));
			o.setTitle(rset.getString("title"));
			o.setSubTitle(rset.getString("subTitle"));
			o.setLevelName(rset.getString("level_name"));
			o.setTimeName(rset.getString("time_name"));
			o.setFilePath(rset.getString("file_path"));
			o.setLikeNum(rset.getInt("like_num"));
			o.setCookCode(rset.getString("time_code"));
			o.setLevelCode(rset.getString("level_code"));
			
			list.add(o);
		}
		
		
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally
	{
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
	}
		return list;
	}


	public String getNavi(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage) {
		
		//1. 전체 몇개의 글이 있는지 확인
		int recordTotalCount = totalCount(conn);
		
		//2. 전체페이지 갯수 확인
		int pageTotalCount=0;
		if((recordTotalCount % recordCountPerPage)>0)
		{
			pageTotalCount=(recordTotalCount/recordCountPerPage)+1;
		}else
		{
			pageTotalCount=recordTotalCount/recordCountPerPage;
		}
		
		//3. 현재 페이지에 보여지는 네비 모양 만들기
		int startNavi = (((currentPage-1)/naviCountPerPage) * naviCountPerPage)+1;
		int endNavi = startNavi +(naviCountPerPage -1);
		
		//4. 예외상황-> endNavi가 pageTotalCount보다 크다면 endNavi를 pageTotalCount로 설정하기
		
		if(endNavi>pageTotalCount)
		{
			endNavi=pageTotalCount;
		}
		
		//5. 페이지 네비 모양 만들기
		
		StringBuilder sb = new StringBuilder();
		
		
		
		
		
		sb.append("<nav aria-label=Page navigation example'>");
		sb.append("<ul class='pagination justify-content-center'>");
		sb.append("<li class='page-item'>");
		

		if(startNavi!=1)
		{	sb.append("<a class='page-link' href='");
			sb.append("/recipe/recipeBoard/selectAll.do?currentPage=");
			sb.append((startNavi-1));
			sb.append("' aria-label='Previous'>");
			sb.append("<span aria-hidden='true'>< prev</span>");
			sb.append("</a>");
		}
		
	
		sb.append("</li>");
		    for(int i=startNavi; i<=endNavi; i++)
			{
				sb.append("<li class='page-item'><a class='page-link' style='color:black' href='#'>");
				sb.append(i);
				sb.append("</a>");
				sb.append("</li>");
			}
			
		   sb.append("<li class='page-item'>");
		   
		   
		   if(endNavi!=pageTotalCount)
		   {   sb.append("<a class='page-link' href='");
		   		sb.append("/recipe/recipeBoard/selectAll.do?currentPage=");
				sb.append((endNavi+1));
		   		sb.append("' aria-label='Next'>");
			   sb.append("<span aria-hidden='true'> Next ></span>");
		   }
		  
		   sb.append("</a>");
		   sb.append("</li>");
		   sb.append("</ul>");
		   sb.append("</nav>");
	
			return sb.toString();
	}

	private int totalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count=0;
		
		String query="SELECT COUNT(*) AS COUNT FROM RECIPE_BOARD WHERE END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset=pstmt.executeQuery();
			
			if(rset.next())
			{
				count=rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}

	//1개의 게시물에 대한 정보 가져오기
	public RecipeDetail selectOnePost(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		RecipeDetail recipe=null;
		
		String query = "SELECT BOARD_NO,USER_ID,TITLE,SUBTITLE,LIKE_NUM FROM RECIPE_BOARD WHERE BOARD_NO=? AND END_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset=pstmt.executeQuery();

			
			if(rset.next())
			{
				recipe = new RecipeDetail();
				
				recipe.setBoardNo(rset.getInt("board_No"));
				recipe.setUserId(rset.getString("user_id"));
				recipe.setTitle(rset.getString("title"));
				recipe.setSubTitle(rset.getString("subtitle"));
				recipe.setLikeNum(rset.getInt("like_num"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
			return recipe;
		
		
	}

	//해당 레시피에 대한 내용 가져오기
	public ArrayList<Content> selectOnePostContent(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Content> contentList= new ArrayList<Content>();
		
		String query="SELECT * FROM RECIPE_CONTENT WHERE BOARD_NO=? AND END_YN='N'";		
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset=pstmt.executeQuery();
			
			while(rset.next())
			{
				Content c = new Content();
				
				c.setBoardNo(rset.getInt("board_no"));
				c.setContentNo(rset.getInt("content_no"));
				c.setContent(rset.getString("content"));
				
				contentList.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return contentList;
		
	}

	//해당 레시피에 대한 파일 가져오기
	public ArrayList<File> selectOnePostFile(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<File> fileList= new ArrayList<File>();
		
		String query = "SELECT * FROM RECIPE_FILE WHERE BOARD_NO=? AND END_YN='N'";
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset=pstmt.executeQuery();

			while(rset.next())
			{
				File file = new File();
				
				file.setBoardNo(rset.getInt("board_no"));
				file.setFileNo(rset.getInt("file_no"));
				file.setFileName(rset.getString("file_name"));
				file.setFilePath(rset.getString("file_path"));
				
				fileList.add(file);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return fileList;
		
		
	}

	public ArrayList<Ingredient> selectOnePostIngredient(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Ingredient> ingredientList= new ArrayList<Ingredient>();
		
		String query="SELECT BOARD_NO,INGREDIENT_NAME,INGREDIENT_NUM FROM RECIPE_MANAGEMENT WHERE BOARD_NO=? AND END_YN='N'";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1,boardNo);
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				
				Ingredient i = new Ingredient();
				
				i.setBaordNo(rset.getInt("board_no"));
				i.setIngredientName(rset.getString("ingredient_name"));
				i.setIngredientNum(rset.getString("ingredient_num"));
				
				ingredientList.add(i);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return ingredientList;
		
		
	}
		//마이냉장고에서 재료 가져오기
	public ArrayList<MyboxIngredient> selectMyBox(Connection conn, String userId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MyboxIngredient> list = new ArrayList<MyboxIngredient>();
		
		
		String query = "SELECT * FROM MY_BOX WHERE USER_ID=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, userId);
			
			rset=pstmt.executeQuery();
			
			while(rset.next())
			{
				MyboxIngredient m = new MyboxIngredient();
				
				m.setUserId(rset.getString("user_id"));
				m.setIngredientCode(rset.getString("ingredient_Code"));
				m.setIngredientName(rset.getString("ingredient_Name"));
				
				list.add(m);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return list;
		
	}
	

	
	public int postLike(Connection conn, int boardNo,int likeNum) {
		
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query="UPDATE RECIPE_BOARD SET LIKE_NUM =? WHERE board_No=?";		
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, likeNum);
			pstmt.setInt(2, boardNo);
			
			result=pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(pstmt);
		}
			return result;
	}

	public int postLikeCancel(Connection conn, int boardNo, int likeNum) {
		PreparedStatement pstmt = null;
		int result = 0;
	
		
		String query="UPDATE RECIPE_BOARD SET LIKE_NUM =? WHERE board_No=?";		
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, likeNum);
			pstmt.setInt(2, boardNo);
			
			result=pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(pstmt);
		}
			return result;
	}

	public int getLike(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int likeNum=0;
		
		String query="SELECT LIKE_NUM FROM RECIPE_BOARD WHERE BOARD_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				likeNum=rset.getInt("like_num");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return likeNum;
	}

	public int deleteBoardPost(Connection conn,int boardNo) {
		
		PreparedStatement pstmt = null;
		int result= 0;
		
		String query=" UPDATE RECIPE_BOARD SET END_YN='Y' WHERE BOARD_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			result=pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(pstmt);
		}
			return result;
		
		
		
		
	}

	public int deleteBoardFile(Connection conn, int boardNo) {
		
		//파일의 갯수와 실제로 지워진 파일의 갯수가 같아야 한다.
		//따라서 실제 파일의 갯수를 구하는 메소드를 생성한다.
		int fileCount=fileCount(conn,boardNo);
		
		PreparedStatement pstmt = null;
		int endFileCount= 0;
		
		String query="UPDATE RECIPE_FILE SET END_YN='Y' WHERE BOARD_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			endFileCount=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		int result = 0;
		//두개의 값이 같다면 리턴되는 값은 1
		if(fileCount==endFileCount)
		{
			result=1;
		}else
		{
			result=0;
		}
		
		return result;
	}
	
	
	//해당 게시물의 파일 갯수
	private int fileCount(Connection conn, int boardNo) {
		
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		int fileCount= 0;
		
		String query=" SELECT COUNT(*)AS COUNT FROM RECIPE_FILE WHERE BOARD_NO=?";
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset=pstmt.executeQuery();
			
			if(rset.next())
			{
				fileCount=rset.getInt("count");
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return fileCount;
	}

	public int deleteBoardContent(Connection conn, int boardNo) {
		
		//content의 갯수와 실제로 지워진 content의 갯수가 같아야 한다.
		//따라서 실제 content의 갯수를 구하는 메소드를 생성한다.
		int contentCount=contentCount(conn,boardNo);
		
		PreparedStatement pstmt = null;
		int endContentCount= 0;
		
		String query="UPDATE RECIPE_CONTENT SET END_YN='Y' WHERE BOARD_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			endContentCount=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		int result = 0;
		//두개의 값이 같다면 리턴되는 값은 1
		if(contentCount==endContentCount)
		{
			result=1;
		}else
		{
			result=0;
		}
		
		return result;
		
		
	}

	private int contentCount(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		int contentCount= 0;
		
		String query=" SELECT COUNT(*)AS COUNT FROM RECIPE_CONTENT WHERE BOARD_NO=?";
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset=pstmt.executeQuery();
			
			if(rset.next())
			{
				contentCount=rset.getInt("count");
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return contentCount;
	}

	public int deleteBoardIngredient(Connection conn, int boardNo) {
		//Ingredient의 갯수와 실제로 지워진 Ingredient의 갯수가 같아야 한다.
		//따라서 실제 Ingredient의 갯수를 구하는 메소드를 생성한다.
		int ingredientCount=ingredientCount(conn,boardNo);
		
		PreparedStatement pstmt = null;
		int endIngredientCount= 0;
		
		String query="UPDATE RECIPE_MANAGEMENT SET END_YN='Y' WHERE BOARD_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			endIngredientCount=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		int result = 0;
		//두개의 값이 같다면 리턴되는 값은 1
		if(ingredientCount==endIngredientCount)
		{
			result=1;
		}else
		{
			result=0;
		}
		
		return result;
	}

	private int ingredientCount(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		int ingredientCount= 0;
		
		String query=" SELECT COUNT(*)AS COUNT FROM RECIPE_MANAGEMENT WHERE BOARD_NO=?";
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			
			rset=pstmt.executeQuery();
			
			if(rset.next())
			{
				ingredientCount=rset.getInt("count");
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return ingredientCount;
	}


	public ArrayList<OurRecipe> selectRecipeKindAllList(Connection conn, int currentPage, int recordCountPerPage, String recipeKind) {
		
		PreparedStatement pstmt= null;
		ResultSet rset = null;
		ArrayList<OurRecipe> list = new ArrayList<OurRecipe>();
		
		//System.out.println(recipeCode);
		/* 글번호의 시작과 끝 번호를 구한다.
		 start = 현재페이지 *목록갯수-(목록갯수-1)
		 end = 현재페이지 * 목록갯수
		 
		 */
	
		
	 int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
	 int end  = currentPage * recordCountPerPage;
		
	 
	 String query="SELECT * " + 
	 		"FROM(SELECT ROW_NUMBER()OVER(ORDER BY R.BOARD_NO DESC) AS NUM,R.USER_ID,R.BOARD_NO,R.SUBTITLE,R.TITLE,L.LEVEL_NAME,C.TIME_NAME,F.FILE_PATH " + 
	 		"FROM RECIPE_BOARD R " + 
	 		"LEFT JOIN COOK_TIME C ON(C.TIME_CODE=R.TIME_CODE) " + 
	 		"LEFT JOIN RECIPE_LEVEL L ON(L.LEVEL_CODE=R.LEVEL_CODE) " + 
	 		"LEFT JOIN RECIPE_FILE F ON(F.BOARD_NO=R.BOARD_NO) " + 
	 		"WHERE R.END_YN='N' AND F.FILE_NO=1 AND R.RECIPE_CODE=?) " + 
	 		"WHERE NUM BETWEEN ? AND ?";
		
	 		try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, recipeKind);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				
				rset=pstmt.executeQuery();
				
				while(rset.next())
				{
					OurRecipe o = new OurRecipe();
					o.setUserId(rset.getString("user_Id"));
					o.setBoardNo(rset.getInt("board_no"));
					o.setTitle(rset.getString("title"));
					o.setSubTitle(rset.getString("subTitle"));
					o.setLevelName(rset.getString("level_name"));
					o.setTimeName(rset.getString("time_name"));
					o.setFilePath(rset.getString("file_path"));
					
					list.add(o);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
	 		{
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
	 		}
	 		
	 	
	 		return list;
		
	}

	public String getKindNavi(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage,
			String recipeKind) {
		
		
			//1. 카체고리별 전체 몇개의 글이 있는지 확인
			int recordTotalCount = kindtotalCount(conn,recipeKind);
			
			
			//2. 전체페이지 갯수 확인
			int pageTotalCount=0;
			if((recordTotalCount % recordCountPerPage)>0)
			{
				pageTotalCount=(recordTotalCount/recordCountPerPage)+1;
			}else
			{
				pageTotalCount=recordTotalCount/recordCountPerPage;
			}
			
			//3. 현재 페이지에 보여지는 네비 모양 만들기
			int startNavi = (((currentPage-1)/naviCountPerPage) * naviCountPerPage)+1;
			int endNavi = startNavi +(naviCountPerPage -1);
			
			//4. 예외상황-> endNavi가 pageTotalCount보다 크다면 endNavi를 pageTotalCount로 설정하기
			
			if(endNavi>pageTotalCount)
			{
				endNavi=pageTotalCount;
			}
			
			//5. 페이지 네비 모양 만들기
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<nav aria-label=Page navigation example'>");
			sb.append("<ul class='pagination justify-content-center'>");
			sb.append("<li class='page-item'>");
			

			if(startNavi!=1)
			{	sb.append("<a class='page-link' href='");
				sb.append("/recipe/recipeBoard/selectAll.do?currentPage=");
				sb.append((startNavi-1));
				sb.append("' aria-label='Previous'>");
				sb.append("<span aria-hidden='true'>< prev</span>");
				sb.append("</a>");
			}
			
		
			sb.append("</li>");
			    for(int i=startNavi; i<=endNavi; i++)
				{
					sb.append("<li class='page-item'><a class='page-link' style='color:black' href='#'>");
					sb.append(i);
					sb.append("</a>");
					sb.append("</li>");
				}
				
			   sb.append("<li class='page-item'>");
			   
			   
			   if(endNavi!=pageTotalCount)
			   {   sb.append("<a class='page-link' href='");
			   		sb.append("/recipe/recipeBoard/selectAll.do?currentPage=");
					sb.append((endNavi+1));
			   		sb.append("' aria-label='Next'>");
				   sb.append("<span aria-hidden='true'> Next ></span>");
			   }
			  
			   sb.append("</a>");
			   sb.append("</li>");
			   sb.append("</ul>");
			   sb.append("</nav>");
		
				
			   return sb.toString();
				
				
	}

	private int kindtotalCount(Connection conn, String recipeKind) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count=0;
		
		
		String query="SELECT COUNT(*) AS COUNT FROM RECIPE_BOARD WHERE END_YN='N' AND RECIPE_CODE=? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, recipeKind);
			rset=pstmt.executeQuery();
			
			if(rset.next())
			{
				count=rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		

		return count;
		
	}

	public ArrayList<OurRecipe> selectMyRecipeList(Connection conn, int currentPage, int recordCountPerPage,String userId) {
		


		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OurRecipe> list = new ArrayList<OurRecipe>();
		
		//글번호의 시작과 끝번호
		 int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		 int end  = currentPage * recordCountPerPage;
		 
		 String query="SELECT * " + 
		 		"FROM(SELECT ROW_NUMBER()OVER(ORDER BY R.BOARD_NO DESC) AS NUM,R.USER_ID,R.BOARD_NO,R.SUBTITLE,R.TITLE,L.LEVEL_NAME,C.TIME_NAME,F.FILE_PATH " + 
		 		"FROM RECIPE_BOARD R " + 
		 		"LEFT JOIN COOK_TIME C ON(C.TIME_CODE=R.TIME_CODE) " + 
		 		"LEFT JOIN RECIPE_LEVEL L ON(L.LEVEL_CODE=R.LEVEL_CODE) " + 
		 		"LEFT JOIN RECIPE_FILE F ON(F.BOARD_NO=R.BOARD_NO) " + 
		 		"WHERE R.END_YN='N' AND F.FILE_NO=1 AND R.USER_ID=?) " + 
		 		"WHERE NUM BETWEEN ? AND ?";
		
		
		 try {
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, userId);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rset=pstmt.executeQuery();
			
			while(rset.next())
			{
				OurRecipe o = new OurRecipe();
				o.setUserId(rset.getString("user_Id"));
				o.setBoardNo(rset.getInt("board_no"));
				o.setTitle(rset.getString("title"));
				o.setSubTitle(rset.getString("subTitle"));
				o.setLevelName(rset.getString("level_name"));
				o.setTimeName(rset.getString("time_name"));
				o.setFilePath(rset.getString("file_path"));
				
				list.add(o);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		 {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		 }
	
		 return list;
	}
	
	
	//나의 레시피 페이지 navi 생성 메소드
	public String getMyRecipeNavi(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage) {
		
		//1. 나의 레시피  글 총 갯수  확인
		int recordTotalCount = myRecipetotalCount(conn);
		
		//2. 전체페이지 갯수 확인
		int pageTotalCount=0;
		if((recordTotalCount % recordCountPerPage)>0)
		{
			pageTotalCount=(recordTotalCount/recordCountPerPage)+1;
		}else
		{
			pageTotalCount=recordTotalCount/recordCountPerPage;
		}
		
		//3. 현재 페이지에 보여지는 네비 모양 만들기
		int startNavi = (((currentPage-1)/naviCountPerPage) * naviCountPerPage)+1;
		int endNavi = startNavi +(naviCountPerPage -1);
		
		//4. 예외상황-> endNavi가 pageTotalCount보다 크다면 endNavi를 pageTotalCount로 설정하기
		
		if(endNavi>pageTotalCount)
		{
			endNavi=pageTotalCount;
		}
		
		//5. 페이지 네비 모양 만들기
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("<nav aria-label=Page navigation example'>");
		sb.append("<ul class='pagination justify-content-center'>");
		sb.append("<li class='page-item'>");
		

		if(startNavi!=1)
		{	sb.append("<a class='page-link' href='");
			sb.append("/recipe/recipeBoard/selectAll.do?currentPage=");
			sb.append((startNavi-1));
			sb.append("' aria-label='Previous'>");
			sb.append("<span aria-hidden='true'>< prev</span>");
			sb.append("</a>");
		}
		
	
		sb.append("</li>");
		    for(int i=startNavi; i<=endNavi; i++)
			{
				sb.append("<li class='page-item'><a class='page-link' style='color:black' href='#'>");
				sb.append(i);
				sb.append("</a>");
				sb.append("</li>");
			}
			
		   sb.append("<li class='page-item'>");
		   
		   
		   if(endNavi!=pageTotalCount)
		   {   sb.append("<a class='page-link' href='");
		   		sb.append("/recipe/recipeBoard/selectAll.do?currentPage=");
				sb.append((endNavi+1));
		   		sb.append("' aria-label='Next'>");
			   sb.append("<span aria-hidden='true'> Next ></span>");
		   }
		  
		   sb.append("</a>");
		   sb.append("</li>");
		   sb.append("</ul>");
		   sb.append("</nav>");
	
		   return sb.toString();
			

	}

	//내가 작성한 레시피의 총 글 갯수 가져오는 메소드
	private int myRecipetotalCount(Connection conn) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int count=0;
		
		String query="SELECT count(*) as COUNT FROM RECIPE_BOARD WHERE USER_ID='user11'";
		
		try {
			pstmt=conn.prepareStatement(query);
			
			//pstmt.setString(1, userId);
			
			rset=pstmt.executeQuery();
			
			if(rset.next())
			{
				count=rset.getInt("count");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
	}

	//대분류를 이용한 중분류 코드 가져오기
	public ArrayList<MiddleCode> getBigCode(Connection conn, String bigCode) {
		
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		ArrayList<MiddleCode> mList = new ArrayList<MiddleCode>();
		
		String query="SELECT MIDDLE_CODE,MIDDLE_NAME FROM PRODUCT_MIDDLE WHERE BIG_CODE =?";
			try {
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1, bigCode);
				rset = pstmt.executeQuery();
				
				
				while(rset.next())
				{
					MiddleCode mCode = new MiddleCode();
					
					mCode.setmCode(rset.getString("middle_code"));
					mCode.setmName(rset.getString("middle_name"));
					
					
					mList.add(mCode);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			{
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
		   
			return mList;
		
	}
	
	
	//중분류를 이용한 재료 코드 가져오기
	public ArrayList<MiddleCode> getMiddleCode(Connection conn, String middleCode) {
		PreparedStatement pstmt = null;
		ResultSet rset=null;
		ArrayList<MiddleCode> mList = new ArrayList<MiddleCode>();
		
		String query="SELECT INGREDIENT_CODE,INGREDIENT_NAME FROM INGREDIENT WHERE MIDDLE_CODE =?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, middleCode);
			rset=pstmt.executeQuery();
			
			while(rset.next())
			{
				MiddleCode mCode = new MiddleCode();
				
				mCode.setmCode(rset.getString("INGREDIENT_CODE"));
				mCode.setmName(rset.getString("INGREDIENT_NAME"));
				
				mList.add(mCode);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mList;
	}





		
	
	
	
}
