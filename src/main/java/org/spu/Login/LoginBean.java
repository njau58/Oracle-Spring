package org.spu.Login;

import java.math.BigDecimal;

public class LoginBean {

	//comit
	private BigDecimal login_id;
	private String login_name;
	private String login_email;
	private String login_password;
	private String login_rank;
	
	public BigDecimal getLogin_id() {
		return login_id;
	}

	public void setLogin_id(BigDecimal login_id) {
		this.login_id = login_id;
	}
	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public void setLogin_email(String login_email ) {
		this.login_email = login_email;
	}

	public String getLogin_email() {
		return login_email;
	}
	public void setLogin_password(String login_password ) {
		this.login_password = login_password;
	}

	public String getLogin_password() {
		return login_password;
	}
	public void setLogin_rank(String login_rank ) {
		this.login_rank = login_rank;
	}

	public String getLogin_rank() {
		return login_rank;
	}


}