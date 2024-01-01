package proj.api.in.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * LoginApiRequest in DTO test method.
 */
@ExtendWith(MockitoExtension.class)
public class LoginApiRequestTest {
	@Test
  @DisplayName("CategoryInDTO getter and setter test")
  public void getterAndSetter() {
  	LoginApiRequest object = new LoginApiRequest();
  	object.setEmail("aayush@nucleusTeq.com");
  	object.setPassword("Aayush@123");
  	
  	String expectedEmail = "aayush@nucleusTeq.com";
  	String expectedPassword = "Aayush@123";
  	assertNotNull(object.getEmail());
  	assertEquals(expectedEmail, object.getEmail());
  	assertEquals(expectedPassword, object.getPassword());
  }
}
