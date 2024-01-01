package proj.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import proj.api.controller.EmployeeController;
import proj.api.entities.Designation;
import proj.api.entities.User;
import proj.api.out.dto.UserOutDto;
import proj.api.services.UserService;

@WebMvcTest(controllers = EmployeeController.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {
 
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private UserService userService;

  @Test
  @DisplayName("Get user by role test")
  void get_roleUser() throws Exception {
  	// Arrange
	Date date = new Date();
  	UserOutDto userOutDto1 = new UserOutDto(101,"Test1", "test1@nucleusTeq.com", Designation.MANAGER, "9826921884", 0, date,"Manager", null);
  	UserOutDto userOutDto2 = new UserOutDto(201,"Test2", "test2@nucleusTeq.com", Designation.MANAGER, "1234567890", 0, date, "Manager", null);
  	List<UserOutDto> userslist = Arrays.asList(userOutDto1, userOutDto2);
  	
  	// Act
  	when(userService.getUserByRole("MANAGER")).thenReturn(userslist);
  	ResultActions response = this.mockMvc
  			.perform(get("/employee/get/MANAGER")
  			.contentType(MediaType.APPLICATION_JSON));
  	response.andExpect(MockMvcResultMatchers.status().isOk());
  }
  
  @Test
  @DisplayName("Assign user to manager test")
  void get_assignUserManager() throws Exception {
  	// Arrange
  	String userEmail = "Employee@nucleusTeq.com";
  	String managerEmail = "Manager@nucleusTeq.com";
  	
  	// Act
  	when(userService.assignEmployee(userEmail, managerEmail)).thenReturn("Successfully assigned user with manager");
  	ResultActions response = this.mockMvc
  			.perform(post("/employee/assign/userEmail/managerEmail")
  			.contentType(MediaType.APPLICATION_JSON));
  	response.andExpect(MockMvcResultMatchers.status().isOk());
  }
}
