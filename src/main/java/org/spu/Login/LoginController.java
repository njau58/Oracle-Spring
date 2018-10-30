package org.spu.Login;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.spu.SendSms.*;

@RestController
@RequestMapping(value = "/hostel-management/login")
public class LoginController {

	@Autowired
	LoginDAO loginDAO;
	
	@Autowired
	SmsEngine SmsEngine ;

	

	@RequestMapping(method = RequestMethod.GET, value = "/fetchlogin")
	public List<LoginBean> fetchlogin() throws SQLException {

		return loginDAO.fetchlogin();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createlogin")
	public  List<LoginBean> createlogin(@RequestBody LoginBean loginBean) throws SQLException {

		return loginDAO.createlogin(loginBean.getLogin_id(),loginBean.getLogin_name(), loginBean.getLogin_email(),loginBean.getLogin_password(),loginBean.getLogin_rank());
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatelogin")
	public List<LoginBean> updatelogin(@RequestBody LoginBean loginBean) throws SQLException {

		return loginDAO.updatelogin(loginBean.getLogin_id(),loginBean.getLogin_name(),  loginBean.getLogin_email(),loginBean.getLogin_password(),loginBean.getLogin_rank());
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteLogin")
	public List<LoginBean> deletelogin(@RequestParam BigDecimal login_id) throws SQLException {

		return loginDAO.deletelogin(login_id);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/SendSms")
	public String sendSms (@RequestParam String recipients, String message ) throws SQLException {

		return SmsEngine.sendSms(recipients, message);
	}
}
