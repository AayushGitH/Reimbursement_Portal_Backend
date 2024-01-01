package proj.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Base64.Encoder;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import proj.api.controller.HomeController;
import proj.api.entities.Designation;
import proj.api.in.dto.DepartmentInDto;
import proj.api.in.dto.LoginInDTO;
import proj.api.in.dto.UserInDto;
import proj.api.out.dto.DepartmentOutDto;
import proj.api.out.dto.ReimburseOutDto;
import proj.api.out.dto.UserOutDto;
import proj.api.services.DepartmentService;
import proj.api.services.UserService;
import proj.api.services.impl.UserServiceImpl;


public class HomeControllerTest {
  
	@Autowired
  private MockMvc mockMvc;

  @Mock
  private UserService userService;

  
  private ObjectMapper objectMapper = new ObjectMapper();
  
  @InjectMocks
  private HomeController homeController;
  
  @SuppressWarnings("deprecation")
	@BeforeEach
  void method() {
  	MockitoAnnotations.initMocks(this);
  	mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
  }
  
  @Test
  @DisplayName("Save user test")
  void saveUser() throws JsonProcessingException, Exception {
	// Arrange
//  DepartmentInDto department = new DepartmentInDto(101, "HR");
  	DepartmentInDto department = new DepartmentInDto();
  	department.setDepartmentId(101);
  	department.setDepartmentName("HR");
  DepartmentOutDto departmentout = new DepartmentOutDto(101, "HR");
  List<ReimburseOutDto> rlist = new ArrayList<ReimburseOutDto>();
	UserInDto userInDto = new UserInDto("Aayush", "aayush@nucleusTeq.com", "Aayush@123", Designation.ADMIN, "1234567890", 0, "My secret", new Date(), department);
	UserOutDto userOutDto = new UserOutDto(101,"Aayush", "aayush@nucleusTeq.com", Designation.ADMIN, "1234567890", 0, new Date(), "Manager", departmentout);
	
	// Act
	when(userService.saveUser(userInDto)).thenReturn(userOutDto);
	
	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
      .post("/home/register")
      .accept(MediaType.APPLICATION_JSON_VALUE)
      .contentType(MediaType.APPLICATION_JSON_VALUE)
      .content(objectMapper.writeValueAsString(userInDto))).andReturn();
	System.out.println("My response is " + mvcResult.getResponse().getContentAsString().length());
	assertEquals(201, mvcResult.getResponse().getStatus());
//	assertEquals(objectMapper.writeValueAsString(userOutDto), mvcResult.getResponse().getContentAsString());
	
  }
  
  @Test
  @DisplayName("Login controller test")
  void testLogin() throws JsonProcessingException, Exception {
    // Arrange
	LoginInDTO request = new LoginInDTO();
	request.setEmail("aayush@nucleusTeq.com");
	request.setPassword("Aayush@123");
	UserOutDto userOutDto = new UserOutDto(101,"Aayu", "aayu@nucleusTeq.com", Designation.ADMIN, "9826921884", 1, new Date(), "Manager", null);

    // Act
	when(userService.getUserByEmailAndPassword(request.getEmail(), request.getPassword())).thenReturn(userOutDto);
	final ResultActions response = mockMvc
			.perform(post("/home/login")
		    .contentType(MediaType.APPLICATION_JSON)
		    .content(objectMapper.writeValueAsString(request)));
	response.andExpect(MockMvcResultMatchers.status().isOk());
  }
}
