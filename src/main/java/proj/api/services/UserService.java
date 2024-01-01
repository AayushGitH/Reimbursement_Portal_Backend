package proj.api.services;

import java.util.List;
import proj.api.in.dto.UserInDto;
import proj.api.out.dto.UserOutDto;

/**
 * User service for declaring the user repository methods.
 */
public interface UserService {

  /**
   * Save method for registering the user.
   *
   * @param user User type parameter.
   * @return User type.
   */

  UserOutDto saveUser(UserInDto user);

  /**
   * Get method for getting the user during login.
   *
   * @param email User email parameter.
   * @param password User password parameter.
   * @return User type.
   */
  UserOutDto getUserByEmailAndPassword(String email, String password);
  /**
   * Get method for getting the user during login.
   *
   * @param role For fetching specific role employees.
   * @return List of Users.
   */
  List<UserOutDto> getUserByRole(String role);
  /**
   * Assign method of the user.
   *
   * @param name User name.
   * @param managerName User name.
   * @return String type.
   */
  String assignEmployee(String name, String managerName);
}
