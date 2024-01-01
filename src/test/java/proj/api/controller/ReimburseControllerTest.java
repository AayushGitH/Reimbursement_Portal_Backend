package proj.api.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import proj.api.entities.Reimburse;
import proj.api.entities.Status;
import proj.api.in.dto.DepartmentInDto;
import proj.api.in.dto.ReimburseInDto;
import proj.api.in.dto.UpdateClaimInDto;
import proj.api.out.dto.DepartmentOutDto;
import proj.api.out.dto.ReimburseOutDto;
import proj.api.services.ReimburseService;

@WebMvcTest(controllers = ReimburseController.class)
@ExtendWith(MockitoExtension.class)
public class ReimburseControllerTest {
	
  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private ReimburseService reimburseService;
  
  @Autowired
  private ObjectMapper objectMapper;
  
  @Test
  @DisplayName("Save reimbursement api test")
  void testSaveReimbursement() throws JsonProcessingException, Exception {
    // Arrange
//  	Reimburse reimburse = new Reimburse(101, "FOOD", 1200, new Date(), "URL", "INR", "Comment", Status.PENDING, null, null);
	  Date date = new Date();
		ReimburseInDto reimburseInDto = new ReimburseInDto("Food", 1200, date, "URL", "INR", "Comment");
		ReimburseOutDto reimburseOutDto = new ReimburseOutDto(101, "Food", 1200, date, "URL", "INR", Status.PENDING, "Comment", "Test", "No description");
		String reimb = "{\"expenseType\":\"Food\",\"amount\":\"1500\",\"currency\":\"Dollar\",\"comment\":\"Personal comment\",\"expenseDate\":\"2023-10-09T12:15:09\",\"status\":\"Accepted\"}";
		MockMultipartFile file 
    = new MockMultipartFile(
      "file", 
      "hello.txt", 
      MediaType.TEXT_PLAIN_VALUE, 
      "Hello, World!".getBytes()
    );
	
	// Act
	when(reimburseService.saveReimburse(reimburseInDto, "test@nucleusTeq.com")).thenReturn(reimburseOutDto);
	final ResultActions response = mockMvc
      .perform(multipart("/claim/create/email").file(file)
      .param("claim", reimb));
	response.andExpect(MockMvcResultMatchers.status().isCreated());
  }
  
  @Test
  @DisplayName("Testing particular user view claims method")
  void testViewClaims() throws Exception {
  	// Arrange
	  Date date = new Date();
  	ReimburseOutDto reimburseOutDto1 = new ReimburseOutDto(101,"Food", 15000, date, "URL", "INR", Status.PENDING, "My comment part 1","Test","Status description");
  	ReimburseOutDto reimburseOutDto2 = new ReimburseOutDto(1012,"Travel", 20000, date, "URL", "Dollar", Status.PENDING, "My comment part 2","Aayush","Status description");
  	List<ReimburseOutDto> list = Arrays.asList(reimburseOutDto1, reimburseOutDto2);
  	
  	// Act
  	when(reimburseService.getReimbursementsByUserEmail("test@nucleusTeq.com")).thenReturn(list);
  	ResultActions response = this.mockMvc
  			.perform(get("/claim/viewClaims/test@nucleusTeq.com")
  			.contentType(MediaType.APPLICATION_JSON));
  	response.andExpect(MockMvcResultMatchers.status().isOk());
  }
  
  @Test
  @DisplayName("Testing get all claims by status method")
  void testGetAllClaimsByStatus() throws Exception {
  	// Arrange
	Date date = new Date();
  	ReimburseOutDto reimburseOutDto1 = new ReimburseOutDto(101,"Food", 15000, date, "URL", "INR", Status.PENDING, "My comment part 1","Test","Status description");
  	ReimburseOutDto reimburseOutDto2 = new ReimburseOutDto(1012,"Travel", 20000, date, "URL", "Dollar", Status.PENDING, "My comment part 2","Aayush","Status description");
  	List<ReimburseOutDto> list = Arrays.asList(reimburseOutDto1, reimburseOutDto2);
  	
  	ReimburseOutDto reimburseOutDto3 = new ReimburseOutDto(201,"Food", 15000, date, "URL", "INR", Status.ACCEPTED, "My comment part 1","Test","Status description");
  	List<ReimburseOutDto> acceptedlist = Arrays.asList(reimburseOutDto3);
  	
  	// Act
  	when(reimburseService.getAllReimbursementsByStatus("Pending")).thenReturn(list);
  	when(reimburseService.getAllReimbursementsByStatus("Accepted")).thenReturn(acceptedlist);
  	ResultActions response = this.mockMvc
  			.perform(get("/claim/getClaimsByStatus/Pending")
  			.contentType(MediaType.APPLICATION_JSON));
  	response.andExpect(MockMvcResultMatchers.status().isOk());
  	response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
  	
  	ResultActions response1 = this.mockMvc
  			.perform(get("/claim/getClaimsByStatus/Accepted")
  			.contentType(MediaType.APPLICATION_JSON));
  	response1.andExpect(MockMvcResultMatchers.status().isOk());
  	response1.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)));
  }
  
  @Test
  @DisplayName("Testing update claim method")
  void testUpdateClaim() throws Exception {
  	// Arrange
	  Date date = new Date();
  	Reimburse reimburse = new Reimburse(111, "Other", 22000, date, "My photo", "Dollar", "Comment given", Status.PENDING, null, null);
  	ReimburseOutDto reimburseOutDto1 = new ReimburseOutDto(111,"Food", 15000, date, "URL", "INR", Status.ACCEPTED, "My comment part 1","Test","Accepted");
  	UpdateClaimInDto updateClaimInDto = new UpdateClaimInDto(101, "Rejected comment");
  	
  	// Act
  	when(reimburseService.updateReimburse(111, "Accepted")).thenReturn(reimburseOutDto1);
  	ResultActions response = this.mockMvc
  			.perform(put("/claim/updateClaim")
  			.content(objectMapper.writeValueAsString(updateClaimInDto))
  			.contentType(MediaType.APPLICATION_JSON));
  	response.andExpect(MockMvcResultMatchers.status().isOk());
  }
  
  @Test
  @DisplayName("Testing get claims for manager method")
  void testGetAllClaimsForManager() throws Exception {
  	// Arrange
	  Date date = new Date();
  	ReimburseOutDto reimburseOutDto1 = new ReimburseOutDto(101,"Food", 15000, date, "URL", "INR", Status.PENDING, "My comment part 1","Test","Status description");
  	ReimburseOutDto reimburseOutDto2 = new ReimburseOutDto(1012,"Travel", 20000, date, "URL", "Dollar", Status.PENDING, "My comment part 2","Aayush","Status description");
  	List<ReimburseOutDto> list = Arrays.asList(reimburseOutDto1, reimburseOutDto2);
  	
  	// Act
  	when(reimburseService.getReimbursementsByManagerId(101)).thenReturn(list);
  	ResultActions response = this.mockMvc
  			.perform(get("/claim/getClaimsManager/101")
  			.contentType(MediaType.APPLICATION_JSON));
  	response.andExpect(MockMvcResultMatchers.status().isOk());
  	response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
  }
  
  @Test
  @DisplayName("Testing get claims for manager with status method")
  void testGetAllClaimsForManagerStatus() throws Exception {
  	// Arrange
	  Date date = new Date();
  	ReimburseOutDto reimburseOutDto1 = new ReimburseOutDto(101,"Food", 15000, date, "URL", "INR", Status.PENDING, "My comment part 1","Test","Status description");
  	ReimburseOutDto reimburseOutDto2 = new ReimburseOutDto(1012,"Travel", 20000, date, "URL", "Dollar", Status.PENDING, "My comment part 2","Aayush","Status description");
  	List<ReimburseOutDto> list = Arrays.asList(reimburseOutDto1, reimburseOutDto2);
  	
  	// Act
  	when(reimburseService.getAllReimbursementsByStatusForManager(101, "Pending")).thenReturn(list);
  	ResultActions response = this.mockMvc
  			.perform(get("/claim/getClaimsManager/101/Pending")
  			.contentType(MediaType.APPLICATION_JSON));
  	response.andExpect(MockMvcResultMatchers.status().isOk());
  	response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
  }
  
  @Test
  @DisplayName("Testing get all claims method")
  void testGetAllClaims() throws Exception {
  	// Arrange
	  Date date = new Date();
  	ReimburseOutDto reimburseOutDto1 = new ReimburseOutDto(101,"Food", 15000, date, "URL", "INR", Status.PENDING, "My comment part 1","Test","Status description");
  	ReimburseOutDto reimburseOutDto2 = new ReimburseOutDto(1012,"Travel", 20000, date, "URL", "Dollar", Status.PENDING, "My comment part 2","Aayush","Status description");
  	List<ReimburseOutDto> list = Arrays.asList(reimburseOutDto1, reimburseOutDto2);
  	
  	// Act
  	when(reimburseService.getAllReimbursements()).thenReturn(list);
  	ResultActions response = this.mockMvc
  			.perform(get("/claim/getClaims")
  			.contentType(MediaType.APPLICATION_JSON));
  	response.andExpect(MockMvcResultMatchers.status().isOk());
  	response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
  }
  
  @Test
  @DisplayName("Testing get all specific claims method")
  void testGetSpecificClaims() throws Exception {
  	// Arrange
	  Date date = new Date();
  	ReimburseOutDto reimburseOutDto1 = new ReimburseOutDto(101,"Food", 15000, date, "URL", "INR", Status.ACCEPTED, "My comment part 1","Test","Status description");
  	ReimburseOutDto reimburseOutDto2 = new ReimburseOutDto(1012,"Travel", 20000, date, "URL", "Dollar", Status.ACCEPTED, "My comment part 2","Aayush","Status description");
  	List<ReimburseOutDto> list = Arrays.asList(reimburseOutDto1, reimburseOutDto2);
  	
  	// Act
  	when(reimburseService.getReimbursementsByUserEmailAndStatus("test@nucleusTeq.com", "Accepted")).thenReturn(list);
  	ResultActions response = this.mockMvc
  			.perform(get("/claim/viewClaims/test@nucleusTeq.com/Accepted")
  			.contentType(MediaType.APPLICATION_JSON));
  	response.andExpect(MockMvcResultMatchers.status().isOk());
  	response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
  }
  
  @Test
  void testGetImage() throws Exception {  	
  	// Act
  	ResultActions response = this.mockMvc
  			.perform(get("/claim/image/1696699309864_fuelBill.jpg")
  			.contentType(MediaType.APPLICATION_JSON));
  	response.andExpect(MockMvcResultMatchers.status().isOk());
  }
}
