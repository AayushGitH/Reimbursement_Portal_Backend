package proj.api.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import proj.api.entities.Designation;
import proj.api.entities.User;

/**
 * User repository calling the data from the database.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
  * JPA method for getting the user with given email and password.
  * @param email User email.
  * @param password User password
  * @return User.
  */
  Optional<User> findByEmailAndPassword(String email, String password);
  /**
   * JPA method for getting the users with given role.
   * @param role Role of employees.
   *
   * @return List of users.
   */
  List<User> findByRole(Designation role);
  /**
   * JPA method for getting the user with given name.
   * @param name User name.
   *
   * @return User.
   */
   Optional<User> findByName(String name);
   /**
    * JPA method for getting the user with given email.
    * @param email User email.
    *
    * @return User.
    */
    Optional<User> findByEmail(String email);
   /**
    * JPA method for getting the boolean value.
    * @param email User email.
    *
    * @return Boolean value.
    */
    Boolean existsByEmailContainingIgnoreCase(String email);
}
