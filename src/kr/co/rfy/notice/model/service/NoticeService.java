package kr.co.rfy.notice.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import kr.co.rfy.notice.model.vo.Notice;

public interface NoticeService {

	HashMap<String, Object> selectAllNoticeList(int currentPage);

	HashMap<String, Object> noticeSearch(int currentPage, String keyword, String type);

	Notice noticeView(int board_no);


}
