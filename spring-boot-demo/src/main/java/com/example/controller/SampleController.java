/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jiguang.msa.rest.common.MsaResp;

import com.example.common.ValidateAssert;
import com.example.common.enums.BizExceptionEnum;
import com.example.entity.UnTargetForm;

@RestController
public class SampleController {

	@RequestMapping("/welcome")
	public String hello() {
		return "Hello World";
	}
	
    @PostMapping(value = "/advertisers/unTarget/openOrClose")
    //@ResponseBody
    public MsaResp<Object> openOrClose(@RequestBody UnTargetForm form) {
    	System.out.println("------------入参输出111：" + form);
    	ValidateAssert.allNotNull(BizExceptionEnum.PARAM_VALID_FAIL, form);
    	System.out.println("------------执行完毕111");
        return null;
    }
    
	@RequestMapping("/sendRedirect")
	public void sendRedirect(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("------------重定向测试开始");
		try {
			response.sendRedirect("https://www.baidu.com");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
