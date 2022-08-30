package vo;

/*
CREATE TABLE reviewBoard(
idx INT PRIMARY KEY,
res_name VARCHAR(50) REFERENCES restaurantInfo(res_name),
nickname VARCHAR(10) REFERENCES member(id),
subject VARCHAR(50) NOT NULL,
photo VARCHAR(50),
content VARCHAR(5000) NOT NULL,
likes INT DEFAULT 0,
rating FLOAT DEFAULT 0
);
 */

public class ReviewBoardDTO {
	private int idx;
	private String res_name;
	private String nickname;
	private String subject;
	private String photo;
	private String content;
	private int likes;
	private float rating;
	private String tag_name;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getRes_name() {
		return res_name;
	}
	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;

	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getTag_name() {
		return tag_name;
	}
	public void setTag_name(String tag_name) {
		this.tag_name = tag_name;
	}
	@Override
	public String toString() {
		return "ReviewBoardDTO [idx=" + idx + ", res_name=" + res_name + ", nickname=" + nickname + ", subject="
				+ subject + ", photo=" + photo + ", content=" + content + ", likes=" + likes + ", rating="
				+ rating + "]";
	}
	

	
	
	
	
}
