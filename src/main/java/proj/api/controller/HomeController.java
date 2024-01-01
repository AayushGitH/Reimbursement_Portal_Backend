package proj.api.controller;


import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proj.api.in.dto.LoginInDTO;
import proj.api.in.dto.UserInDto;
import proj.api.out.dto.UserOutDto;
import proj.api.services.UserService;
import proj.api.utils.Constants;

/**
* Controller for firing the url's related registration.
*/
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.HOME_BASE_URL)
public class HomeController {

  /**
  * The userService object.
  */
  @Autowired
  private UserService userService;
  /**
   * The logger object.
   */
  private final Logger logger = LoggerFactory.getLogger(HomeController.class);
  /**
   * Save user method for registering the user.
   * @param user For getting the user as a request.
   * @return ResponseEntity(User)
   */
  @PostMapping("/register")
  public ResponseEntity<UserOutDto> saveUser(
  final @Valid @RequestBody UserInDto user) {
    logger.info("Request received to save the user for {}",
    user.toString());
    UserOutDto saveUser = userService.saveUser(user);
    logger.info("Successfully added the user .. for the user {}",
    user.toString());
    return ResponseEntity.status(HttpStatus.CREATED)
    .body(saveUser);
  }

  /**
  * Login user method for login the user.
  * @param request for users login credentials.
  * @return ResponseEntity(UserDto)
  */
  @PostMapping("/login")
  public ResponseEntity<UserOutDto> loginUser(
  final @Valid @RequestBody LoginInDTO request) {
    logger.info("Request received to login the user for {}",
    request.getEmail());
    UserOutDto userOutDto = this.userService.getUserByEmailAndPassword(
    request.getEmail(), request.getPassword());
    logger.info("Successfully logged the user with email - {}",
    request.getEmail());
    return ResponseEntity.status(HttpStatus.OK).body(userOutDto);
  }
}
