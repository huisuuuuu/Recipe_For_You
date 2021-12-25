package kr.co.rfy.qna.model.service;

import java.util.HashMap;

import kr.co.rfy.qna.model.vo.Qna;

public interface QnaService {

	HashMap<String, Object> selectAllQnaList(int currentPage);

	HashMap<String, Object> qnaSearch(int currentPage, String keyword, String type);

	Qna qnaView(int board_no);

}
