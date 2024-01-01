package proj.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import proj.api.controller.DepartmentController;
import proj.api.entities.Department;
import proj.api.in.dto.DepartmentInDto;
import proj.api.out.dto.DepartmentOutDto;
import proj.api.services.DepartmentService;

@WebMvcTest(controllers = DepartmentController.class)
@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {
  
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private DepartmentService departmentService;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  @Test
  @DisplayName("Save department api test")
  void saveDepartment() throws JsonProcessingException, Exception {
    // Arrange
//	final Department department = new Department(101, "Finance", null);
	final DepartmentInDto departmentInDto = new DepartmentInDto(101, "Finance");
	final DepartmentOutDto departmentOutDto = new DepartmentOutDto(101, "Finance");
	
	// Act
	when(departmentService.saveDepartment(departmentInDto)).thenReturn(departmentOutDto);
	final ResultActions response = mockMvc
      .perform(post("/department/create")
      .contentType(MediaType.APPLICATION_JSON)
      .content(objectMapper.writeValueAsString(departmentInDto)));
	response.andExpect(MockMvcResultMatchers.status().isCreated());
	
	// 
  }
  
  @Test
  @DisplayName("Get all departments api test")
  void getAllDepartments() throws JsonProcessingException, Exception {
    // Arrange
	final DepartmentOutDto departmentOutDto1 = new DepartmentOutDto(101, "Finance");
	final DepartmentOutDto departmentOutDto2 = new DepartmentOutDto(202, "Marketing");
	final List<DepartmentOutDto> departmentlist = Arrays.asList(departmentOutDto1, departmentOutDto2);
	
	// Act
	when(departmentService.getAllDepartments()).thenReturn(departmentlist);
	final ResultActions response = mockMvc
      .perform(get("/department/getAll")
      .contentType(MediaType.APPLICATION_JSON));
	response.andExpect(MockMvcResultMatchers.status().isOk());
	
	// 
  }
  
}
