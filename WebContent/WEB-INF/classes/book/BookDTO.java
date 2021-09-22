package book;

public class BookDTO {
	
	int sellBookID;
	String userID;
	// 기본 계정 정보 및 찜 카운트
	
	String bookName;
	String bookPublisher;
	
	int lectureYear;	
	String semesterDivide;
	String lectureDivide;
	
	String lectureName;
	String bookPrice;

	String bookContent;
	
	String totalScore;
	String bookStat;
	String writingStat;
	String cleanStat;// 레이아웃 내부에 있는 디자인
	
	int likeCount; 
	
	String collegeDivide;
	
	
	
	public BookDTO() {
		
	}
	
	public BookDTO(int sellBookID, String userID, String bookName, String bookPublisher, int lectureYear,
			String semesterDivide, String lectureDivide, String lectureName, String bookPrice, String bookContent,
			String totalScore, String bookStat, String writingStat, String cleanStat, int likeCount,
			String collegeDivide) {
		super();
		this.sellBookID = sellBookID;
		this.userID = userID;
		this.bookName = bookName;
		this.bookPublisher = bookPublisher;
		this.lectureYear = lectureYear;
		this.semesterDivide = semesterDivide;
		this.lectureDivide = lectureDivide;
		this.lectureName = lectureName;
		this.bookPrice = bookPrice;
		this.bookContent = bookContent;
		this.totalScore = totalScore;
		this.bookStat = bookStat;
		this.writingStat = writingStat;
		this.cleanStat = cleanStat;
		this.likeCount = likeCount;
		this.collegeDivide = collegeDivide;
	}




	public String getCollegeDivide() {
		return collegeDivide;
	}

	public void setCollegeDivide(String collegeDivide) {
		this.collegeDivide = collegeDivide;
	}

	public int getSellBookID() {
		return sellBookID;
	}
	public void setSellBookID(int sellBookID) {
		this.sellBookID = sellBookID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookPublisher() {
		return bookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}
	public int getLectureYear() {
		return lectureYear;
	}
	public void setLectureYear(int lectureYear) {
		this.lectureYear = lectureYear;
	}
	public String getSemesterDivide() {
		return semesterDivide;
	}
	public void setSemesterDivide(String semesterDivide) {
		this.semesterDivide = semesterDivide;
	}
	public String getLectureDivide() {
		return lectureDivide;
	}
	public void setLectureDivide(String lectureDivide) {
		this.lectureDivide = lectureDivide;
	}
	public String getLectureName() {
		return lectureName;
	}
	public void setLectureName(String lectureName) {
		this.lectureName = lectureName;
	}
	public String getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}
	public String getBookContent() {
		return bookContent;
	}
	public void setBookContent(String bookContent) {
		this.bookContent = bookContent;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public String getBookStat() {
		return bookStat;
	}
	public void setBookStat(String bookStat) {
		this.bookStat = bookStat;
	}
	public String getWritingStat() {
		return writingStat;
	}
	public void setWritingStat(String writingStat) {
		this.writingStat = writingStat;
	}
	public String getCleanStat() {
		return cleanStat;
	}
	public void setCleanStat(String cleanStat) {
		this.cleanStat = cleanStat;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	
	
	
	
	

}
