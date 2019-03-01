package com.hzit.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hzit.bean.Dept;

import feign.hystrix.FallbackFactory;

@Component
public class DeptClientServiceFallbackFactoy implements FallbackFactory<DeptClientService> {

	/**
	 * fallbackMethod:方法
	 */
	@Override
	public DeptClientService create(Throwable cause) {
		// TODO Auto-generated method stub
		return new DeptClientService() {

			@Override
			public List<Dept> list() {
				return null;
			}

			@Override
			public Dept get(Long deptno) {
				return new Dept().setDeptno(400L).setDname("服务降级，请8点之后再来访问").setLoc("no data...");
			}

			@Override
			public boolean add(Dept dept) {
				return false;
			}
		};
	}

}
