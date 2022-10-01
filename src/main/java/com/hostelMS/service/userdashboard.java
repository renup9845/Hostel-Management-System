package com.hostelMS.service;

import com.hostelMS.exception.GlobalException;

public interface userdashboard {
	
	public void dashboard(int uId) throws GlobalException;
	public void viewRoom();
	public void viewDueAmount() throws GlobalException;
	public void viewProfile();
	public void changePhonenumber() throws GlobalException;
	public void changePassword() throws GlobalException;

}
