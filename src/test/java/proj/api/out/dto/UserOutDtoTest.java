package proj.api.out.dto;

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
import proj.api.out.dto.UserOutDto;

@ExtendWith(MockitoExtension.class)
public class UserOutDtoTest {


	@Test
	@DisplayName("UserOutDTO getter and setter")
	public void testUserGetterAndSetter() {
		UserOutDto user = new UserOutDto();
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
		
		int managerId = 111;
		user.setManagerId(managerId);
		assertNotNull(user.getManagerId());
		assertEquals(managerId, user.getManagerId());
		
		String managerName = "Manager";
		user.setManagerName(managerName);
		assertNotNull(user.getManagerName());
		assertEquals(managerName, user.getManagerName());
		
		Date date = new Date();
		user.setJoiningDate(date);
		assertNotNull(user.getJoiningDate());
		assertEquals(date, user.getJoiningDate());	
		
		DepartmentOutDto department = new DepartmentOutDto();
		department.setDepartmentId(201);
		department.setDepartmentName("HR");
		user.setDepartment(department);
		assertNotNull(user.getDepartment());
		assertEquals(department, user.getDepartment());
	}
	
	@Test
	@DisplayName("UserInDTO toString method test")
	public void toStringTest() {
		UserOutDto user = new UserOutDto();
		user.setUserId(101);
		user.setName("Aayush");
		user.setEmail("aayush@nucleusTeq.com");
		user.setRole(Designation.EMPLOYEE);
		user.setContact("9826921884");
		user.setManagerId(123);
		Date date = new Date();
		String newDate = date.toString();
		user.setJoiningDate(date);
		user.setManagerName("Manager");
		user.setDepartment(null);
		String expected = "UserOutDto [userId=101, name=Aayush, email=aayush@nucleusTeq.com, role=EMPLOYEE, contact=9826921884, managerId=123, joiningDate="+newDate+", department=null, managerName=Manager]";
		
		assertEquals(expected, user.toString());
		String notExpected = "Not expected to string";
		assertNotEquals(notExpected, user.toString());
	}

	@Test
  @DisplayName("User Out DTO hashCode and equals method test")
  public void hashCodeAndEqualsTest() {
    Date date1 = new Date();
  	DepartmentOutDto department = new DepartmentOutDto(101, "HR");
  	UserOutDto user1 = new UserOutDto(501, "Test", "test@nucleusTeq.com"
    , Designation.EMPLOYEE, "1234567890", 11, date1, "Test",department);
  	
  	assertEquals(user1, user1);
  	assertEquals(user1.hashCode(), user1.hashCode());
  	
  	assertNotEquals(user1, new User());
  	assertNotEquals(user1, new Object());
  	
  	assertNotNull(user1);
  	assertNotNull(user1.hashCode());
  	assertFalse(user1.equals(null));
  	
  	UserOutDto user2 = new UserOutDto(501, "Test", "test@nucleusTeq.com"
  	    , Designation.EMPLOYEE, "1234567890", 11, date1, "Test", department);
  	
  	user2.setUserId(789);
  	assertFalse(user1.equals(user2));
  	
  	user2.setName("Aayush");
  	assertFalse(user1.equals(user2));
  	
  	user2.setEmail("test2@nucleusTeq.com");
  	assertFalse(user1.equals(user2));
  	
  	user2.setRole(Designation.ADMIN);
  	assertFalse(user1.equals(user2));
  	
  	user2.setContact("9876543210");
  	assertFalse(user1.equals(user2));
  	
  	user2.setManagerId(20);
  	assertFalse(user1.equals(user2));
  	
  	user2.setManagerName("Manager");
  	assertFalse(user1.equals(user2));
  	
  	Date date2 = new Date();
  	user2.setJoiningDate(date2);
  	assertFalse(user1.equals(user2));
  }
}
