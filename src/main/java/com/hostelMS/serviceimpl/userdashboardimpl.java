/**
 * HOSTEL   MANAGEMENT    SYSTEM
 * ->END USER
 * AND PRINT DATA OF ONE OR ALL USER USING LOGGER, DELETE USER AND ROOM USING DATA ACCESS OBJECT AND HQL 
 * CREATING AND USING GLOBAL EXCEPTION
 * ILLUSTRATING OBJECT RELATION MAPPING IN ENTITY USING HIBERNATE
 * ONE ROOM CAN HAVE MANY USER
 * By Renu
 */

package com.hostelMS.serviceimpl;

import java.util.Scanner;


import com.hostelMS.dao.userDao;
import com.hostelMS.daoimpl.userDaoimpl;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.User;
import com.hostelMS.service.userdashboard;

import org.apache.log4j.Logger;

public class userdashboardimpl implements userdashboard{

	static Logger log=Logger.getLogger(userdashboardimpl.class);
	
	// CREATING SCANNER OBJECT
	static Scanner scan=new Scanner(System.in);
	
	// CREATING USER DASHBOARD IMPL OBJECT
	static userdashboardimpl udimpl=new userdashboardimpl();
	
	// CREATING USER DAO OBJECT
	static userDao udao = new userDaoimpl();
	static int userId;
	
	
	// METHOD 1
	// METHOD TO IMPLEMENT DASHBOARD
	// GIVE USER CHOICE TO PERFORM DIFFERENT ACTION
	@Override
	public void dashboard(int uId) throws GlobalException {
		// TODO Auto-generated method stub
		log.info("\n\nUSER DASHBOARD\n");
		int choice=0;
		userId=uId;
		
		// CREATING LOOP
		while(choice<6) {
			
			// USER CAN PERFORM THESE ACTIONS
			log.info("\nPress 1 - View Your Room\nPress 2 - Due Amount \nPress 3 - View  Your Profile\nPress 4 - Change Contact Number \nPress 5 - Change password \nPress 6 - Exit");
			
			choice=scan.nextInt();
			
			switch(choice) {
		
				case 1->udimpl.viewRoom();
				
				case 2->udimpl.viewDueAmount();
				
				case 3->udimpl.viewProfile();
				
				case 4->udimpl.changePhonenumber();
				
				case 5->udimpl.changePassword();
			}
		}
		
	}

	
	// METHOD 2
	// TO SHOW ROOM DETAILS OF USER
	@Override
	public void viewRoom() {
		// TODO Auto-generated method stub
		User u= udao.userRoom(userId);// FETCHING USER ROOM DETAILS
		log.info("Hello "+u.getUserName()+"\nRoom number : "+u.getUserRoom().getRoomId()+"\nRoom Name : "+u.getUserRoom().getRoomName()+"\nRoom Type : "+u.getUserRoom().getRoomType());
	}

	// METHOD 3
	// TO SHOW DUE AMOUNT OF USER
	@Override
	public void viewDueAmount() throws GlobalException {
		// TODO Auto-generated method stub
		int amount= udao.userDueAmount(userId);// FETCH DUE AMOUNT DETAILS
		log.info("Fees Due : "+amount);
	}

	// METHOD 4
	// FETCH AND SHOW PROFILE DETIALS TO USER
	@Override
	public void viewProfile() {
		// TODO Auto-generated method stub
		User u= udao.userProfile(userId);// FETCHING USER DETAILS
		log.info(u);
	}

	
	// METHOD 5
	// TO CHANGE CONTACT NUMBER
	@Override
	public void changePhonenumber() throws GlobalException {
		// TODO Auto-generated method stub
		log.info("Enter New Contact number");
		String contact = scan.next();
		int res=udao.changeContact(userId, contact);// UPDATING CONTACT NUMBER
		if(res==1) {
			log.info("Phone number updated");
		}
	}

	
	// METHOD 6
	// TO CHANGE PASSWORD
	@Override
	public void changePassword() {
		// TODO Auto-generated method stub
		log.info("Enter Current Password");
		String oldpswrd = scan.next();
		log.info("Enter New Password");
		String newpswd = scan.next();
		int res = 0 ;
		try {
			res = udao.changePassWord(userId, oldpswrd, newpswd);
		} catch (GlobalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// CHANGING ACCOUNT PASSWORD
		if(res==1) {
			log.info("password changed");
		}
	}

	
}
	
	


