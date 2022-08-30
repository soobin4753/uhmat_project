package vo;
/*
CREATE TABLE community_tmi (
    idx INT PRIMARY KEY,
	nickname VARCHAR(10) REFERENCES member(nickname),
	subject VARCHAR(50),
	content VARCHAR(1000),
	readcount INT NOT NULL,
	datetime TIMESTAMP DEFAULT now()
);
*/

import java.sql.Timestamp;

public class CommunityTmiDTO {
	private int idx;
	private String nickname;
	private String subject;
	private String content;
	private int readcount;
	private Timestamp date;
	private String report;
	
	
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
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	@Override
	public String toString() {
		return "CommunityTmiDTO [idx=" + idx + ", nickname=" + nickname + ", subject=" + subject + ", content="
				+ content + ", readcount=" + readcount + ", datetime=" + date + "]";
	}
	
	
	
}
