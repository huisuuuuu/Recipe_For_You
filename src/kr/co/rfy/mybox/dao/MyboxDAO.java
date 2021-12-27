package kr.co.rfy.mybox.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import kr.co.rfy.common.JDBCTemplate;
import kr.co.rfy.member.model.vo.Member;
import kr.co.rfy.mybox.vo.Ingredient;
import kr.co.rfy.mybox.vo.Mybox;
import kr.co.rfy.mybox.vo.ProductBig;
import kr.co.rfy.mybox.vo.ProductMiddle;
import kr.co.rfy.mybox.vo.RecipeWithFile;

public class MyboxDAO {

	// 나의 재료 등록
	public int myboxCreate( Connection conn, List<Mybox> myboxList )  {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String sql = " INSERT INTO my_box "
				  + " ( my_box_no, user_id, ingredient_code, ingredient_name, end_date, memo ) "
				  + " VALUES "
				  + " ( MY_BOX_SEQ.nextval, ?, ?, ?, ?, ? ) ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			for (Mybox mybox : myboxList) {
				int index = 1;
				
				String userId = mybox.getUser_id();
				int myboxNo = mybox.getMy_box_no();
				String ingredientCode = mybox.getIngredient_code();
				String ingredientName = mybox.getIngredient_name();
				String endDate = mybox.getEnd_date();
				String memo = mybox.getMemo();
				
				System.out.println("my_box_no:" + myboxNo + ", ingredientCode");
				
				pstmt.setString(index++, userId);
				pstmt.setString(index++, ingredientCode);
				pstmt.setString(index++, ingredientName);
				pstmt.setString(index++, endDate);
				pstmt.setString(index++, memo);
				
				// 등록된 행의 개수
				result = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
//			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}
	
	// 나의 재료 전체 삭제
	public int myboxDeleteAll( Connection conn, String userId )  {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String sql = " DELETE FROM my_box WHERE user_id = ? "; 
				
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, userId);
				
			// 등록된 행의 개수
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}
	
	// 나의 재료 단일 삭제
	public int myboxDelete( Connection conn, String myboxNo )  {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		String sql = " DELETE FROM my_box WHERE my_box_no = ? "; 
				
		try {
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, myboxNo);
				
			// 등록된 행의 개수
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}
	
	
	
	// 나의 재료 목록
	public List<Mybox> myboxList(Connection conn, String userId) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
//		String sql = " SELECT * FROM my_box WHERE user_id = ? ";
		
		String sql = " SELECT  box.my_box_no, "
				   + " box.user_id,  "
				   + " box.end_date, "
				   + " box.memo, "
				   + " ing.ingredient_name,  "
				   + " ing.ingredient_code, "
				   + " ing.middle_code, "
				   + " mid.middle_name, "
				   + " mid.big_code, "
				   + " big.big_name, "
				   + "  CASE WHEN sysdate - box.end_date < 0 THEN 'Y'  ELSE 'N' END end_yn "
				   + " FROM my_box box, ingredient ing, product_middle mid, product_big big "
				   + " WHERE box.ingredient_code = ing.ingredient_code "
				   + " AND ing.middle_code = mid.middle_code "
				   + " AND mid.big_code = big.big_code "
				   + " AND box.ingredient_code IN ( SELECT ingredient_code FROM my_box WHERE user_id = ? ) "
				   + " ORDER BY end_date ASC "
				   ;
		
		List<Mybox> myboxList = new LinkedList<Mybox>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
				
			rset = pstmt.executeQuery();
			
			
			while( rset.next() ) {

				int my_box_no = rset.getInt("my_box_no");
				String user_id = rset.getString("user_id");
				String ingredient_code = rset.getString("ingredient_code");
				String ingredient_name = rset.getString("ingredient_name");
				String end_date = rset.getString("end_date").substring(0, 10);			// 2021-11-22 00:00:00
				String memo = rset.getString("memo");
				String end_yn = rset.getString("end_yn");
				
				String middle_code = rset.getString("middle_code");
				String middle_name = rset.getString("middle_name");
				String big_code = rset.getString("big_code");
				String big_name = rset.getString("big_name");
				
				
				Mybox mybox = new Mybox(my_box_no, user_id, ingredient_code, ingredient_name, end_date, memo, end_yn, middle_code, middle_name, big_code, big_name);
				
				myboxList.add(mybox);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rset != null) {
				JDBCTemplate.close(rset);
			}
			if (pstmt != null) {
				JDBCTemplate.close(pstmt);
			}	
		}
		
		return myboxList;
	}
	
	
	// 대분류 조회
	public List<ProductBig> productBigList(Connection conn) {
		
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = " SELECT * FROM product_big ORDER BY big_code ASC ";
		
		List<ProductBig> productBigList = new LinkedList<ProductBig>();
		
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while( rset.next() ) {

				String big_code = rset.getString("big_code");
				String big_name = rset.getString("big_name");
				
				
				ProductBig productBig = new ProductBig(big_code, big_name);
				
				productBigList.add(productBig);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return productBigList;
	}
	
	

	
	// 중분류 조회 (목록 - 대분류 코드)
	public List<ProductMiddle> productMiddleList(Connection conn, String bigCode) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = " SELECT * FROM product_middle WHERE big_code = ? ORDER BY middle_code ASC  ";
		
		List<ProductMiddle> productMiddleList = new LinkedList<ProductMiddle>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bigCode);
			rset = pstmt.executeQuery();
			
			while( rset.next() ) {

				String big_code = rset.getString("big_code");
				String middle_code = rset.getString("middle_code");
				String middle_name = rset.getString("middle_name");
				
				ProductMiddle productMiddle = new ProductMiddle(big_code, middle_code, middle_name);
				
				productMiddleList.add(productMiddle);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return productMiddleList;
	}
	
	
	// 제품명 조회 (목록 - 중분류 코드)
	public List<Ingredient> ingredientList(Connection conn, String middleCode) {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = " SELECT * FROM ingredient WHERE middle_code = ? ORDER BY ingredient_code ASC ";
		
		List<Ingredient> ingredientList = new LinkedList<Ingredient>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, middleCode);
			rset = pstmt.executeQuery();
			
			while( rset.next() ) {

				String middle_code = rset.getString("middle_code");
				String ingredient_code = rset.getString("ingredient_code");
				String ingredient_name = rset.getString("ingredient_name");
				
				Ingredient ingredient = new Ingredient(middle_code, ingredient_code, ingredient_name);
				
				ingredientList.add(ingredient);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return ingredientList;
	}
	
	

	public List<RecipeWithFile> topMatchedRecipes(Connection conn, String user_id) {
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT *");
		sb.append("	FROM (");
		sb.append("	SELECT * ");
		sb.append("	FROM (");
		sb.append("	SELECT rm.BOARD_NO, COUNT(*) matched");
		sb.append("	FROM RECIPE_MANAGEMENT rm");
		sb.append("	JOIN (");
		sb.append("	SELECT mb.INGREDIENT_CODE, i.INGREDIENT_NAME ");
		sb.append("	FROM MY_BOX  mb ");
		sb.append("	JOIN INGREDIENT i ");
		sb.append("	ON mb.INGREDIENT_CODE = i.INGREDIENT_CODE");
		sb.append("	WHERE mb.USER_ID = " + "'" + user_id + "'");
		sb.append("	) mine");
		sb.append("	USING(INGREDIENT_NAME)");
		sb.append("	GROUP BY BOARD_NO");
		sb.append("	ORDER BY MATCHED DESC");
		sb.append("	) o1");
		sb.append("	JOIN RECIPE_BOARD o2");
		sb.append("	USING(BOARD_NO)");
		sb.append("	WHERE ROWNUM <= 4");
		sb.append("	) o3");
		sb.append("	JOIN (	SELECT * FROM RECIPE_FILE oo1	ORDER BY FILE_NAME ) o4");
		sb.append("	USING (BOARD_NO)");
		sb.append("	ORDER BY BOARD_NO");
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String sql = sb.toString();
		
		List<RecipeWithFile> recipeWithFileList = new LinkedList<RecipeWithFile>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			String beforeTitle = "";
			while( rset.next() ) {

				String title = rset.getString("title");
				String subtitle = rset.getString("subtitle");
				String file_path = rset.getString("file_path").replaceAll("\\/", "/");
				
				if(beforeTitle.compareTo(title) != 0) {
					beforeTitle = title;
					RecipeWithFile recipe = new RecipeWithFile(title, subtitle, file_path);
					recipeWithFileList.add(recipe);
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return recipeWithFileList;
	}
		
}









































