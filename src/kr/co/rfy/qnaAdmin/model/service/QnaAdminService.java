package kr.co.rfy.qnaAdmin.model.service;

import java.util.HashMap;

import kr.co.rfy.qna.model.vo.Qna;

public interface QnaAdminService {

	HashMap<String, Object> selectAllQnaList(int currentPage);

	HashMap<String, Object> qnaAdminSearch(int currentPage, String keyword, String type);

	Qna qnaAdminView(int board_no);

	int insertQna(Qna qna);

	int searchBoardNo(Qna qna);

	int updateView(Qna qna, int board_no, String user_id,String title,String content,String q_code);

	int deleteView(int board_no, String user_id);
}
