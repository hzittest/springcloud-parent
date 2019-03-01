package com.hzit.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//需要rest传递  一定需要序列化
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true) // 支持链式写法
public class Dept implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long deptno;
	private String dname;
	private String loc;

	// 测试 链式写法
	public Dept auto(String name) {
		Dept dept = new Dept().setDname(name).setLoc("深圳华美居");
		return dept;
	}

}
