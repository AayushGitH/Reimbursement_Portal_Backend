package proj.api.out.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import proj.api.entities.Department;
import proj.api.in.dto.DepartmentInDto;
import proj.api.out.dto.DepartmentOutDto;

@ExtendWith(MockitoExtension.class)
public class DepartmentOutDtoTest {

	@Test
  @DisplayName("DepartmentOutDTO getter and setter test")
  public void getterAndSetter() {
  	DepartmentOutDto department = new DepartmentOutDto();
  	int id = 201;
  	department.setDepartmentId(id);
  	assertNotNull(department.getDepartmentId());
  	assertEquals(id, department.getDepartmentId());
//  	assertThat(department.getDepartmentId()).isNull();
  	
  	String departmentName = "HR";
  	department.setDepartmentName(departmentName);
  	assertNotNull(department.getDepartmentName());
  	assertEquals(departmentName, department.getDepartmentName());
  }
	
	@Test
  @DisplayName("Department out DTO toString method test")
  public void toStringTest() {
		String expected = "DepartmentOutDto [departmentId=201, departmentName=HR]";
  	DepartmentOutDto department = new DepartmentOutDto();
  	department.setDepartmentId(201);
  	department.setDepartmentName("HR");
  	
  	assertEquals(expected, department.toString());
  }
	
	@Test
  @DisplayName("Department Out DTO hashCode and equals method test")
  public void hashCodeAndEqualsTest() {
		
		DepartmentOutDto department1 = new DepartmentOutDto(101, "Finance");
  	assertEquals(department1, department1);
  	assertEquals(department1.hashCode(), department1.hashCode());
  	
  	assertNotEquals(department1, new Department());
  	assertNotEquals(department1, new Object());
  	
  	assertNotNull(department1);
  	assertNotNull(department1.hashCode());
  	assertFalse(department1.equals(null));
  	
  	DepartmentOutDto department2 = new DepartmentOutDto(101, "Finance");
  	assertEquals(department1, department2);
  	assertEquals(department1.hashCode(), department2.hashCode());
  	
  	department2.setDepartmentName("HR");
  	assertFalse(department1.equals(department2));
  	
  	department2.setDepartmentId(10000);
  	assertFalse(department1.equals(department2));
  }
}
