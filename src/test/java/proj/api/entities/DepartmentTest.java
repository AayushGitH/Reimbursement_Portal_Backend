package proj.api.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import proj.api.entities.Department;

@ExtendWith(MockitoExtension.class)
public class DepartmentTest {

	@Test
  @DisplayName("Department entity getter and setter test")
  public void getterAndSetter() {
  	Department department = new Department();
  	int id = 201;
  	department.setDepartmentId(id);
  	assertNotNull(department.getDepartmentId());
  	assertEquals(id, department.getDepartmentId());
  	
  	String departmentName = "HR";
  	department.setDepartmentName(departmentName);
  	assertNotNull(department.getDepartmentName());
  	assertEquals(departmentName, department.getDepartmentName());
  	
  	List<User> userslist = new ArrayList<User>();
  	department.setUsers(userslist);
  	assertNotNull(department.getUsers());
  	assertEquals(userslist, department.getUsers());
  }
	
	@Test
  @DisplayName("Department entity toString method test")
  public void toStringTest() {
		String expected = "Department [departmentId=201, departmentName=HR, users=null]";
  	Department department = new Department();
  	department.setDepartmentId(201);
  	department.setDepartmentName("HR");
  	department.setUsers(null);
  	
  	assertEquals(expected, department.toString());
  }
	
	@Test
  @DisplayName("Department entity hashCode and equals method test")
  public void hashCodeAndEqualsTest() {
		Department department1 = new Department(101, "Marketing", null);
  	assertEquals(department1, department1);
  	assertEquals(department1.hashCode(), department1.hashCode());
  	
  	assertNotEquals(department1, new Department());
  	assertNotEquals(department1, new Object());
  	
  	assertNotNull(department1);
  	assertNotNull(department1.hashCode());
  	assertFalse(department1.equals(null));
  	
  	Department department2 = new Department(101, "Marketing", null);
  	assertEquals(department1, department2);
  	assertEquals(department1.hashCode(), department2.hashCode());
  	
  	department2.setDepartmentName("HR");
  	assertFalse(department1.equals(department2));
  	
  	department2.setDepartmentId(10000);
  	assertFalse(department1.equals(department2));
  }
}
