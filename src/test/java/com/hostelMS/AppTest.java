/**
 * HOSTEL   MANAGEMENT    SYSTEM
 * Illustrating UNIT TESTING OF HOSTEL MANAGEMENT SYSTEM
 * THERE ARE TWO TYPES OF USER
 * ->ADMIN
 * ->END USER
 * UNIT TESTING OF PRINT DATA OF ONE OR ALL USER USING LOGGER, DELETE USER AND ROOM USING DATA ACCESS OBJECT AND HQL 
 * TESTING OF METHOD OF HOTELMS, USER AND ADMIN DASHBOARD METHOD USING JUNIT TEST CASES
 * USED POSITIVE AND NEGATIVE TEST CASES TO PERFORM TESTING OF FOLLOWING METHOD 
 * By Renu
 */


package com.hostelMS;

import com.hostelMS.config.HibernateUtil;
import com.hostelMS.dao.adminDao;
import com.hostelMS.dao.hostelMSDao;
import com.hostelMS.dao.userDao;
import com.hostelMS.daoimpl.adminDaoimpl;
import com.hostelMS.daoimpl.hostelMSDaoimpl;
import com.hostelMS.daoimpl.userDaoimpl;
import com.hostelMS.exception.GlobalException;
import com.hostelMS.model.Room;
import com.hostelMS.model.User;

import org.hibernate.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppTest{
	
	// TEST 1
	@Test
	@DisplayName("REGISTRATION TESTING")
	void registrationTest() {
		
		// CREATING DAO OBJECT OF HOSTEL MS
		hostelMSDao dao = new hostelMSDaoimpl();
		// CREATING USER OBJECT THAT ALREADY EXIST IN DATABASE
		User a = new User();
		a.setFirstName("RenuR");
		a.setLastName("PrajapatiP");
		a.setUserName("RenuRRR");
		a.setUserPassword("r12345@");
		a.setUserContact("9643025686");
		a.setUserAddress("Delhi");
		
		// CREATING NEW USER OBJECT
		User b = new User();
		b.setFirstName("TanujhaT");
		b.setLastName("BisthB");
		b.setUserName("TanujhaTTT");
		b.setUserPassword("T12345@Kk");
		b.setUserContact("8800546951");
		b.setUserAddress("Haryana");
		
		assertAll(
				
				// POSITIVE TEST CASE 
				// NEW USER IS THE TEST CASE
				// ADD NEW USER IN DATABASE
				// EXPECTING POSITIVE VALUE
				()->assertEquals(1,dao.Registration(b)),
				
				// NEGATIVE TEST CASE
				// TRYING TO ADD USER THAT ALREADY EXIST
				// EXPECTING AN EXCEPTION
				()->assertThrows(GlobalException.class,()->dao.Registration(a))
		);
	}
	
	// TEST 2
	@Test
	@DisplayName("LOGIN TESTING")
	void loginTest() throws GlobalException {
		
		// CREATING DAO OBJECT OF HOSTEL MS
		hostelMSDao dao = new hostelMSDaoimpl();
		
		// CREAYTING SESSION OBJECT
		Session ses = HibernateUtil.getSession();
		
		// CREATING TWO USER OBJECT
		// FETCHING USER FROM DATA USING SESSION OBJECT
		User u = ses.get(User.class, 1);
		
		// FETCHING SAME USER FROM DATABASE USING DAO OBJECT AND PRIMARY KEY 
		User a = dao.Login("RenuR", "R12345@");
		
		assertAll(
				// POSITIVE TEST CASE
				// COMPARING IF BOTH USER OBJECT IS SAME
				// EXPECTING SAME RESULT
				()->assertEquals(u.toString(),a.toString()),
				
				// NEGATIVE TEST CASE
				// GIVING WRONG USERNAME AND PASSWORD IN LOGIN METHOD
				// EXPECTING AN EXCEPTION
				()->assertThrows(GlobalException.class,()->dao.Login("RenuPrajapati", "R123456"))
		);
	}

	// TEST 3
	// TEST 3
	@Test
	@DisplayName("CREATE ROOM TESTING")
	void addRoomTest(){
		
		// CREATING ADMIN DAO OBJECT
		adminDao dao = new adminDaoimpl();
		
		// CREATING ROOM OBJECT
		// ROOM ALREADY EXIST IN DATABASE
		Room r = new Room();
		r.setRoomId(100);
		r.setRoomName("Room");
		r.setRoomType("Ac");
		
		// CREATING ROOM OBJECT
		// NEW ROOM OBJECR
		Room a = new Room();
		a.setRoomId(109);
		a.setRoomName("Room P");
		a.setRoomType("Non - AC");
		
		assertAll(
				
				// POSITIVE TEST CASE
				// ADDING NEW ROOM TO DATABASE
				// EXPECTING POSITIVE RESULT
				()->assertEquals(1,dao.createRoom(a)),
				
				// NEGATIVE TEST CASE
				// TRYING TO ADD A ROOM THA ALREADY EXIST
				// EXPECTING AN EXCEPTION
				()->assertThrows(GlobalException.class,()->dao.createRoom(r))
		);
	}

	// TEST 4
	// TEST 4
	@Test
	@DisplayName("Allot Room Testing")
	void allotRoomTest() {
		
		// CREATING ADMIN DAO OBJECT
		adminDao dao = new adminDaoimpl();
	
		assertAll(
				// POSITIVE TEST CASE
				// ALLOTING A ROOM TO A USER FROM DATABASE
				// EXPECTING POSITIVE RESULT
				()->assertEquals(1,dao.allotRoom(2, 100)),
				
				// NEGATIVE TEST CASE
				// GIVIING WRONG ROOM ID
				// GIVING WRONG USER ID IN METHOD
				// EXPECTING AN EXCEPTION
				()->assertThrows(GlobalException.class,()->dao.allotRoom(500, 500))
		);
		
	}

	// TEST 5
	// TEST 5
	@Test
	@DisplayName("DELETE USER TESTING")
	void deleteUserTest() {
		
		// CREATING ADMIN DAO OBJECT
		adminDao dao = new adminDaoimpl();
		
		assertAll(
				
				// POSTIVE TEST CASE
				// GIVING USER ID TO DELETE FROM DATABSE
				// EXPECTING TO DELETE USER FROM DATABASE SUCCESSFULLY
				()->assertEquals(1,dao.deleteUser(10)),
				
				// NEGATIVE TEST CASE
				// GIVING USER ID THAT DOES NOT EXIST IN DATABASE
				// EXPECTING AN EXCEPTION
				()->assertThrows(GlobalException.class,()->dao.deleteUser(500))
				);
	}

	// TEST 6
	// TEST 6
	@Test
	@DisplayName("SET RENT TESTING")
	void setRentTest() {
		
		// CREATING ADMIN DAO OBJECT
		adminDao dao = new adminDaoimpl();
		
		assertAll(
				// POSITIVE TEST CASE
				// GIVING USER ID 4 AND AMMOUNT 5000
				// EXPECTING GENERATED RENT OF 5000 FOR USER 4
				()-> assertEquals(1,dao.generateRent(4,5000)),
				
				// NEGATIVE TEST CASE
				// GIVING INCORRECT USER ID
				// EXPECTING AN EXCEPTION
				()->assertThrows(GlobalException.class,()->dao.generateRent(500,-600))
		);															
	}
	
	// TEST 7
	// TEST 7
	@Test
	@DisplayName("RENT PAYMENT TESTING")
	void payRentTest() {
		
		adminDao dao = new adminDaoimpl();
		
		assertAll(
				// POSITIVE TEST CASE
				// GIVING 1000 AMOUNT AS RENT PAYMENT
				// EXEPCTING AMOUNT 9000  LEFT TO PAY
				()->assertEquals(9000,dao.rentPayment(5, 1000)),
				
				// NEGATIVE TEST CASE
				// TESTING FOR INCORRECT USER ID
				// EXCEPTING AN EXCEPTION
				()->assertThrows(GlobalException.class,()->dao.rentPayment(500, 1000))
		);
	}

	// TEST 8
	// TEST 8
	@Test
	@DisplayName("FETCH USER TESTING")
	void viewUserTest() throws GlobalException {
		
		// CREATING ADMIN DAO OBJECT
		adminDao dao = new adminDaoimpl();
		// CREATING SESSSION OBJECT
		Session ses = HibernateUtil.getSession();
		// FETCHING USER DETAIL USING SESSION OBJECT
		User u = ses.get(User.class, 1);
		// FETCHING SAME USER USING FETCH USER PROFILR METHOD
		User u1 = dao.fetchUserProfile(1);
		assertAll(
				
				// POSTIVE TEST CASE
				// COMPARING BOTH USER THAT WE FETCHED EARLIER
				// EXPECTING THAT BOTH ARE EQUAL/SAME
				()->assertEquals(u.toString(),u1.toString()),
				
				// NEGATIVE TEST CASE
				// TESTING FETCH USER PROFILE METHOD FOR INCORRECT USER ID
				// EXCEPTING TO THROW AN EXCEPTION
				()->assertThrows(GlobalException.class,()->dao.fetchUserProfile(500))
		);
	}
	
	// TEST 9
	// TEST 9
	@Test
	@DisplayName("DUE RENT TESTING")
	void dueAmountTest() {
		
		// CREATING USER DAO METHOD
		userDao dao = new userDaoimpl();
		
		assertAll(
				// POSITIVE TEST CASE
				// FETCHING RENT BILL FOR USER 2
				// EXPECTING 10000 RENT AMOUNT FROM USER 2
				()->assertEquals(10000,dao.userDueAmount(2)),
				
				// NEGATIVE TEST CASE
				// TESTING USER DUE AMOUNT METHOD FOR WRONG USER ID
				// EXPECTING TO THROW AN EXCEPTION
				()->assertThrows(GlobalException.class,()->dao.userDueAmount(500))
		);
	}
	
	// TEST 10
	// TEST 10
	@Test
	@DisplayName("CHANGE CONTACT TESTING")
	void changeContactTest() {
		
		// CREATING USER DAO METHOD
		userDao dao = new userDaoimpl();
		
		assertAll(
				// POSITIVE TEST CASE
				// TESTING CHANGE CONTACT METHOD TO CHAGE CONTCT OF USER 2
				// EXPECTING TO CHANGE USER 2 CONTACT DETAIL IN DATABSE
				()->assertEquals(1,dao.changeContact(2, "2222222222")),
				
				// NEGATIVE TEST CASE
				// TESTING CHANGE CONTACT METHOD FOR WRONG USER ID
				// EXPECTING TO THROW AN EXCEPTION
				()->assertThrows(GlobalException.class,()->dao.changeContact(500, "2222222222"))
				);
	}
	
	// TEST 11
	// TEST 11
	@Test
	@DisplayName("CHANGE PASSWORD TESTING")
	void cahngePasswordTest() {
		
		userDao dao = new userDaoimpl();
		
		assertAll(
				()->assertEquals(1,dao.changePassWord(2,"Tan@123","Ren@1234#")),
				()->assertThrows(GlobalException.class,()->dao.changePassWord(4,"Tan@123","Ren@1234#"))
				);
	}
	
}
	
	
	
	
	
  
