package kr.co.rfy.recipe.vo;

import java.sql.Date;

public class Recipe {
/*
	 Board_Code    VARCHAR2(30)  REFERENCES Board_Code (Board_Code ) NOT NULL,
	    board_No    NUMBER primary key,
	     Recipe_Code    VARCHAR2(40) REFERENCES Recipe_Class (Recipe_Code ) NOT NULL,
	    LEVEL_CODE     VARCHAR2(50) REFERENCES RECIPE_LEVEL (LEVEL_Code ) NOT NULL,
	    TIME_CODE   VARCHAR2(50) REFERENCES COOK_TIME (TIME_Code ) NOT NULL,
	    title    VARCHAR2(100) NOT NULL,
	    subtitle    VARCHAR2(100) NOT NULL,
	    user_Id    VARCHAR2(15)  REFERENCES Member (user_id ) NOT NULL,
	    REGDATE    DATE NOT NULL,
	     Like_num    NUMBER,
	    View_Count    NUMBER DEFAULT 0,
	    END_YN    CHAR(1) NOT NULL
*/
/*
	 *   board_No    NUMBER REFERENCES Recipe_Board(Board_No ) NOT NULL,
    file_No    NUMBER NOT NULL,
    file_Name    VARCHAR2(100) NOT NULL,
    file_path    VARCHAR2(300) NOT NULL,
    end_YN    CHAR(1) NOT NULL
	 */
	
	private String board_code;
	private int board_no;
	private String recipe_code;
	private String level_code;
	private String time_code;
	private String title;
	private String subtitle;
	private String user_id;
	private Date regdate;
	private int like_num;
	private int view_count;
	private char end_YN;
	private String file_path;
	private String time_name;
	private String level_name;
	private String content;
	private int file_no;
	public String getBoard_code() {
		return board_code;
	}
	public void setBoard_code(String board_code) {
		this.board_code = board_code;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getRecipe_code() {
		return recipe_code;
	}
	public void setRecipe_code(String recipe_code) {
		this.recipe_code = recipe_code;
	}
	public String getLevel_code() {
		return level_code;
	}
	public void setLevel_code(String level_code) {
		this.level_code = level_code;
	}
	public String getTime_code() {
		return time_code;
	}
	public void setTime_code(String time_code) {
		this.time_code = time_code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public char getEnd_YN() {
		return end_YN;
	}
	public void setEnd_YN(char end_YN) {
		this.end_YN = end_YN;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getTime_name() {
		return time_name;
	}
	public void setTime_name(String time_name) {
		this.time_name = time_name;
	}
	public String getLevel_name() {
		return level_name;
	}
	public void setLevel_name(String level_name) {
		this.level_name = level_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Recipe(String board_code, int board_no, String recipe_code, String level_code, String time_code,
			String title, String subtitle, String user_id, Date regdate, int like_num, int view_count, char end_YN,
			String file_path, String time_name, String level_name, String content, int file_no) {
		super();
		this.board_code = board_code;
		this.board_no = board_no;
		this.recipe_code = recipe_code;
		this.level_code = level_code;
		this.time_code = time_code;
		this.title = title;
		this.subtitle = subtitle;
		this.user_id = user_id;
		this.regdate = regdate;
		this.like_num = like_num;
		this.view_count = view_count;
		this.end_YN = end_YN;
		this.file_path = file_path;
		this.time_name = time_name;
		this.level_name = level_name;
		this.content = content;
		this.file_no = file_no;
	}
	@Override
	public String toString() {
		return "Recipe [board_code=" + board_code + ", board_no=" + board_no + ", recipe_code=" + recipe_code
				+ ", level_code=" + level_code + ", time_code=" + time_code + ", title=" + title + ", subtitle="
				+ subtitle + ", user_id=" + user_id + ", regdate=" + regdate + ", like_num=" + like_num
				+ ", view_count=" + view_count + ", end_YN=" + end_YN + ", file_path=" + file_path + ", time_name="
				+ time_name + ", level_name=" + level_name + ", content=" + content + ", file_no=" + file_no + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
