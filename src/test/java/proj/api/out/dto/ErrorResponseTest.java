package proj.api.out.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class ErrorResponseTest {
	@Test
  @DisplayName("ErrorResponse getter and setter test")
  public void getterAndSetter() {
		
		String val1 = "Error message one";
		String val2 = "Error message two";
		List<String> list = Arrays.asList(val1, val2);
  	ErrorResponse errorResponse = new ErrorResponse();
  	errorResponse.setErrorMessage(list);
  	assertNotNull(errorResponse.getErrorMessage());
  	assertEquals(list, errorResponse.getErrorMessage());
  	
  	errorResponse.setStatus(HttpStatus.BAD_REQUEST.toString());
  	assertNotNull(errorResponse.getStatus());
  	assertEquals(HttpStatus.BAD_REQUEST.toString(), errorResponse.getStatus());
  	
  	Instant value = Instant.now();
  	errorResponse.setTimestamp(value);
  	assertNotNull(errorResponse.getTimestamp());
  	assertEquals(value, errorResponse.getTimestamp());
  	
  	ErrorResponse errorResponse2 = new ErrorResponse(list, HttpStatus.BAD_GATEWAY.toString(), Instant.now());
  	assertNotNull(errorResponse2);
  }
}
