package proj.api.in.dto;

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
import proj.api.entities.User;
import proj.api.in.dto.DepartmentInDto;

@ExtendWith(MockitoExtension.class)
public class DepartmentInDtoTest {

	@Test
  @DisplayName("DepartmentInDTO getter and setter test")
  public void getterAndSetter() {
  	DepartmentInDto department = new DepartmentInDto();
  	int id = 201;
  	department.setDepartmentId(id);
  	assertNotNull(department.getDepartmentId());
  	assertEquals(id, department.getDepartmentId());
  	
  	String departmentName = "HR";
  	department.setDepartmentName(departmentName);
  	assertNotNull(department.getDepartmentName());
  	assertEquals(departmentName, department.getDepartmentName());
  }
	
	@Test
  @DisplayName("Department in DTO toString method test")
  public void toStringTest() {
		String expected = "DepartmentInDto [departmentId=201, departmentName=HR]";
  	DepartmentInDto department = new DepartmentInDto();
  	department.setDepartmentId(201);
  	department.setDepartmentName("HR");
  	
  	assertEquals(expected, department.toString());
  }
	
	@Test
  @DisplayName("Department In DTO hashCode and equals method test")
  public void hashCodeAndEqualsTest() {
		
		DepartmentInDto department1 = new DepartmentInDto(101, "Finance");
  	assertEquals(department1, department1);
  	assertEquals(department1.hashCode(), department1.hashCode());
  	
  	assertNotEquals(department1, new Department());
  	assertNotEquals(department1, new Object());
  	
  	assertNotNull(department1);
  	assertNotNull(department1.hashCode());
  	assertFalse(department1.equals(null));
  	
  	DepartmentInDto department2 = new DepartmentInDto(101, "Finance");
  	assertEquals(department1, department2);
  	assertEquals(department1.hashCode(), department2.hashCode());
  	
  	department2.setDepartmentName("HR");
  	assertFalse(department1.equals(department2));
  	
  	department2.setDepartmentId(10000);
  	assertFalse(department1.equals(department2));
  }
}
