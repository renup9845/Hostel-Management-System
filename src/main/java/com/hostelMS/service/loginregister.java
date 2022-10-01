package com.hostelMS.service;

import com.hostelMS.exception.GlobalException;

public interface loginregister {
	
	public void Register() throws GlobalException;
	public void Login() throws GlobalException;
}
