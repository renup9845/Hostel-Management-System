package com.hostelMS.dao;

import com.hostelMS.exception.GlobalException;

import com.hostelMS.model.User;

public interface userDao {
	

	public User userRoom(int uId);
	public int userDueAmount(int uId) throws GlobalException;
	public User userProfile(int uId);
	public int changeContact(int uId, String newContact) throws GlobalException;
	public int changePassWord(int uId, String oldPswrd, String newPswrd) throws GlobalException;

}
