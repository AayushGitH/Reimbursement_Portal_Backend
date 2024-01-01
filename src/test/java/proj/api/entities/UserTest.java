package proj.api.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import proj.api.entities.User;

@ExtendWith(MockitoExtension.class)
public class UserTest {
	
	@Test
	@DisplayName("User getter and setter")
	public void testUserGetterAndSetter() {
		User user = new User();
		int id = 801;
		user.setUserId(id);
		assertNotNull(user.getUserId());
		assertEquals(id, user.getUserId());
		
		String name = "Aayush";
		user.setName(name);
		assertNotNull(user.getName());
		assertEquals(name, user.getName());
		
		String email = "aayush@nucleusTeq.com";
		user.setEmail(email);
		assertNotNull(user.getEmail());
		assertEquals(email, user.getEmail());
		
		user.setRole(Designation.EMPLOYEE);
		assertNotNull(user.getEmail());
		assertEquals(Designation.EMPLOYEE, user.getRole());
		
		String contact = "9826921884";
		user.setContact(contact);
		assertNotNull(user.getContact());
		assertEquals(contact, user.getContact());
		
		String password = "Aayush@123";
		user.setPassword(password);
		assertNotNull(user.getPassword());
		assertEquals(password, user.getPassword());
		
		int managerId = 111;
		user.setManagerId(managerId);
		assertNotNull(user.getManagerId());
		assertEquals(managerId, user.getManagerId());
		
		String secretAnswer = "Rocky";
		user.setSecretAnswer(secretAnswer);
		assertNotNull(user.getSecretAnswer());
		assertEquals(secretAnswer, user.getSecretAnswer());
		
		Date date = new Date();
		user.setJoiningDate(date);
		assertNotNull(user.getJoiningDate());
		assertEquals(date, user.getJoiningDate());	
		
		Department department = new Department();
		department.setDepartmentId(201);
		department.setDepartmentName("HR");
		user.setDepartment(department);
		assertNotNull(user.getDepartment());
		assertEquals(department, user.getDepartment());
		
		List<Reimburse> requests = new ArrayList<Reimburse>();
		user.setRequests(requests);
		assertNotNull(user.getRequests());
		assertEquals(requests, user.getRequests());
		
	}
	
	@Test
	@DisplayName("User toString method test")
	public void toStringTest() {
		User user = new User();
		user.setUserId(111);
		user.setName("Aayush");
		user.setEmail("aayush@nucleusTeq.com");
		user.setPassword("Aayush@123");
		user.setRole(Designation.EMPLOYEE);
		user.setContact("9826921884");
		user.setManagerId(123);
		user.setSecretAnswer("AayushTiwari");
		Date date = new Date();
		user.setJoiningDate(date);
		String newDate = date.toString();
		String expected = "User [userId=111, name=Aayush, email=aayush@nucleusTeq.com, password=Aayush@123, role=EMPLOYEE, contact=9826921884, managerId=123, secretAnswer=AayushTiwari, joiningDate="+newDate+"]";
		assertEquals(expected, user.toString());
		String notExpected = "Not expected to string";
		assertNotEquals(notExpected, user.toString());
	}
	
	@Test
  @DisplayName("User entity hashCode and equals method test")
  public void hashCodeAndEqualsTest() {
		List<Reimburse> list1 = new ArrayList<Reimburse>();
		Date date = new Date();
  	Department department = new Department(101, "HR", null);
  	User user1 = new User(501, "Test", "test@nucleusTeq.com",
    "Test@123", Designation.EMPLOYEE, "1234567890", 11,
    "Secret answer", date, department, list1);
  	
  	assertEquals(user1, user1);
  	assertEquals(user1.hashCode(), user1.hashCode());
  	
  	assertNotEquals(user1, new User());
  	assertNotEquals(user1, new Object());
  	
  	assertNotNull(user1);
  	assertNotNull(user1.hashCode());
  	assertFalse(user1.equals(null));
  	
  	List<Reimburse> list2 = new ArrayList<Reimburse>();
  	User user2 = new User(501, "Test", "test@nucleusTeq.com",
    "Test@123", Designation.EMPLOYEE, "1234567890", 11,
    "Secret answer", date, department, list2);
  	
  	user2.setUserId(789);
  	assertFalse(user1.equals(user2));
  	
  	user2.setName("Aayush");
  	assertFalse(user1.equals(user2));
  	
  	user2.setEmail("test2@nucleusTeq.com");
  	assertFalse(user1.equals(user2));
  	
  	user2.setPassword("Test@12345");
  	assertFalse(user1.equals(user2));
  	
  	user2.setRole(Designation.ADMIN);
  	assertFalse(user1.equals(user2));
  	
  	user2.setContact("9876543210");
  	assertFalse(user1.equals(user2));
  	
  	user2.setManagerId(20);
  	assertFalse(user1.equals(user2));
  	
  	user2.setSecretAnswer("Other answer");
  	assertFalse(user1.equals(user2));
  	
  	user2.setJoiningDate(date);
  	assertFalse(user1.equals(user2));
  	
  	Department newDepartment = new Department();
  	user2.setDepartment(newDepartment);
  	assertFalse(user1.equals(user2));
  	
  	List<Reimburse> list = new ArrayList<Reimburse>();
  	user2.setRequests(list);
  	assertFalse(user1.equals(user2));
  }
}
