package kr.co.rfy.mybox.model.vo;

public class RecipeWithFile {
		
	private String title;
	private String subtitle;
	private String file_path;

	public RecipeWithFile() {
	}
	
	public RecipeWithFile(String title, String subtitle, String file_path) {
		this.title = title;
		this.subtitle = subtitle;
		this.file_path = file_path;
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

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	@Override
	public String toString() {
		return "RecipeWithFile [title=" + title + ", subtitle=" + subtitle + ", file_path=" + file_path + "]";
	}
	
	
}

