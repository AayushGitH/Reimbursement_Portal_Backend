package proj.api.in.dto;

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

import proj.api.entities.Designation;
import proj.api.entities.Reimburse;
import proj.api.entities.Status;
import proj.api.entities.User;

@ExtendWith(MockitoExtension.class)
public class ReimburseInDtoTest {

	@Test
	@DisplayName("ReimburseInDTO getter and setter test method")
	public void getterAndSetter() {
		ReimburseInDto reimburse = new ReimburseInDto();
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
	}
	
	@Test
	@DisplayName("Reimburse out DTO toString test method")
	public void toStringTest() {
		ReimburseInDto reimburse = new ReimburseInDto();
		reimburse.setExpenseType("Food");
		reimburse.setAmount(12000);
		Date date = new Date();
		String newDate = date.toString();
		reimburse.setExpenseDate(date);
		reimburse.setDocumentUrl("url");
		reimburse.setCurrency("Dollar");
		reimburse.setComment("My comment");
		String expected = "ReimburseInDto ["
				+ "expenseType=Food, amount=12000, expenseDate="+newDate+", documentUrl=url,"
				+ " currency=Dollar, comment=My comment]";
		
		assertEquals(expected, reimburse.toString());
	}

	@Test
	@DisplayName("Reimburse In DTO hashCode and equals method test")
	public void hashCodeAndEqualsTest() {

		Date date = new Date();
		ReimburseInDto reimburse1 = new ReimburseInDto("FOOD", 1234, date, "URL", "INR", "Comment");

		assertEquals(reimburse1, reimburse1);
		assertEquals(reimburse1.hashCode(), reimburse1.hashCode());

		assertNotEquals(reimburse1, new Reimburse());
		assertNotEquals(reimburse1, new Object());

		assertNotNull(reimburse1);
		assertNotNull(reimburse1.hashCode());
		assertFalse(reimburse1.equals(null));

		ReimburseInDto reimburse2 = new ReimburseInDto("FOOD", 1234, date, "URL", "INR", "Comment");

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
	}
}
