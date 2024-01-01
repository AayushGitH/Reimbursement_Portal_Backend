package proj.api.in.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UpdateClaimInDtoTest {
	@Test
  @DisplayName("UpdateClaimInDTO getter and setter test")
  public void getterAndSetter() {
		UpdateClaimInDto updateClaimInDto = new UpdateClaimInDto();
  	int id = 201;
  	updateClaimInDto.setId(id);
  	assertNotNull(updateClaimInDto.getId());
  	assertEquals(id, updateClaimInDto.getId());
  	
  	String comment = "Rejected comment";
  	updateClaimInDto.setComment(comment);
  	assertNotNull(updateClaimInDto.getComment());
  	assertEquals(comment, updateClaimInDto.getComment());
  }
}
