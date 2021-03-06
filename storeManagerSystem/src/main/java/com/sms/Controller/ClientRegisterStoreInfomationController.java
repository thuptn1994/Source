package com.sms.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.sms.common.ClientSMSCommons;
import com.sms.common.Status;
import com.sms.common.SystemCommon;
import com.sms.common.SystemURL;
import com.sms.form.CategoryForm;
import com.sms.form.RegisterStoreInfomationForm;
import com.sms.form.RegisterUserForm;
import com.sms.impl.ClientStoreOwnerImpl;
import com.sms.input.RegisterUserInputBean;
import com.sms.input.StoreOwnerInputBean;
import com.sms.models.ResultObject;

@SuppressWarnings("unchecked")
@Controller
public class ClientRegisterStoreInfomationController {
	
	public static final String REGISTER_STORE_INFOMATION = "registerStoreInfomation";
	
	/**
	 * Mean: init
	 * @param form
	 * @return
	 */
	@RequestMapping(value="/RegisterStoreInfomation/init", method=RequestMethod.GET)
	public String init(@ModelAttribute("registerStoreInfomation") RegisterStoreInfomationForm form, ModelMap modelMap){
		
		form.getLstCategory().put("01", "Thoi trang");
		form.getLstCategory().put("02", "Dien thoai");
		
		return REGISTER_STORE_INFOMATION;
	}
	
	
	/**
	 * Mean: insert
	 * @param form
	 * @return
	 */
	@RequestMapping(value="/RegisterStoreInfomation/insert", method=RequestMethod.POST)
	public String insert(@ModelAttribute("registerStoreInfomation") RegisterStoreInfomationForm form,HttpSession session){
		RestTemplate restTemplate = new RestTemplate();
		
		RegisterUserForm registerUserForm = (RegisterUserForm) ClientSMSCommons.getAttribute(session, "registerUserForm");
		
		RegisterUserInputBean  registerUserInputBean = new RegisterUserInputBean();
		registerUserInputBean.setFullName(registerUserForm.getFullName());
		registerUserInputBean.setPassword(registerUserForm.getPassword());
		registerUserInputBean.setUsername(registerUserForm.getUsername());
		registerUserInputBean.setTelephoneStoreOwner(registerUserForm.getTelephone());
		registerUserInputBean.setAddressStoreOwner(registerUserForm.getAddress());
		registerUserInputBean.setEmailStoreOwner(registerUserForm.getEmail());
		registerUserInputBean.setRole(SystemCommon.USER);
		// 
		registerUserInputBean.setAddressStore(form.getAddress());
		registerUserInputBean.setCategoryStore(form.getCategoryed());
		registerUserInputBean.setStoreName(form.getStoreName());
		registerUserInputBean.setTelephoneStoreOwner(form.getTelephone());
		registerUserInputBean.setEmailStore(form.getEmail());
		registerUserInputBean.setDomain(form.getDomain());
		
		// excute
		ResultObject result = restTemplate.postForObject(SystemURL.REGISTERUSER_INSERT, registerUserInputBean, ResultObject.class);
		
		
		if(result.getStatus() == Status.ERROR){
			System.out.println("Error");
			
		}else if(result.getStatus() == Status.SUCCESS){
			System.out.println("Success");
		}
		
		return REGISTER_STORE_INFOMATION;
	}
	
	
	
}
