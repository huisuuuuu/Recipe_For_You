package kr.co.rfy.Search.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.recipe.vo.Recipe;

public class SearchDAO {

	
	public ArrayList<Recipe> selectSearchPostList(Connection conn, int currentPage, int recordCountPerPage,
			String keyword,String type) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Recipe> list = new ArrayList<Recipe>();
		
		
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		int end = currentPage * recordCountPerPage;
		
		
		String query = "";
		
		switch(type)
		{
		case "latest":
			 query =
			 	"SELECT * " + 
			 	"FROM(SELECT ROW_NUMBER()OVER(ORDER BY R.BOARD_NO DESC) AS NUM,R.USER_ID,R.BOARD_NO,R.SUBTITLE,R.TITLE,L.LEVEL_NAME,C.TIME_NAME,F.FILE_PATH " + 
			 	"FROM RECIPE_BOARD R  " + 
			 	"LEFT JOIN COOK_TIME C ON(C.TIME_CODE=R.TIME_CODE)  " + 
			 	"LEFT JOIN RECIPE_LEVEL L ON(L.LEVEL_CODE=R.LEVEL_CODE)  " + 
			 	"LEFT JOIN RECIPE_FILE F ON(F.BOARD_NO=R.BOARD_NO) " + 
			 	"WHERE R.END_YN='N' AND F.FILE_NO=1 " + 
			 	"AND (R.TITLE LIKE ? OR R.SUBTITLE LIKE ?)) " + 
			 	"WHERE NUM BETWEEN ? AND ? ";
			break;
			
		case "like":
			 query =
					 "SELECT * " + 
							 	"FROM(SELECT ROW_NUMBER()OVER(ORDER BY R.LIKE_NUM DESC) AS NUM,R.USER_ID,R.BOARD_NO,R.SUBTITLE,R.TITLE,L.LEVEL_NAME,C.TIME_NAME,F.FILE_PATH " + 
							 	"FROM RECIPE_BOARD R  " + 
							 	"LEFT JOIN COOK_TIME C ON(C.TIME_CODE=R.TIME_CODE)  " + 
							 	"LEFT JOIN RECIPE_LEVEL L ON(L.LEVEL_CODE=R.LEVEL_CODE)  " + 
							 	"LEFT JOIN RECIPE_FILE F ON(F.BOARD_NO=R.BOARD_NO) " + 
							 	"WHERE R.END_YN='N' AND F.FILE_NO=1 " + 
							 	"AND (R.TITLE LIKE ? OR R.SUBTITLE LIKE ?)) " + 
							 	"WHERE NUM BETWEEN ? AND ? ";
			
			break;
		}
		
		

		try {
			pstmt = conn.prepareStatement(query);
			
			
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
			
			
			rset = pstmt.executeQuery();
			
			while(rset.next())
			{
				Recipe r = new Recipe();
				
				//r.setBoard_code(rset.getString("board_code"));
				r.setBoard_no(rset.getInt("board_no"));
				//r.setRecipe_code(rset.getString("recipe_code"));
				//r.setLevel_code(rset.getString("level_code"));
				//r.setTime_code(rset.getString("time_code"));
				r.setTitle(rset.getString("title"));
				r.setSubtitle(rset.getString("subtitle"));
				r.setUser_id(rset.getString("user_id"));
				//r.setRegdate(rset.getDate("regdate"));
				//r.setLike_num(rset.getInt("like_num"));
				//r.setView_count(rset.getInt("view_count"));
				//r.setEnd_YN(rset.getString("end_yn").charAt(0));
				r.setFile_path(rset.getString("file_path"));
				r.setLevel_name(rset.getString("level_name"));
				r.setTime_name(rset.getString("time_name"));
				//r.setContent(rset.getString("content"));
				//r.setFile_no(rset.getInt("file_no"));
				
				list.add(r);
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

	public String getSearchPageNavi(Connection conn, int naviCountPerPage, int recordCountPerPage, int currentPage,
			String keyword,String type) {
		int recordTotalCount = totalSearchCount(conn,keyword); //전체 글 갯수
		
		int pageTotalCount = 0; //전체 페이지 개수
		
		
		
		if((recordTotalCount % recordCountPerPage)>0)
		{
			pageTotalCount = (recordTotalCount / recordCountPerPage) + 1;
		}else
		{
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
	
		int startNavi = (((currentPage-1) / naviCountPerPage) * naviCountPerPage) + 1;
		int endNavi = startNavi + (naviCountPerPage-1);
		
	
		if(endNavi > pageTotalCount)
		{
			endNavi = pageTotalCount;
		}

		
		// PageNavi 모양 만들기
		
				StringBuilder sb = new StringBuilder();
				
				
				
				if(startNavi!=1)
				{
					sb.append("<li class='page-item'><a class='page-link text-dark'" +	
				"href='/search/recipe.do?currentPage="+(startNavi-1)+"&keyword="+keyword+"&type="+type+"' aria-label='Previous'> <span aria-hidden='true'>&laquo;</span>" +
							"</a></li>");
					
				}
				else {
					sb.append("<li class='page-item disabled'><span class='page-link'" +
							"aria-label=\"Previous\"> <span aria-hidden='true'>&laquo;</span>" +
							"</span></li>");
				}
				
				for(int i=startNavi; i<=endNavi;i++)
				{
					if(i==currentPage)
					{
						sb.append("<li class='page-item active' aria-current='page'>" +
								"<a class='page-link' style='background-color:#5D9A71;border-color:#5D9A71;' href='/search/recipe.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a></li>");
	
					}else
					{

						sb.append("<li class='page-item'><a class='page-link text-dark' href='/search/recipe.do?currentPage="+i+"&keyword="+keyword+"&type="+type+"'>"+i+"</a></li>");
					}
					
				}
				
				if(endNavi!=pageTotalCount)
				{
				
					sb.append("<li class='page-item'><a class='page-link text-dark'" +
							"href='/search/recipe.do?currentPage="+(endNavi+1)+"&keyword="+keyword+"&type="+type+"' aria-label=\"Next\"> <span aria-hidden='true'>&raquo;</span>" +
							"</a></li>");
				}else
				{
					sb.append("<li class='page-item disabled'><span class='page-link'" +
							"aria-label='Next'> <span aria-hidden='true'>&raquo;</span>" +
							"</span></li>");
				}
				
				
				
				
		
		return sb.toString();
	}

	private int totalSearchCount(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count = 0;
		
		String query =" SELECT COUNT(*) as count FROM recipe_BOARD  " + 
				"  WHERE recipe_BOARD.END_YN='N'  AND (RECIPE_BOARD.TITLE like ? or RECIPE_BOARD.SUBTITLE LIKE ? ) ";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");
			
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				count = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}

	
		

	public int count(Connection conn,String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int count = 0;
		
		String query =" SELECT COUNT(*) as count FROM recipe_BOARD  " + 
				"  WHERE recipe_BOARD.END_YN='N' AND (RECIPE_BOARD.TITLE like ? or RECIPE_BOARD.SUBTITLE LIKE ?  ) ";
		
		
		try {
			pstmt = conn.prepareStatement(query);
			
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setString(2, "%"+keyword+"%");

			
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next())
			{
				count = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}

	
	
	
	

	

	

}
