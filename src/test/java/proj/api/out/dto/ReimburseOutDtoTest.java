package proj.api.out.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import proj.api.entities.Reimburse;
import proj.api.entities.Status;
import proj.api.entities.User;
import proj.api.in.dto.ReimburseInDto;
import proj.api.out.dto.ReimburseOutDto;

@ExtendWith(MockitoExtension.class)
public class ReimburseOutDtoTest {

	@Test
	@DisplayName("ReimburseOutDTO getter and setter test method")
	public void getterAndSetter() {
		ReimburseOutDto reimburse = new ReimburseOutDto();
		int id = 101;
		reimburse.setReimburseId(id);
		assertNotNull(reimburse.getReimburseId());
		assertEquals(id, reimburse.getReimburseId());
		
		String expenseType = "Food";
		reimburse.setExpenseType(expenseType);
		assertNotNull(reimburse.getExpenseType());
		assertEquals(expenseType, reimburse.getExpenseType());
		
		int amount = 12000;
		reimburse.setAmount(amount);
		assertNotNull(reimburse.getAmount());
		assertEquals(amount, reimburse.getAmount());
		
		Date date = new Date();
		reimburse.setExpenseDate(date);
		assertNotNull(reimburse.getExpenseDate());
		assertEquals(date, reimburse.getExpenseDate());
		
		String documentURL = "My URL";
		reimburse.setDocumentUrl(documentURL);
		assertNotNull(reimburse.getDocumentUrl());
		assertEquals(documentURL, reimburse.getDocumentUrl());
		
		String currency = "INR";
		reimburse.setCurrency(currency);
		assertNotNull(reimburse.getCurrency());
		assertEquals(currency, reimburse.getCurrency());
		
		String comment = "My comment";
		reimburse.setComment(comment);
		assertNotNull(reimburse.getComment());
		assertEquals(comment, reimburse.getComment());
		
		reimburse.setStatus(Status.PENDING);
		assertNotNull(reimburse.getStatus());
		assertEquals(Status.PENDING, reimburse.getStatus());
		
		String description = "Status description";
		reimburse.setStatusDescription(description);
		assertNotNull(reimburse.getStatusDescription());
		assertEquals(description, reimburse.getStatusDescription());
		
		String employeeName = "Aayush Tiwari";
		reimburse.setEmployeeName(employeeName);
		assertNotNull(reimburse.getEmployeeName());
		assertEquals(employeeName, reimburse.getEmployeeName());
	}
	
	@Test
	@DisplayName("Reimburse out DTO toString test method")
	public void toStringTest() {
		ReimburseOutDto reimburse = new ReimburseOutDto();
		reimburse.setReimburseId(101);
		reimburse.setExpenseType("Food");
		reimburse.setAmount(12000);
		Date date = new Date();
		String newDate = date.toString();
		reimburse.setExpenseDate(date);
		reimburse.setDocumentUrl("url");
		reimburse.setCurrency("Dollar");
		reimburse.setComment("My comment");
		reimburse.setStatus(Status.PENDING);
		reimburse.setStatusDescription("My description");
		reimburse.setEmployeeName("Aayush Tiwari");
		String expected = "ReimburseOutDto [reimburseId=101, expenseType=Food,"
				+ " amount=12000, expenseDate="+newDate+", documentUrl=url, "
				+ "currency=Dollar, status=PENDING, comment=My comment, employeeName=Aayush Tiwari, "
				+ "statusDescription=My description]";
		
		assertEquals(expected, reimburse.toString());
	}
	
	@Test
	@DisplayName("Reimburse Out DTO hashCode and equals method test")
	public void hashCodeAndEqualsTest() {
		Date date = new Date();
		ReimburseOutDto reimburse1 = new ReimburseOutDto(101, "Food", 1200, date, "URL", "INR", Status.PENDING, "Comment", "Test", "No description");

		assertEquals(reimburse1, reimburse1);
		assertEquals(reimburse1.hashCode(), reimburse1.hashCode());

		assertNotEquals(reimburse1, new Reimburse());
		assertNotEquals(reimburse1, new Object());

		assertNotNull(reimburse1);
		assertNotNull(reimburse1.hashCode());
		assertFalse(reimburse1.equals(null));

		ReimburseOutDto reimburse2 = new ReimburseOutDto(101, "Food", 1200, date, "URL", "INR", Status.PENDING, "Comment", "Test", "No description");
		
		assertTrue(reimburse1.equals(reimburse2));
		reimburse1.setReimburseId(200);
		assertFalse(reimburse1.equals(reimburse2));
		
		reimburse2.setExpenseType("TRAVEL");
		assertFalse(reimburse1.equals(reimburse2));

		reimburse2.setAmount(1500);
		assertFalse(reimburse1.equals(reimburse2));

		reimburse2.setExpenseDate(date);
		assertFalse(reimburse1.equals(reimburse2));

		reimburse2.setDocumentUrl("URL part 2");
		assertFalse(reimburse1.equals(reimburse2));

		reimburse2.setCurrency("Dollar");
		assertFalse(reimburse1.equals(reimburse2));

		reimburse2.setComment("New comment");
		assertFalse(reimburse1.equals(reimburse2));
		
		reimburse2.setStatus(Status.ACCEPTED);
		assertFalse(reimburse1.equals(reimburse2));
		
		reimburse2.setEmployeeName("Test name");
		assertFalse(reimburse1.equals(reimburse2));
		
		reimburse2.setStatusDescription("New description");
		assertFalse(reimburse1.equals(reimburse2));
	}
}
