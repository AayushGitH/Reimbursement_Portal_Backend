package proj.api.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Date;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import proj.api.entities.Reimburse;

@ExtendWith(MockitoExtension.class)
public class ReimburseTest {

	@Test
	@DisplayName("Reimburse entity getter and setter test method")
	public void getterAndSetter() {
		Reimburse reimburse = new Reimburse();
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
		
		User user = new User();
		user.setUserId(111);
		user.setName("Aayush Tiwari");
		reimburse.setUser(user);
		assertNotNull(reimburse.getUser());
		assertEquals(user, reimburse.getUser());
//		assertEquals(comment, reimburse.getUser());
	}
	
	@Test
	@DisplayName("Reimburse entity toString test method")
	public void toStringTest() {
		
		Reimburse reimburse = new Reimburse();
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
		reimburse.setUser(null);
		String expected = "Reimburse [reimburseId=101,"
				+ " expenseType=Food, amount=12000, expenseDate=" + newDate + ", documentUrl=url,"
				+ " currency=Dollar, comment=My comment, status=PENDING,"
				+ " statusDescription=My description, user=null]";
		
		assertEquals(expected, reimburse.toString());
	}
	@Test
  @DisplayName("Reimburse entity hashCode and equals method test")
  public void hashCodeAndEqualsTest() {
		Date date = new Date();
  	User user1 = new User(501, "Test", "test@nucleusTeq.com",
    "Test@123", Designation.EMPLOYEE, "1234567890", 11,
    "Secret answer", date, null, null);
  	Reimburse reimburse1 = new Reimburse(201, "FOOD", 1200,
  	date, "doc url", "INR", "Comment", Status.PENDING,
  	"Description", user1);
  	
  	assertEquals(reimburse1, reimburse1);
  	assertEquals(reimburse1.hashCode(), reimburse1.hashCode());
  	
  	assertNotEquals(reimburse1, new Reimburse());
  	assertNotEquals(reimburse1, new Object());
  	
  	assertNotNull(reimburse1);
  	assertNotNull(reimburse1.hashCode());
  	assertFalse(reimburse1.equals(null));
  	
  	Reimburse reimburse2 = new Reimburse(201, "FOOD", 1200,
    date, "doc url", "INR", "Comment", Status.PENDING,
    "Description", user1);
  	
  	reimburse2.setReimburseId(301);
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
  	
  	reimburse2.setStatusDescription("New description");
  	assertFalse(reimburse1.equals(reimburse2));
  	
  	reimburse2.setUser(new User());
  	assertFalse(reimburse1.equals(reimburse2));
  }
}
