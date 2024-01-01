package proj.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj.api.entities.Department;
import proj.api.entities.Designation;
import proj.api.entities.User;
import proj.api.exception.AlreadyExistException;
import proj.api.exception.BadRequestException;
import proj.api.exception.InvalidCredentialException;
import proj.api.exception.ResourceNotFoundException;
import proj.api.exception.UserNotFoundException;
import proj.api.in.dto.UserInDto;
import proj.api.out.dto.DepartmentOutDto;
import proj.api.out.dto.UserOutDto;
import proj.api.repository.UserRepository;
import proj.api.services.UserService;
import proj.api.utils.Constants;
import proj.api.utils.ErrorConstants;

/**
 * User service implementation class
 * for defining the methods of user service.
 */
@Service
public class UserServiceImpl implements UserService {

  /**
   * The userRepository object.
   */
  @Autowired
  private UserRepository userRepository;
  /**
   * The departmentService object.
   */
  @Autowired
  private DepartmentServiceImpl departmentService;
  /**
   * The logger object.
   */
  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  /**
   * Save user method.
   * @param userInDto for User type request.
   * @return UserDto object.
   */
  @Override
  public UserOutDto saveUser(final UserInDto userInDto)
  throws ResourceNotFoundException {
    logger.info("The user received is {}", userInDto);
    logger.info("Mapping the UserInDto object into user object");
    Boolean isMailExists = userRepository
    .existsByEmailContainingIgnoreCase(userInDto.getEmail());
    if (isMailExists) {
      throw new AlreadyExistException(ErrorConstants.EMAIL_ALREADY_EXISTS);
    }
    userInDto.setManagerId(1);
    User user = this.userInDtoToUser(userInDto);
    logger.info("Saving the user into database...");
    User savedUser = this.userRepository.save(user);
    UserOutDto finalUser = this.userToUserOutDto(savedUser);
    Department department = this.departmentService
    .getDepartmentById(userInDto.getDepartment().getDepartmentId());
    if (department == null) {
      throw new ResourceNotFoundException(ErrorConstants
      .DEPARTMENT_NOT_FOUND);
    }
    DepartmentOutDto departmentOutDto =
    this.departmentService.departmentToDepartmentOutDto(department);
    finalUser.setDepartment(departmentOutDto);
    logger.info("Successfully saved the user");
    return finalUser;
  }

  /**
   * Get method for getting the user during login.
   *
   * @param email User email parameter.
   * @param password User password parameter.
   * @return User type.
   */
  @Override
  public UserOutDto getUserByEmailAndPassword(final String email,
  final String password) {
    logger.info("Entering the login method with {} and {} ...",
    email, password);
    userRepository.findByEmail(email)
    .orElseThrow(() -> new InvalidCredentialException(
    ErrorConstants.USER_NOT_FOUND));
    User user = this.userRepository.findByEmailAndPassword(email, password)
    .orElseThrow(() -> new InvalidCredentialException(
    ErrorConstants.NOT_VALID_PASSWORD));
    UserOutDto userOutDto = this.userToUserOutDto(user);
    logger.info("User is successfully logged in");
    return userOutDto;
  }
  /**
   * Get method for getting the users by their role.
   *
   * @param role Employees role.
   * @return List of Users.
   */
  @Override
  public List<UserOutDto> getUserByRole(final String role) {
    logger.info("Started fetching the users with the role - {} ", role);
    List<User> users = null;
    if (role.equals(Constants.EMPLOYEE)) {
      users = this.userRepository.findByRole(Designation.EMPLOYEE);
    } else  if (role.equals(Constants.MANAGER)) {
      users = this.userRepository.findByRole(Designation.MANAGER);
    } else {
      throw new BadRequestException(ErrorConstants.INVALID_REQUEST);
    }
    List<UserOutDto> finalUsers = users.stream().map(
    user -> this.userToUserOutDto(user))
    .collect(Collectors.toList());
    logger.info("Started fetching the users with the role - {} ", role);
    return finalUsers;
  }
  /**
   * Assigning method.
   *
   * @param name Employee name.
   * @param managerName Manager name.
   * @return Message.
   */
  @Override
  public String assignEmployee(final String name, final String managerName) {
  User user = this.userRepository.findByEmail(name)
    .orElseThrow(() -> new UserNotFoundException(
    ErrorConstants.USER_NOT_FOUND));
  System.out.println(user.getUserId());
  User manager = this.userRepository.findByEmail(managerName)
  .orElseThrow(() -> new UserNotFoundException(
  String.format(ErrorConstants.MANAGER_NOT_FOUND)));
  System.out.println(manager.getUserId());
  user.setManagerId(manager.getUserId());
  this.userRepository.save(user);
  return Constants.ASSIGNED_EMPLOYEE_MESSAGE;
  }
  /**
   * Model mapper method.
   *
   * @param user User instance.
   * @return UserOutDto.
   */
  public UserOutDto userToUserOutDto(final User user) {
    UserOutDto userOutDto = new UserOutDto();
    userOutDto.setUserId(user.getUserId());
    userOutDto.setName(user.getName());
    userOutDto.setEmail(user.getEmail());
    userOutDto.setRole(user.getRole());
    userOutDto.setContact(user.getContact());
    userOutDto.setManagerId(user.getManagerId());
    userOutDto.setJoiningDate(user.getJoiningDate());
    DepartmentOutDto departmentOutDto = new DepartmentOutDto();
    departmentOutDto.setDepartmentId(user.getDepartment().getDepartmentId());
    departmentOutDto.setDepartmentName(user.getDepartment()
    .getDepartmentName());
    User manager = this.userRepository.findById(user.getManagerId())
    .orElseThrow(() -> new UserNotFoundException(
    ErrorConstants.USER_NOT_FOUND));
    userOutDto.setManagerName(manager.getName());
    userOutDto.setDepartment(departmentOutDto);
    return userOutDto;
  }
  /**
   * Model mapper method.
   *
   * @param userInDto UserInDTO instance.
   * @return User.
   */
  public User userInDtoToUser(final UserInDto userInDto) {
    final User user = new User();
    user.setName(userInDto.getName());
    user.setRole(userInDto.getRole());
    user.setEmail(userInDto.getEmail());
    user.setJoiningDate(userInDto.getJoiningDate());
    user.setContact(userInDto.getContact());
    user.setManagerId(userInDto.getManagerId());
    user.setPassword(userInDto.getPassword());
    user.setSecretAnswer(userInDto.getSecretAnswer());
    if (userInDto.getDepartment() == null) {
      throw new ResourceNotFoundException(ErrorConstants
      .DEPARTMENT_NOT_FOUND);
    }
    final Department department = new Department();
    department.setDepartmentId(userInDto.getDepartment().getDepartmentId());
    department.setDepartmentName(userInDto.getDepartment().getDepartmentName());
    user.setDepartment(department);
    return user;
  }
}
