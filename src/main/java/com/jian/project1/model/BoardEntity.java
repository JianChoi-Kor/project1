package com.jian.project1.model;

public class BoardEntity {

	private int boardPk;
	private int category;
	private int seq;
	private String title;
	private String content;
	private int hits;
	private int userPk;
	private String regDt;
	private String modDt;
	private int isDel;
	private String userNm;
	private String boardMainImg;
	
	public String getBoardMainImg() {
		return boardMainImg;
	}
	public void setBoardMainImg(String boardMainImg) {
		this.boardMainImg = boardMainImg;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public int getBoardPk() {
		return boardPk;
	}
	public void setBoardPk(int boardPk) {
		this.boardPk = boardPk;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	public int getUserPk() {
		return userPk;
	}
	public void setUserPk(int userPk) {
		this.userPk = userPk;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	public String getModDt() {
		return modDt;
	}
	public void setModDt(String modDt) {
		this.modDt = modDt;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}
