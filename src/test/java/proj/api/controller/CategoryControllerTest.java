package proj.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import proj.api.controller.CategoryController;
import proj.api.in.dto.CategoryInDto;
import proj.api.out.dto.CategoryOutDto;
import proj.api.services.CategoryService;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import proj.api.controller.CategoryController;
import proj.api.in.dto.CategoryInDto;
import proj.api.out.dto.CategoryOutDto;
import proj.api.services.CategoryService;

@WebMvcTest(controllers = CategoryController.class)
@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CategoryService categoryService;
	
	@InjectMocks
	private CategoryController categoryController;
	
	@Test
	@DisplayName("Create category method test")
	public void saveCategory() throws JsonProcessingException, Exception {
	  // Arrange
      CategoryInDto category = new CategoryInDto(201, "Food", 45000);
      CategoryOutDto categoryOutDto = new CategoryOutDto(201,"Food", 45000);
      
      // Act
      when(categoryService.saveCategorys(category)).thenReturn(categoryOutDto);
      final ResultActions response = mockMvc
        .perform(post("/category/create")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(category)));
      response.andExpect(MockMvcResultMatchers.status().isCreated());
      
//    	MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
//          .post("/category/create")
//          .accept(MediaType.APPLICATION_JSON_VALUE)
//          .contentType(MediaType.APPLICATION_JSON_VALUE)
//          .content(objectMapper.writeValueAsString(category))).andReturn();
//    	assertEquals(objectMapper.writeValueAsString(categoryOutDto), mvcResult.getResponse().getContentAsString());
    	
//    	MvcResult mvcResult = mockMvc
//          .perform(MockMvcRequestBuilders.post("/category/create").accept(MediaType.APPLICATION_JSON_VALUE)
//                  .contentType(MediaType.APPLICATION_JSON_VALUE)
//                  .content(objectMapper.writeValueAsString(category)))
//          .andExpect(status().isOk()).andReturn();
	}
	
	@Test
	@DisplayName("Get all categories method test")
	void getAllCategories() throws JsonProcessingException, Exception {
	  // Arrange
      final CategoryOutDto categoryOutDto1 = new CategoryOutDto(101,"Food", 45000);
      final CategoryOutDto categoryOutDto2 = new CategoryOutDto(201,"Travel", 15000);
      final List<CategoryOutDto> categorylist = Arrays.asList(categoryOutDto1,categoryOutDto2);
      
      // Act
      when(categoryService.getAllCategories()).thenReturn(categorylist);
      final ResultActions response = mockMvc
        .perform(get("/category/getAll")
        .contentType(MediaType.APPLICATION_JSON));
      response.andExpect(MockMvcResultMatchers.status().isOk());
      response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)));
	}
	
	@Test
	@DisplayName("Delete category method test")
	void deleteCategory() throws JsonProcessingException, Exception {
	  doNothing().when(categoryService).deleteCategory("Travel");
	  final ResultActions response = mockMvc
			  .perform(delete("/category/delete/Travel")
			  .contentType(MediaType.APPLICATION_JSON));
	  response.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
