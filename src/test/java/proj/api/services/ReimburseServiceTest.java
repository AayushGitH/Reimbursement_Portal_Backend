package proj.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import proj.api.entities.Department;
import proj.api.entities.Designation;
import proj.api.entities.Reimburse;
import proj.api.entities.Status;
import proj.api.entities.User;
import proj.api.exception.BadRequestException;
import proj.api.exception.UserNotFoundException;
import proj.api.in.dto.DepartmentInDto;
import proj.api.in.dto.ReimburseInDto;
import proj.api.out.dto.DepartmentOutDto;
import proj.api.out.dto.ReimburseOutDto;
import proj.api.repository.ReimburseRepository;
import proj.api.repository.UserRepository;
import proj.api.services.impl.ReimburseServiceImpl;


@ExtendWith(MockitoExtension.class)
public class ReimburseServiceTest {
	
	@Mock
	private ReimburseRepository reimburseRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private ReimburseServiceImpl reimburseServiceImpl;
	
	@Test
	void testSaveReimburseSuccess() {
		// Arrange
		User mapuser = new User();
		mapuser.setName("Aayush Tiwari");
		Date date = new Date();
		Reimburse reimburse = new Reimburse(101, "FOOD", 1200, date, "URL", "INR", "Comment", Status.PENDING, null, mapuser);
		ReimburseInDto reimburseInDto = new ReimburseInDto("FOOD", 1200, date, "URL", "INR", "comment");
//		ReimburseOutDto reimburseOutDto = new ReimburseOutDto("FOOD", 1200, new Date(), "URL", "INR", Status.PENDING);
		List<Reimburse> list = Arrays.asList(reimburse);
		User user = new User(202, "Test", "test@nucleusTeq.com", "Test@123", Designation.MANAGER, "9826921884", 0, "Secret answer", date, null, list);
		Reimburse finalReimburse = this.reimburseServiceImpl.reimburseInDtoToReimburse(reimburseInDto);
		finalReimburse.setUser(user);
		ReimburseOutDto finalReimburseOutDto = this.reimburseServiceImpl.reimburseToReimburseOutDto(finalReimburse);

		// Act
//		when(modelMapper.map(reimburseInDto, Reimburse.class)).thenReturn(reimburse);
//		when(modelMapper.map(reimburse, ReimburseOutDto.class)).thenReturn(reimburseOutDto);
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.ofNullable(user));
		when(reimburseRepository.save(any(Reimburse.class))).thenReturn(reimburse);
		ReimburseOutDto saveReimburse = this.reimburseServiceImpl.saveReimburse(reimburseInDto, "test@nucleusTeq.com");

		// Assert
		assertThat(saveReimburse.getExpenseType()).isEqualTo(finalReimburseOutDto.getExpenseType());
		assertThat(saveReimburse.getExpenseType()).isNotEqualTo("TRAVEL");
	}
	
	@Test
	void testSaveReimburseFailure() {
		// Arrange
		User mapuser = new User();
		mapuser.setName("Aayush Tiwari");
		Date date = new Date();
		ReimburseInDto reimburseInDto = new ReimburseInDto("FOOD", 1200, date, "URL", "INR", "comment");
		User user = new User(202, "Test", "test@nucleusTeq.com", "Test@123", Designation.MANAGER, "9826921884", 0, "Secret answer", date, null, null);

		// Act
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

		// Assert
		assertThrows(UserNotFoundException.class, ()->reimburseServiceImpl.saveReimburse(reimburseInDto, user.getEmail()));
	}
	
	@Test
	void testSaveReimburseFailureExpenseType() {
		// Arrange
		User mapuser = new User();
		mapuser.setName("Aayush Tiwari");
		ReimburseInDto reimburseInDto = new ReimburseInDto();
		Date date = new Date();
		User user = new User(202, "Test", "test@nucleusTeq.com", "Test@123", Designation.MANAGER, "9826921884", 0, "Secret answer", date, null, null);

		// Assert
		assertThrows(BadRequestException.class, ()->reimburseServiceImpl.saveReimburse(reimburseInDto, user.getEmail()));
	}
	
	@Test
	void testSaveReimburseFailureAmount() {
		// Arrange
		User mapuser = new User();
		mapuser.setName("Aayush Tiwari");
		ReimburseInDto reimburseInDto = new ReimburseInDto();
		reimburseInDto.setExpenseType("Food");
		reimburseInDto.setAmount(0);
		reimburseInDto.setComment("My comment");
		Date date = new Date();
		User user = new User(202, "Test", "test@nucleusTeq.com", "Test@123", Designation.MANAGER, "9826921884", 0, "Secret answer", date, null, null);

		// Assert
		assertThrows(BadRequestException.class, ()->reimburseServiceImpl.saveReimburse(reimburseInDto, user.getEmail()));
	}
	
	@Test
	void testSaveReimburseFailureComment() {
		// Arrange
		User mapuser = new User();
		mapuser.setName("Aayush Tiwari");
		ReimburseInDto reimburseInDto = new ReimburseInDto();
		reimburseInDto.setExpenseType("Food");
		reimburseInDto.setAmount(1200);
		Date date = new Date();
		User user = new User(202, "Test", "test@nucleusTeq.com", "Test@123", Designation.MANAGER, "9826921884", 0, "Secret answer", date, null, null);

		// Assert
		assertThrows(BadRequestException.class, ()->reimburseServiceImpl.saveReimburse(reimburseInDto, user.getEmail()));
	}
	
	@Test
	void testGetReimbursements() {
		// Arrange
		User user1 = new User();
		user1.setName("Test name");
		User user2 = new User();
		user2.setName("Peronal name");
		Date date = new Date();
		Reimburse reimburse1 = new Reimburse(101, "FOOD", 1200, date, "URL", "INR", "Comment", Status.PENDING, null, user1);
		Reimburse reimburse2 = new Reimburse(201, "TRAVEL", 3500, date, "URL", "DOLLAR", "Comment", Status.PENDING, null, user2);
		List<Reimburse> reimburselist = Arrays.asList(reimburse1, reimburse2);
		User user = new User(202, "Test", "test@nucleusTeq.com", "Test@123", Designation.MANAGER, "9826921884", 0, "Secret answer", date, null, null);

		// Act
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.ofNullable(user));
		when(reimburseRepository.findByUserId(202)).thenReturn(reimburselist);
		ReimburseOutDto finalreimburseOutDto1 = this.reimburseServiceImpl.reimburseToReimburseOutDto(reimburse1);
		ReimburseOutDto finalreimburseOutDto2 = this.reimburseServiceImpl.reimburseToReimburseOutDto(reimburse2);
		List<ReimburseOutDto> reimburseOutDtos = Arrays.asList(finalreimburseOutDto1, finalreimburseOutDto2);
		List<ReimburseOutDto> reimbursements = this.reimburseServiceImpl.getReimbursementsByUserEmail(user.getEmail());

		// Assert
		assertThat(reimbursements.size()).isEqualTo(reimburseOutDtos.size());
	}
	
	@Test
	void testGetReimbursementsByUserEmailFailure() {
		// Arrange
		Date date = new Date();
		User user = new User(202, "Test", "test@nucleusTeq.com", "Test@123", Designation.MANAGER, "9826921884", 0, "Secret answer", date, null, null);

		// Act
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

		// Assert
		assertThrows(UserNotFoundException.class, ()->reimburseServiceImpl.getReimbursementsByUserEmail(user.getEmail()));
	}
	
	@Test
	void testGetReimbursementsByStatus() {
		// Arrange
		User user1 = new User();
		user1.setName("Test name");
		User user2 = new User();
		user2.setName("Peronal name");
		Date date = new Date();
		Reimburse reimburse1 = new Reimburse(101, "FOOD", 1200, date, "URL", "INR", "Comment", Status.PENDING, null, user1);
		Reimburse reimburse2 = new Reimburse(201, "TRAVEL", 3500, date, "URL", "DOLLAR", "Comment", Status.PENDING, null, user2);
		
		Reimburse reimburse3 = new Reimburse(401, "SHIFTING", 1200, date, "URL", "INR", "New Comment", Status.ACCEPTED, null, user1);
		Reimburse reimburse4 = new Reimburse(501, "OTHER", 3500, date, "URL", "INR", "New Comment", Status.ACCEPTED, null, user2);
		
		Reimburse reimburse5 = new Reimburse(601, "NEW REIMBURSE", 1200, date, "URL", "INR", "New Comment part 2", Status.REJECTED, null, user1);

		List<Reimburse> pendingreimburselist = Arrays.asList(reimburse1, reimburse2);
		List<Reimburse> acceptedreimburselist = Arrays.asList(reimburse1, reimburse2);
		List<Reimburse> rejectedreimburselist = Arrays.asList(reimburse5);

		// Act
		when(reimburseRepository.findByStatus(Status.PENDING)).thenReturn(pendingreimburselist);
		when(reimburseRepository.findByStatus(Status.ACCEPTED)).thenReturn(acceptedreimburselist);
		when(reimburseRepository.findByStatus(Status.REJECTED)).thenReturn(rejectedreimburselist);
		ReimburseOutDto finalreimburseOutDto1 = this.reimburseServiceImpl.reimburseToReimburseOutDto(reimburse1);
		ReimburseOutDto finalreimburseOutDto2 = this.reimburseServiceImpl.reimburseToReimburseOutDto(reimburse2);
		ReimburseOutDto finalreimburseOutDto3 = this.reimburseServiceImpl.reimburseToReimburseOutDto(reimburse3);
		ReimburseOutDto finalreimburseOutDto4 = this.reimburseServiceImpl.reimburseToReimburseOutDto(reimburse4);
		ReimburseOutDto finalreimburseOutDto5 = this.reimburseServiceImpl.reimburseToReimburseOutDto(reimburse5);
		List<ReimburseOutDto> pendingreimburseOutDtos = Arrays.asList(finalreimburseOutDto1, finalreimburseOutDto2);
		List<ReimburseOutDto> acceptedreimburseOutDtos = Arrays.asList(finalreimburseOutDto3, finalreimburseOutDto4);
		List<ReimburseOutDto> rejectedreimburseOutDtos = Arrays.asList(finalreimburseOutDto5);
		List<ReimburseOutDto> pendingreimbursements = this.reimburseServiceImpl.getAllReimbursementsByStatus("Pending");
		List<ReimburseOutDto> acceptedreimbursements = this.reimburseServiceImpl.getAllReimbursementsByStatus("Accepted");
		List<ReimburseOutDto> rejectedreimbursements = this.reimburseServiceImpl.getAllReimbursementsByStatus("Rejected");

		// Assert
		assertThat(pendingreimbursements.size()).isEqualTo(pendingreimburseOutDtos.size());
		assertThat(pendingreimbursements.get(0).getComment()).isEqualTo("Comment");
		assertThat(acceptedreimbursements.size()).isEqualTo(2);
		assertThat(rejectedreimbursements.size()).isEqualTo(1);
		assertThat("New Comment part 2").isEqualTo(rejectedreimbursements.get(0).getComment());
		
	}
	
	@Test
	void testGetReimbursementsByStatusFailure() {
		// Assert
		assertThrows(BadRequestException.class, ()->reimburseServiceImpl.getAllReimbursementsByStatus("Demo"));	
	}
	
	@Test
	void testUpdateReimburse() {
		// Arrange
		Date date = new Date();
		User user = new User(101, "Aayush", null, null, null, null, 0, null, date, null, null);
		Reimburse reimburse = new Reimburse(201, "Food", 12000, date, "URL", "INR", "My comment", Status.PENDING, "Not solved yet", user);
		Reimburse reimburse1 = new Reimburse(202, "Food", 12000, date, "URL", "INR", "Accepted", Status.PENDING, "Not solved yet", user);
		Reimburse updatedreimburse = new Reimburse(201, "Food", 12000, date, "URL", "INR", "Updated comment", Status.PENDING, "Not solved yet", user);
		Reimburse updatedreimburse2 = new Reimburse(201, "Food", 12000, date, "URL", "INR", "Accepted", Status.PENDING, "Not solved yet", user);
		ReimburseOutDto reimburseOutDto = new ReimburseOutDto(201, "Food", 12000, date, "URL", "INR", Status.REJECTED, "Personal comment", "Aayush", "Status description");
		ReimburseOutDto reimburseOutDto2 = new ReimburseOutDto(202, "Food", 12000, date, "URL", "INR", Status.REJECTED, "Personal comment", "Aayush", "Accepted");
		
		// Act
		when(reimburseRepository.findById(201)).thenReturn(Optional.ofNullable(reimburse));
		when(reimburseRepository.findById(202)).thenReturn(Optional.ofNullable(reimburse1));
//		when(reimburseServiceImpl.updateReimburse(201, "Updated comment")).thenReturn(reimburseOutDto);
		when(reimburseRepository.save(reimburse)).thenReturn(updatedreimburse);
		when(reimburseRepository.save(reimburse1)).thenReturn(updatedreimburse2);
		ReimburseOutDto updateReimburse = reimburseServiceImpl.updateReimburse(reimburse.getReimburseId(), "Updated comment");
		ReimburseOutDto updateReimburse2 = this.reimburseServiceImpl.updateReimburse(202, "Accepted");
		
		// Assert
		assertEquals(reimburseOutDto.getExpenseType(), updateReimburse.getExpenseType());
		assertEquals(reimburseOutDto2.getExpenseType(), updateReimburse2.getExpenseType());
	}
	
	@Test
	void testGetAllReimbursements() {
		// Arrange
		Date date = new Date();
		User user = new User(101, "Aayush", null, null, null, null, 0, null, date, null, null);
		Reimburse reimburse = new Reimburse(201, "Food", 12000, date, "URL", "INR", "My comment", Status.PENDING, "Not solved yet", user);
		Reimburse reimburse1 = new Reimburse(202, "Food", 12000, date, "URL", "INR", "Accepted", Status.PENDING, "Not solved yet", user);
		
		ReimburseOutDto reimburseOutDto = new ReimburseOutDto(201, "Food", 12000, date, "URL", "INR", Status.REJECTED, "My comment", "Aayush", "Status description");
		ReimburseOutDto reimburseOutDto2 = new ReimburseOutDto(202, "Food", 12000, date, "URL", "INR", Status.REJECTED, "Personal comment", "Aayush", "Accepted");
		
		List<ReimburseOutDto> erlist = Arrays.asList(reimburseOutDto, reimburseOutDto2);
		List<Reimburse> arlist = Arrays.asList(reimburse, reimburse1);
		
		// Act
		when(reimburseRepository.findAll()).thenReturn(arlist);
		List<ReimburseOutDto> allReimbursements = reimburseServiceImpl.getAllReimbursements();
		
		// Assert
		assertEquals(2, allReimbursements.size());
		assertEquals(reimburseOutDto.getComment(), allReimbursements.get(0).getComment());
	}
	
	@Test
	void testGetAllReimbursementsForUser() {
		// Arrange
		Date date = new Date();
		User user = new User(101, "Aayush", null, null, null, null, 0, null, date, null, null);
		Reimburse reimburse = new Reimburse(201, "Food", 12000, date, "URL", "INR", "My comment", Status.PENDING, "Not solved yet", user);
		Reimburse reimburse1 = new Reimburse(202, "Food", 12000, date, "URL", "INR", "Accepted", Status.PENDING, "Not solved yet", user);
		
		Reimburse reimburse2 = new Reimburse(201, "Travel", 12000, date, "URL", "INR", "My comment", Status.ACCEPTED, "Not solved yet", user);
		
		Reimburse reimburse3 = new Reimburse(202, "Food", 12000, date, "URL", "INR", "Accepted", Status.REJECTED, "Not solved yet", user);
		Reimburse reimburse4 = new Reimburse(201, "Food", 12000, date, "URL", "INR", "My comment", Status.REJECTED, "Not solved yet", user);
		Reimburse reimburse5 = new Reimburse(202, "Food", 250, date, "URL", "INR", "Accepted", Status.REJECTED, "Not solved yet", user);
		
		List<Reimburse> actuallist1 = Arrays.asList(reimburse, reimburse1);
		List<Reimburse> actuallist2 = Arrays.asList(reimburse2);
		List<Reimburse> actuallist3 = Arrays.asList(reimburse4, reimburse5, reimburse3);
		
		// Act
		when(reimburseRepository.findByStatusAndEmail(Status.PENDING, "test@nucleusTeq.com")).thenReturn(actuallist1);
		when(reimburseRepository.findByStatusAndEmail(Status.ACCEPTED, "test@nucleusTeq.com")).thenReturn(actuallist2);
		when(reimburseRepository.findByStatusAndEmail(Status.REJECTED, "test@nucleusTeq.com")).thenReturn(actuallist3);
		List<ReimburseOutDto> allReimbursements1 = reimburseServiceImpl.getReimbursementsByUserEmailAndStatus("test@nucleusTeq.com", "Pending");
		List<ReimburseOutDto> allReimbursements2 = reimburseServiceImpl.getReimbursementsByUserEmailAndStatus("test@nucleusTeq.com", "Accepted");
		List<ReimburseOutDto> allReimbursements3 = reimburseServiceImpl.getReimbursementsByUserEmailAndStatus("test@nucleusTeq.com", "Rejected");
		
		// Assert
		assertEquals(2, allReimbursements1.size());
		assertEquals(1, allReimbursements2.size());
		assertEquals(3, allReimbursements3.size());
		assertEquals(reimburse.getComment(), allReimbursements1.get(0).getComment());
		assertEquals(reimburse2.getExpenseType(), allReimbursements2.get(0).getExpenseType());
//		assertEquals(reimburse5.getAmount(), allReimbursements3.get(2).getAmount());
	}
	
	@Test
	void testGetAllReimbursementsForUserFailure() {
		// Assert
		assertThrows(BadRequestException.class, ()->reimburseServiceImpl.getReimbursementsByUserEmailAndStatus("aayush@nucleusTeq.com", "Demo"));
	}
	
	
	@Test
	void testGetReimbursementsByManagerId() {
		// Arrange
		Date date = new Date();
		User user = new User(101, "Aayush", null, null, null, null, 0, null, date, null, null);
		Reimburse reimburse = new Reimburse(201, "Food", 12000, date, "URL", "INR", "My comment", Status.PENDING, "Not solved yet", user);
		Reimburse reimburse1 = new Reimburse(202, "Food", 12000, date, "URL", "INR", "Accepted", Status.PENDING, "Not solved yet", user);
		
		ReimburseOutDto reimburseOutDto = new ReimburseOutDto(201, "Food", 12000, date, "URL", "INR", Status.REJECTED, "My comment", "Aayush", "Status description");
		ReimburseOutDto reimburseOutDto2 = new ReimburseOutDto(202, "Food", 12000, date, "URL", "INR", Status.REJECTED, "Personal comment", "Aayush", "Accepted");
		
		List<ReimburseOutDto> erlist = Arrays.asList(reimburseOutDto, reimburseOutDto2);
		List<Reimburse> arlist = Arrays.asList(reimburse, reimburse1);
		
		// Act
		when(reimburseRepository.findByManagerId(201)).thenReturn(arlist);
		List<ReimburseOutDto> allReimbursements = reimburseServiceImpl.getReimbursementsByManagerId(201);
		
		// Assert
		assertEquals(2, allReimbursements.size());
		assertEquals(reimburseOutDto.getComment(), allReimbursements.get(0).getComment());
	}
	
	
	@Test
	void testGetAllReimbursementsForManager() {
		// Arrange
		Date date = new Date();
		User user = new User(101, "Aayush", null, null, null, null, 0, null, date, null, null);
		Reimburse reimburse = new Reimburse(201, "Food", 12000, date, "URL", "INR", "My comment", Status.PENDING, "Not solved yet", user);
		Reimburse reimburse1 = new Reimburse(202, "Food", 12000, date, "URL", "INR", "Accepted", Status.PENDING, "Not solved yet", user);
		
		Reimburse reimburse2 = new Reimburse(201, "Travel", 12000, date, "URL", "INR", "My comment", Status.ACCEPTED, "Not solved yet", user);
		
		Reimburse reimburse3 = new Reimburse(202, "Food", 12000, date, "URL", "INR", "Accepted", Status.REJECTED, "Not solved yet", user);
		Reimburse reimburse4 = new Reimburse(201, "Food", 12000, date, "URL", "INR", "My comment", Status.REJECTED, "Not solved yet", user);
		Reimburse reimburse5 = new Reimburse(202, "Food", 250, date, "URL", "INR", "Accepted", Status.REJECTED, "Not solved yet", user);
		
		ReimburseOutDto reimburseOutDto = new ReimburseOutDto(201, "Food", 12000, date, "URL", "INR", Status.PENDING, "My comment", "Aayush", "Status description");
		ReimburseOutDto reimburseOutDto2 = new ReimburseOutDto(202, "Food", 12000, date, "URL", "INR", Status.PENDING, "Personal comment", "Aayush", "Accepted");
		
		ReimburseOutDto reimburseOutDto3 = new ReimburseOutDto(201, "Travel", 12000, date, "URL", "INR", Status.ACCEPTED, "My comment", "Aayush", "Status description");
		
		ReimburseOutDto reimburseOutDto4 = new ReimburseOutDto(201, "Food", 12000, date, "URL", "INR", Status.REJECTED, "My comment", "Aayush", "Status description");
		ReimburseOutDto reimburseOutDto5 = new ReimburseOutDto(202, "Food", 12000, date, "URL", "INR", Status.REJECTED, "Personal comment", "Aayush", "Accepted");
		ReimburseOutDto reimburseOutDto6 = new ReimburseOutDto(202, "Food", 250, date, "URL", "INR", Status.REJECTED, "Personal comment", "Aayush", "Accepted");
		
		List<ReimburseOutDto> prlist = Arrays.asList(reimburseOutDto, reimburseOutDto2);
		List<ReimburseOutDto> arlist = Arrays.asList(reimburseOutDto3);
		List<ReimburseOutDto> rrlist = Arrays.asList(reimburseOutDto4, reimburseOutDto5, reimburseOutDto6);
		List<Reimburse> actuallist1 = Arrays.asList(reimburse, reimburse1);
		List<Reimburse> actuallist2 = Arrays.asList(reimburse2);
		List<Reimburse> actuallist3 = Arrays.asList(reimburse4, reimburse5, reimburse3);
		
		// Act
		when(reimburseRepository.findByManagerIdAndStatus(101, Status.PENDING)).thenReturn(actuallist1);
		when(reimburseRepository.findByManagerIdAndStatus(101, Status.ACCEPTED)).thenReturn(actuallist2);
		when(reimburseRepository.findByManagerIdAndStatus(101, Status.REJECTED)).thenReturn(actuallist3);
		List<ReimburseOutDto> allReimbursements1 = reimburseServiceImpl.getAllReimbursementsByStatusForManager(101, "Pending");
		List<ReimburseOutDto> allReimbursements2 = reimburseServiceImpl.getAllReimbursementsByStatusForManager(101, "Accepted");
		List<ReimburseOutDto> allReimbursements3 = reimburseServiceImpl.getAllReimbursementsByStatusForManager(101, "Rejected");
		
		// Assert
		assertEquals(2, allReimbursements1.size());
		assertEquals(1, allReimbursements2.size());
		assertEquals(3, allReimbursements3.size());
		assertEquals(reimburse.getComment(), allReimbursements1.get(0).getComment());
		assertEquals(reimburse2.getExpenseType(), allReimbursements2.get(0).getExpenseType());
	}
	
	@Test
	void testReimburseInDtoToReimburse() {
		// Arrange
		Date date = new Date();
		Reimburse reimburse = new Reimburse(201, "Food", 12000, date, "URL", "INR", "My comment", Status.PENDING, "Not solved yet", null);
		ReimburseInDto reimburseInDto = new ReimburseInDto("Food", 12000, date, "URL", "INR", "My comment");
		
		// Act
		Reimburse finalreimburse = this.reimburseServiceImpl.reimburseInDtoToReimburse(reimburseInDto);
		
		// Assert
		assertThat(finalreimburse.getExpenseType()).isEqualTo(reimburse.getExpenseType());
	}
	
	@Test
	void testReimburseToReimburseOutDto() {
		// Arrange
		User user = new User();
		user.setName("Aayush Tiwari");
		Date date = new Date();
		Reimburse reimburse = new Reimburse(201, "Food", 12000, date, "URL", "INR", "My comment", Status.PENDING, "Not solved yet", user);
		ReimburseOutDto reimburseOutDto = new ReimburseOutDto(202,"Food", 1200, date, "URL", "INR", Status.PENDING, "My comment","Aayush Tiwari","Status description");
		
		// Act
		ReimburseOutDto finalreimburse = this.reimburseServiceImpl.reimburseToReimburseOutDto(reimburse);
		
		// Assert
		assertThat(finalreimburse.getExpenseType()).isEqualTo(reimburseOutDto.getExpenseType());
		
	}
}
