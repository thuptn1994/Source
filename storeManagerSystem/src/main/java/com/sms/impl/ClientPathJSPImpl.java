package com.sms.impl;

import org.springframework.web.client.RestTemplate;

import com.sms.common.SystemURL;
import com.sms.input.PathJSPInputBean;
import com.sms.models.ResultObject;

public class ClientPathJSPImpl {
	RestTemplate rest = new RestTemplate();
	public static ClientPathJSPImpl intances = new ClientPathJSPImpl();

	/**
	 * get all category store
	 * @return
	 */
	public ResultObject getPathJSP() {

		ResultObject result = rest.getForObject(SystemURL.PATHJSP_GETPATHJSP, ResultObject.class);
		return result;
	}

	/**
	 * get one category store by id
	 * @return
	 */
	public ResultObject getPathJSPById(PathJSPInputBean request) {

		ResultObject result = rest.postForObject(SystemURL.PATHJSP_GETPATHJSPBYID,request, ResultObject.class);
		return result;
	}
	
	
}
