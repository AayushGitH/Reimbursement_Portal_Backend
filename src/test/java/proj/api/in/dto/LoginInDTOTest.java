package proj.api.in.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LoginInDTOTest {
	@Test
  @DisplayName("LoginInDTO getter and setter test")
  public void getterAndSetter() {
  	LoginInDTO loginInDTO = new LoginInDTO();
  	loginInDTO.setEmail("aayush@nucleusTeq.com");
  	loginInDTO.setPassword("Aayush@123");
  	
  	String expectedEmail = "aayush@nucleusTeq.com";
  	String expectedPassword = "Aayush@123";
  	assertNotNull(loginInDTO.getEmail());
  	assertEquals(expectedEmail, loginInDTO.getEmail());
  	assertEquals(expectedPassword, loginInDTO.getPassword());
  }
}
