package vo;

import java.sql.Timestamp;

public class MateDTO {

	private int idx;
	private String nickname;
	private String subject;
	private String content;
	private int readcount;
	private Timestamp datetime;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public Timestamp getDatetime() {
		return datetime;
	}
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "MateDTO [idx=" + idx + ", nickname=" + nickname + ", subject=" + subject + ", content=" + content
				+ ", readcount=" + readcount + ", datetime=" + datetime + "]";
	}
	
	
	
}
