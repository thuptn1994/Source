
package com.sms.Controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sms.common.Status;
import com.sms.impl.LoginImpl;
import com.sms.models.ResultObject;
import com.sms.models.User;

@Path("/login")
public class LoginController {

	@Path("/checklogin")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public ResultObject getObject(User resquest) {
		ResultObject response = new ResultObject(Status.ERROR);
		User user = LoginImpl.loginImpl.checkLoginIml(resquest);
		if (null != user) {
			response.setObj(user);
			response.setStatus(Status.SUCCESS);
		} else {
			response.setStatus(Status.ERROR);
		}
		return response;
	}

}
