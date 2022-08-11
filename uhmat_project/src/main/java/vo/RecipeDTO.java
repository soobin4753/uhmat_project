package vo;

import java.sql.Timestamp;

public class RecipeDTO {
	private int idx;
	private String nickname;
	private String subject;
	private String content;
	private int readcount;
	private String original_File;
	private String real_File;
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
	public String getOriginal_File() {
		return original_File;
	}
	public void setOriginal_File(String original_File) {
		this.original_File = original_File;
	}
	public String getReal_File() {
		return real_File;
	}
	public void setReal_File(String real_File) {
		this.real_File = real_File;
	}
	public Timestamp getDatetime() {
		return datetime;
	}
	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}
	@Override
	public String toString() {
		return "RecipeDTO [idx=" + idx + ", nickname=" + nickname + ", subject=" + subject + ", content=" + content
				+ ", readcount=" + readcount + ", original_File=" + original_File + ", real_File=" + real_File
				+ ", datetime=" + datetime + "]";
	}
	
	
}
