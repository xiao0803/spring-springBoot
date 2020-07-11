package com.example.actuator.httptrace;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

/**
 * 自定义跟踪仓库类；目前还不完善（不知道怎样使用）
 * @author LENOVO
 *
 */
@Component
public class TestHttpTraceRepository implements HttpTraceRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestHttpTraceRepository.class);
	
	private static final List<HttpTrace> list = new ArrayList<HttpTrace>();

	@Override
	public List<HttpTrace> findAll() {
		return list;
	}

	@Override
	public void add(HttpTrace trace) {
		// send log to remote server or log center
		LOGGER.info("HttpTrace输出：" + JSON.toJSONString(trace));
		list.add(trace);
	}

}
