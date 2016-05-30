/**
 * 
 */
package com.ttgis.education.entity;

/**
 * @author 董再兴
 * Controller 返回消息包装
 */
public class Msg {

	/**
	 * 成功状态
	 */
	public static final String SUCCESS = "success";
	
	/**
	 * 失败状态
	 */
	public static final String ERROR = "error";
	
	public Msg() {}
	
	/**
	 * Controller 返回消息包装构造方法
	 * @param head		消息头
	 * @param msgBody	消息体
	 * @param status	消息状态
	 */
	public Msg(String head, Object msgBody, String status) {
		super();
		this.head = head;
		this.msgBody = msgBody;
		this.status = status;
	}

	/**
	 * 消息头
	 */
	String head;
	
	/**
	 * 消息体
	 */
	Object msgBody;
	
	/**
	 * 消息状态
	 * success  处理成功返回
	 * error	处理失败返回
	 */
	String status;

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public Object getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(Object msgBody) {
		this.msgBody = msgBody;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
