package kr.co.fty.NoticeAdmin.model.service;

import java.util.HashMap;

import kr.co.rfy.notice.model.vo.Notice;

public interface NoticeAdminService {

	HashMap<String, Object> selectAllNoticeList(int currentPage);

	HashMap<String, Object> noticeAdminSearch(int currentPage, String keyword, String type);

	Notice noticeAdminView(int board_no);

	int insertNotice(Notice notice);

	int searchBoardNo(Notice notice);

	int deleteView(int board_no, String user_id);

	int updateView(Notice notice, String title, String content, int board_no, String user_id);


}
