package kr.co.rfy.Search.Service;

import java.util.HashMap;

public interface SearchService {



	HashMap<String, Object> selectSearchPost(int currentPage, String keyword,String type);

	

}
