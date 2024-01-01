package proj.api.exception;

import static org.mockito.Mockito.mock;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.bind.MethodArgumentNotValidException;
import proj.api.controller.HomeController;
import proj.api.entities.User;
import proj.api.in.dto.UserInDto;
import proj.api.services.impl.UserServiceImpl;

public class GlobalExceptionTest {
	
//	@MockBean
//	private HomeController homeController;
//	
//	@Test
//	void testMethodArgumentExceptionTest() {
//	  Assertions.assertThrows(MethodArgumentNotValidException.class, ()->{
//		  UserInDto userInDto = new UserInDto(0, "", "aayush@gmail.com", "Password", "Admin", "1111111111", 0, "", null, null);
//		  homeController.saveUser(userInDto);
//	  });	
//	}
}
