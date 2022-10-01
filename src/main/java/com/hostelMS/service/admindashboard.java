package com.hostelMS.service;

import com.hostelMS.exception.GlobalException;

public interface admindashboard {
	
	public void dashboard() throws GlobalException;
	public void fetchAllRooms();
	public void fetchAllUsers();
	public void createRoom() throws GlobalException;
	public void allotRoom() throws GlobalException;
	public void deleteUser() throws GlobalException;
	public void userInARoom();
	public void generateRent() throws GlobalException;
	public void rentPayment() throws GlobalException;
	public void setUserRole();
	public void viewUserProfile() throws GlobalException;

}
