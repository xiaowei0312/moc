package com.sram.entity;
/** 
 * <p>功能:题目解析报告实体</p> 
 * @author 杨楷
 * @date 2015-3-2 上午09:36:59
 */
public class QuestionUploadAnalyseReport {
	private String fileName;
	private String sheet;
	public String msg;
	private int ok;
	private int fail;
	private boolean isOk;
	public boolean isOk() {
		return isOk;
	}
	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getSheet() {
		return sheet;
	}
	public void setSheet(String sheet) {
		this.sheet = sheet;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getOk() {
		return ok;
	}
	public void setOk(int ok) {
		this.ok = ok;
	}
	public int getFail() {
		return fail;
	}
	public void setFail(int fail) {
		this.fail = fail;
	}
	
	@Override
	public String toString() {
		return this.fileName+"\t"+this.msg+"\t"+this.sheet+"\t"+this.ok+"\t"+this.fail;
	}
	
}
