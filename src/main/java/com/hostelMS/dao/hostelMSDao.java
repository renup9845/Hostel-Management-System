package com.hostelMS.dao;

import com.hostelMS.exception.GlobalException;

import com.hostelMS.model.User;

public interface hostelMSDao {
	
	public int Registration(User u) throws GlobalException;
	public User Login(String UserName,String password) throws GlobalException;
	
	}
