package proj.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import proj.api.entities.Department;
import proj.api.entities.Designation;
import proj.api.entities.User;
import proj.api.exception.AlreadyExistException;
import proj.api.exception.BadRequestException;
import proj.api.exception.InvalidCredentialException;
import proj.api.exception.ResourceNotFoundException;
import proj.api.in.dto.DepartmentInDto;
import proj.api.in.dto.UserInDto;
import proj.api.out.dto.DepartmentOutDto;
import proj.api.out.dto.UserOutDto;
import proj.api.repository.DepartmentRepository;
import proj.api.repository.UserRepository;
import proj.api.services.impl.DepartmentServiceImpl;
import proj.api.services.impl.UserServiceImpl;
import proj.api.utils.Constants;
import proj.api.utils.ErrorConstants;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

 
  @Mock
  private UserRepository userRepository;
  
  @Mock
  private DepartmentRepository departmentRepository;
  
  @InjectMocks
  private UserServiceImpl userService;
  
  @Mock
  private DepartmentServiceImpl departmentService;

  
  @Test
  @DisplayName("Registration test method")
  void testregistration() {
  // Arrange
	  Date date = new Date();
  DepartmentInDto departmentInDto = new DepartmentInDto(101, "HR");
  UserInDto userInDto = new UserInDto("Test", "test@nucleusTeq.com", "Test@123", Designation.ADMIN, "1234567890", 0, "Test secret", date, departmentInDto);
  Department department = new Department(101, "HR", null);
  User user = new User(0, "Test", "test@nucleusTeq.com", "Test@123", Designation.ADMIN, "1234567890", 0, "Test secret", date, department, null);
  DepartmentOutDto departmentOutDto = new DepartmentOutDto(101, "HR");
  UserOutDto userOutDto = new UserOutDto(0, "Test", "test@nucleusTeq.com", Designation.ADMIN, "1234567890", 0, date, "Test", departmentOutDto);

  // Act
  when(userRepository.save(any(User.class))).thenReturn(user);
  when(departmentService.getDepartmentById(101)).thenReturn(department);
  when(departmentService.departmentToDepartmentOutDto(department)).thenReturn(departmentOutDto);
  when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(user));
  UserOutDto saveUser = userService.saveUser(userInDto);

  // Assert
  assertThat(saveUser.getContact()).isEqualTo(userOutDto.getContact());
  }
  
  @Test
  void testregistrationWithExistingEmail() {
  // Arrange
  DepartmentInDto departmentInDto = new DepartmentInDto(101, "HR");
  Date date = new Date();
  UserInDto user = new UserInDto("Test", "aayush@nucleusTeq.com", "Test@123", Designation.ADMIN, "1234567890", 0, "Test secret", date, departmentInDto);

  // Act
  when(userRepository.existsByEmailContainingIgnoreCase("aayush@nucleusTeq.com")).thenReturn(true);

  // Assert
  assertThrows(AlreadyExistException.class, () -> userService.saveUser(user));
  }
  
  @Test
  void testregistrationWithNullDepartment() {
  // Arrange
	  Date date = new Date();
  UserInDto user = new UserInDto("Test", "aayush@nucleusTeq.com", "Test@123", Designation.ADMIN, "1234567890", 0, "Test secret", date, null);

  // Act
  when(userRepository.existsByEmailContainingIgnoreCase("aayush@nucleusTeq.com")).thenReturn(false);

  // Assert
  assertThrows(ResourceNotFoundException.class, () -> userService.saveUser(user));
  }
  
  @Test
  @DisplayName("Login test method")
  void testloginSuccess() {
  // Arrange
  String email = "rocky@nucleusTeq.com";
  String password = "Aayush@123";
  Department department = new Department(110, "HR", null);
  Date date = new Date();
  User user = new User(0, "Aayush", "rocky@nucleusTeq.com",
  "Aayush@123", Designation.ADMIN, "982898239", 0, "Aayush456", date, department, null);

  // Act
  when(userRepository.findByEmailAndPassword(email,password)).thenReturn(Optional.ofNullable(user));
  when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(user));
  when(userRepository.findByEmail(email)).thenReturn(Optional.ofNullable(user));
  UserOutDto loggedInUser = userService.getUserByEmailAndPassword(email, password);

  // Assert
  assertThat(loggedInUser.getName()).isEqualTo("Aayush");
  assertThat(loggedInUser).isNotNull();
  }
  
  @Test
  void testloginFailurePassword() {
  // Arrange
  String email = "rocky@nucleusTeq.com";
  String password = "Aayush@123";

  // Act
  when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

  // Assert
  assertThrows(InvalidCredentialException.class, ()->userService.getUserByEmailAndPassword(email, password));
  }
  
  @Test
  @DisplayName("Get user by role method test")
  void testGetUserByRole() {
  // Arrange
  	Department department = new Department(110, "HR", null);
    DepartmentOutDto departmentOutDto = new DepartmentOutDto(110, "HR");
    Date date = new Date();
  User user1 = new User(101, "Aayush", "rocky@nucleusTeq.com",
  "Aayush@123", Designation.EMPLOYEE, "982898239", 0, "Aayush456", date, department, null);
  User user2 = new User(201, "Test", "test@nucleusTeq.com",
  "Test@123", Designation.EMPLOYEE, "1111111111", 0, "Test", date, department, null);
  UserOutDto userdto2 = new UserOutDto(201,"Test",
  "test@nucleusTeq.com", Designation.EMPLOYEE, "1111111111", 0, date, "Test", departmentOutDto);
  List<User> employees = Arrays.asList(user1,user2);
  List<User> managers = Arrays.asList(user1,user2);

  // Act
  when(userRepository.findByRole(Designation.EMPLOYEE)).thenReturn(employees);
  when(userRepository.findByRole(Designation.MANAGER)).thenReturn(managers);
  when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(user1));
  List<UserOutDto> finalUsers = userService.getUserByRole("Employee");
  List<UserOutDto> finalManagers = userService.getUserByRole("Manager");
  
  // Assert
  assertThat(finalManagers).isNotNull();
  assertThat(finalUsers).isNotNull();
  assertThat(finalUsers.size()).isEqualTo(2);
  assertThat(finalManagers.size()).isEqualTo(2);
  assertThat(finalUsers.get(1).getEmail()).isEqualTo(userdto2.getEmail());
  assertThrows(BadRequestException.class, ()->userService.getUserByRole("Man"));
  }
 
  @Test
  @DisplayName("Assign employee method test")
  void testAssignEmployee() {
  // Arrange
  String name = "Aayush";
  String managerName = "Jay";
  Date date = new Date();
  User user1 = new User(101, "Aayush", "rocky@nucleusTeq.com",
  "Aayush@123", Designation.MANAGER, "982898239", 0, "Aayush456", date, null, null);
  User user2 = new User(201, "Jay", "jay@nucleusTeq.com",
  "Jay@123", Designation.MANAGER, "982898239", 0, "Jay123", date, null, null);
  User user3 = new User(101, "Aayush", "rocky@nucleusTeq.com",
  "Aayush@123", Designation.MANAGER, "982898239", 201, "Aayush456", date, null, null);
  
  	
  // Act
  when(userRepository.findByEmail(managerName)).thenReturn(Optional.ofNullable(user2));
  when(userRepository.findByEmail(name)).thenReturn(Optional.ofNullable(user1));
  when(userRepository.save(user1)).thenReturn(user3);
  String message = userService.assignEmployee(name, managerName);
  
  // Assert
  assertThat(message).isEqualTo(Constants.ASSIGNED_EMPLOYEE_MESSAGE);
  
  }
  
  
  @Test
  @DisplayName("User In DTO to User mapper test")
  void testUserInDTOtoUser() {
  	// Arrange 
  	DepartmentInDto departmentInDto = new DepartmentInDto(101, "HR");
  	Date date = new Date();
  	UserInDto userInDto = new UserInDto("Test", "test@nucleusTeq.com", "Test@123", Designation.ADMIN, "1234567890", 0, "Test secret", date, departmentInDto);
  	Department department = new Department(101, "HR", null);
  	User user = new User(0, "Test", "test@nucleusTeq.com", "Test@123", Designation.ADMIN, "1234567890", 0, "Test secret", date, department, null);
  	
  	// Act 
    User savedUser = this.userService.userInDtoToUser(userInDto);
  	
  	// Assert
    assertThat(savedUser.getName()).isEqualTo(user.getName());
  }
  
  @Test
  void testUserInDTOtoUserFailure() {
  	// Arrange 
	  Date date = new Date();
  	UserInDto userInDto = new UserInDto("Test", "test@nucleusTeq.com", "Test@123", Designation.ADMIN, "1234567890", 0, "Test secret", date, null);
  	
  	// Assert
    assertThrows(ResourceNotFoundException.class, ()->userService.userInDtoToUser(userInDto));
  }
  
  
  @Test
  @DisplayName("User to User Out DTO mapper test")
  void testUsertoUserOutDTO() {
  	// Arrange 
    Department department = new Department(101, "HR", null);
    Date date = new Date();
    User user = new User(0, "Test", "test@nucleusTeq.com", "Test@123", Designation.ADMIN, "1234567890", 0, "Test secret", date, department, null);
    DepartmentOutDto departmentOutDto = new DepartmentOutDto(101, "HR");
    UserOutDto userOutDto = new UserOutDto(0, "Test", "test@nucleusTeq.com", Designation.ADMIN, "1234567890", 0, date, "Test", departmentOutDto);
  	
  	// Act 
    when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(user));
    UserOutDto savedUser = this.userService.userToUserOutDto(user);
  	
  	// Assert
    assertThat(userOutDto.getEmail()).isEqualTo(savedUser.getEmail());
  }
}
