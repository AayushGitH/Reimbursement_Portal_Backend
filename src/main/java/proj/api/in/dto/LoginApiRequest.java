package proj.api.in.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Login API Request class.
 */
public class LoginApiRequest {

  /**
  * Email.
  */
  @Email
  @NotBlank
  private String email;

  /**
   * Password.
   */
  @NotBlank
  private String password;
  /**
   * Getter of email.
   * @return Email.
   */
  public String getEmail() {
    return email;
  }
  /**
  * Setter of email.
  * @param emailValue User Email.
  */
  public void setEmail(final String emailValue) {
    this.email = emailValue;
  }
  /**
  * Getter of Password.
  * @return password.
  */
  public String getPassword() {
    return password;
  }
  /**
  * Setter of password.
  * @param passwordValue Password field.
  */
  public void setPassword(final String passwordValue) {
    this.password = passwordValue;
  }
  /**
  * No argument constructor.
  */
  public LoginApiRequest() {
  }
}
