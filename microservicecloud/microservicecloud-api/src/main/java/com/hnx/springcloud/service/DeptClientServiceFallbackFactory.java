package com.hnx.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hnx.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;

/**
 * 	修改microservicecloud-api工程， 根据已经有的DeptClientService接口新建一个实现了
 *	FallbackFactory接口的类DeptClientServiceFallbackFactory
 * 
 * @author dadedawei
 *
 */
@Component // 不要忘记添加，不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	@Override
	public DeptClientService create(Throwable throwable) {
		return new DeptClientService() {
			@Override
			public Dept get(long id) {
				return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭")
						.setDb_source("no this database in MySQL");
			}

			@Override
			public List<Dept> list() {
				return null;
			}

			@Override
			public boolean add(Dept dept) {
				return false;
			}
		};

	}

}