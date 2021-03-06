package com.hzit.dept.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hzit.bean.Dept;
import com.hzit.dept.service.DeptService;

@RestController
public class DeptController {

	@Autowired
	private DeptService deptService;

	@RequestMapping("/dept/list")
	public List<Dept> list() {
		List<Dept> list = deptService.list();
		return list;
	}

	@RequestMapping("/dept/get/{deptno}")
	public Dept get(@PathVariable(value = "deptno") Long deptno) {
		Dept dept = deptService.get(deptno);
		return dept;
	}

	// @RequestBody:将json格式的参数 自动转为java对象 和 @ResponseBody相反
	@RequestMapping("/dept/add")
	public boolean add(@RequestBody Dept dept) {
		boolean bool = deptService.add(dept);
		return bool;
	}

	@Autowired
	private DiscoveryClient discoveryClient;

	@ResponseBody
	@GetMapping("/provider/discovery")
	public Object discovery() {
		List<String> list = discoveryClient.getServices(); //获取所有的服务
		System.out.println(list+"--------->");
		List<ServiceInstance> insList = discoveryClient.getInstances("SPRINGCLOUD-DEPT");
		for (ServiceInstance si : insList) {
			System.out.println(si.getHost() + "," + si.getServiceId() + "," + si.getPort() + "," + si.getUri() + ","
					+ si.getMetadata());
		}
		return this.discoveryClient;
	}

}
