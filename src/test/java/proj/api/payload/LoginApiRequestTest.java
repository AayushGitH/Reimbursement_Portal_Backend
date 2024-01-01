package proj.api.payload;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import proj.api.in.dto.LoginApiRequest;
import proj.api.in.dto.LoginInDTO;

/**
* Login Api Request test class.
*/
@ExtendWith(MockitoExtension.class)
public class LoginApiRequestTest {
	/**
	  * Login request class test method.
	  */
	  @Test
	  @DisplayName("Login Api Request entity")
	  void testGettersAndSetters() {
	    LoginApiRequest loginApiRequest = new LoginApiRequest();
	    loginApiRequest.setEmail("test@nucleusTeq.com");
	    loginApiRequest.setPassword("Aayush@123");
	    
	    assertNotNull(loginApiRequest.getEmail());
	    assertEquals("test@nucleusTeq.com", loginApiRequest.getEmail());
	    
	    assertNotNull(loginApiRequest.getPassword());
	    assertEquals("Aayush@123", loginApiRequest.getPassword());
	  }
}
