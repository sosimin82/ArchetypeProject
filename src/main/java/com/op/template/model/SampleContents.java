package com.op.template.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.ibatis.type.Alias;

/**
 * Redis 에 캐쉬하기 위해 Model Class는 Serializable 을 구현해야 함
 */
@Alias("sampleContents")
public class SampleContents implements Serializable {
	private static final long serialVersionUID = 172602401102437298L;

	private int seq;
	
	@NotNull @Size(min = 1)
	private String msg;

	public int getSeq() {
		return seq;
	}
	
	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder()
		.append("seq=[").append(seq).append("]").append(" ")
		.append("msg=[").append(msg).append("]");
		return sb.toString();
	}
}
