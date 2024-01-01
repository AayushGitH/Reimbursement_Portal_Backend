package proj.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.api.out.dto.UserOutDto;
import proj.api.services.UserService;
import proj.api.utils.Constants;

/**
* Controller for firing the url's related department.
*/
@CrossOrigin
@RestController
@RequestMapping(value = Constants.EMPLOYEE_BASE_URL)
public class EmployeeController {
  /**
  * The userService object.
  */
  @Autowired
  private UserService userService;
  /**
   * The logger object.
   */
  private final Logger logger = LoggerFactory
  .getLogger(CategoryController.class);
  /**
   * Get the users according to the role.
   *
   * @param role for getting the user.
   * @return ResponseEntity(Users)
   */
  @GetMapping("/get/{role}")
  public ResponseEntity<List<UserOutDto>> getUsersByRole(
  final @PathVariable("role") String role) {
    logger.info("Entered the get users method for the role - {}", role);
    logger.info("Fetching the users from the service");
    List<UserOutDto> users = this.userService.getUserByRole(role);
    logger.info("Successfully fetched the users");
    return ResponseEntity.status(HttpStatus.OK).body(users);
  }
  /**
   * Assign the manager.
   *
   * @param managerEmail for getting the manager.
   * @param userEmail for getting the user.
   * @return ResponseEntity(Users)
   */
  @PostMapping("/assign/{userEmail}/{managerEmail}")
  public ResponseEntity<Map<String, String>> assignUserManager(
  final @PathVariable("userEmail") String userEmail,
  final @PathVariable("managerEmail") String managerEmail) {
    logger.info("Employee Email came is {} and manager email came"
    + "is {}", userEmail, managerEmail);
    System.out.println(userEmail + " " + managerEmail);
    String message = this.userService.assignEmployee(userEmail, managerEmail);
    logger.info("{} is successfully assigned with {}", userEmail, managerEmail);
    Map<String, String> map = new HashMap<String, String>();
    map.put("message", message);
    return ResponseEntity.status(HttpStatus.OK).body(map);
  }
}
