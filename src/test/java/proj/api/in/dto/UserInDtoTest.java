package proj.api.in.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import proj.api.entities.Department;
import proj.api.entities.Designation;
import proj.api.entities.Reimburse;
import proj.api.entities.User;
import proj.api.in.dto.UserInDto;

@ExtendWith(MockitoExtension.class)
public class UserInDtoTest {

	@Test
	@DisplayName("UserInDTO getter and setter")
	public void testUserGetterAndSetter() {
		UserInDto user = new UserInDto();
		
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
		
		String password = "Aayush@123";
		user.setPassword(password);
		assertNotNull(user.getPassword());
		assertEquals(password, user.getPassword());
		
		String contact = "9826921884";
		user.setContact(contact);
		assertNotNull(user.getContact());
		assertEquals(contact, user.getContact());
		
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
	}
	
	@Test
	@DisplayName("UserInDTO toString method test")
	public void toStringTest() {
		UserInDto user = new UserInDto();
		user.setName("Aayush");
		user.setEmail("aayush@nucleusTeq.com");
		user.setPassword("Aayush@123");
		user.setRole(Designation.EMPLOYEE);
		user.setContact("9826921884");
		user.setManagerId(123);
		user.setSecretAnswer("AayushTiwari");
		Date date = new Date();
		String newDate = date.toString();
		user.setJoiningDate(date);
		user.setDepartment(null);
		String expected = "UserInDto [name=Aayush, email=aayush@nucleusTeq.com, password=Aayush@123, role=EMPLOYEE, contact=9826921884, managerId=123, secretAnswer=AayushTiwari, joiningDate="+newDate+", department=null]";
		
		assertEquals(expected, user.toString());
		String notExpected = "Not expected to string";
		assertNotEquals(notExpected, user.toString());
	}

	@Test
  @DisplayName("User In DTO hashCode and equals method test")
  public void hashCodeAndEqualsTest() {
		Date date = new Date();
  	DepartmentInDto department = new DepartmentInDto(101, "HR");
  	UserInDto user1 = new UserInDto("Test", "test@nucleusTeq.com",
    "Test@123", Designation.EMPLOYEE, "9999999999", 1, "Secret answer",
    date, department);
  	
  	assertEquals(user1, user1);
  	assertEquals(user1.hashCode(), user1.hashCode());
  	
  	assertNotEquals(user1, new User());
  	assertNotEquals(user1, new Object());
  	
  	assertNotNull(user1);
  	assertNotNull(user1.hashCode());
  	assertFalse(user1.equals(null));
  	
  	UserInDto user2 = new UserInDto("Test", "test@nucleusTeq.com",
    "Test@123", Designation.EMPLOYEE, "9999999999", 1, "Secret answer",
    date, department);
  	
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
  	
  	DepartmentInDto newDepartment = new DepartmentInDto();
  	user2.setDepartment(newDepartment);
  	assertFalse(user1.equals(user2));
  }
}
