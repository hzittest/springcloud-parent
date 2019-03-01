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
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

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
	@HystrixCommand(fallbackMethod = "nullDeptMethod")
	public Dept get(@PathVariable(value = "deptno") Long deptno) {

		// 模拟异常 当dept为空的时候 主动的抛出异常 throws throw try...catch...finally
		// throws:不想处理异常，主动向上抛出异常，谁调用谁处理 (定义方法)
		// throw:主动制造异常
		Dept dept = deptService.get(deptno);
		if (dept == null) {
			throw new RuntimeException();// 主动抛出 运行时候异常
		}
		return dept;
	}

	public Dept nullDeptMethod(@PathVariable(value = "deptno") Long deptno) {

		System.out.println("--------->" + deptno + ":没有找到该数据....");

		// 断路器给调用返回一个符合预期的，可处理的FallBack 500L:表示long类似
		Dept dept = new Dept().setDeptno(500L).setDname(deptno + ":没有找到该部门编号对应的值").setLoc("no data...hystris");
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
		List<String> list = discoveryClient.getServices(); // 获取所有的服务
		System.out.println(list + "--------->");
		List<ServiceInstance> insList = discoveryClient.getInstances("SPRINGCLOUD-DEPT");
		for (ServiceInstance si : insList) {
			System.out.println(si.getHost() + "," + si.getServiceId() + "," + si.getPort() + "," + si.getUri() + ","
					+ si.getMetadata());
		}
		return this.discoveryClient;
	}

}
