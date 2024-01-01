package proj.api.in.dto;

import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import proj.api.entities.Designation;

/**
 * In DTO class of User.
 */
public class UserInDto {
  /**
  * User name field to store the name of the user.
  */
  @Size(min = 5, max = 30, message = "Name size must be"
  + "in between 5 and 30")
  @NotBlank(message = "Name is required")
  @Pattern(message = "Name must contain only letters",
  regexp = "^[A-Za-z ]*$")
  private String name;
  /**
  * User email field to store the user email.
  */
  @Email(regexp = "^[a-zA-Z0-9+_.-]+@nucleusTeq.com$",
  message = "Email must be matched with the predefined criteria")
  @NotBlank(message = "Email is required")
  private String email;
  /**
  * Password field to store the password.
  */
  @NotBlank(message = "Password is required...")
  @Size(min = 8, max = 25, message = "Password size must be"
  + "in between 8 and 25")
  private String password;
  /**
  * User role field to store the designation of the user.
  */
  @NotNull(message = "Role is required")
  private Designation role;
  /**
  * Contact field to store the users contact.
  */
  @Pattern(regexp = "^[0-9]+", message = "Contact must contain only digits")
  @Size(min = 10, max = 10, message = "Contact must be of 10 digits")
  private String contact;
  /**
  * Manager id field to store the id of the manager.
  */
  private int managerId;
  /**
  * Secret answer field to store the secret answer.
  */
  @NotBlank(message = "Secret answer is required")
  private String secretAnswer;
  /**
  * Joining date field to store the joining date.
  */
  @Past(message = "Joining date must be of past")
  private Date joiningDate;
  /**
   * Department field to link the user to the department.
   */
  private DepartmentInDto department;
  /**
   * User name getter.
   * @return name.
   */
   public String getName() {
     return name;
   }
   /**
   * User name setter.
   * @param value User name.
   */
   public void setName(final String value) {
     this.name = value;
   }
   /**
   * User email getter.
   * @return email.
   */
   public String getEmail() {
     return email;
   }
   /**
   * User email setter.
   * @param value User email.
   */
   public void setEmail(final String value) {
     this.email = value;
   }
   /**
   * Password getter.
   * @return password.
   */
   public String getPassword() {
     return password;
   }
   /**
   * Password setter.
   * @param value password.
   */
   public void setPassword(final String value) {
     this.password = value;
   }
   /**
   * User designation getter.
   * @return role.
   */
   public Designation getRole() {
     return role;
   }
   /**
   * User designation setter.
   * @param value User role.
   */
   public void setRole(final Designation value) {
     this.role = value;
   }
   /**
   * User contact getter.
   * @return contact.
   */
   public String getContact() {
     return contact;
   }
   /**
   * User contact setter.
   * @param value User contact.
   */
   public void setContact(final String value) {
     this.contact = value;
   }
   /**
   * Manager id getter.
   * @return managerId.
   */
   public int getManagerId() {
     return managerId;
   }
   /**
   * Manager id setter.
   * @param value Managerid.
   */
   public void setManagerId(final int value) {
     this.managerId = value;
   }
   /**
   * Secret answer getter.
   * @return secret answer.
   */
   public String getSecretAnswer() {
     return secretAnswer;
   }
   /**
   * Secret answer setter.
   * @param value Secret answer.
   */
   public void setSecretAnswer(final String value) {
     this.secretAnswer = value;
   }
   /**
   * Joining date getter.
   * @return Joining date.
   */
   public Date getJoiningDate() {
    Date date = new Date(joiningDate.getTime());
    return date;
   }
   /**
   * Joining date setter.
   * @param value Joining date.
   */
   public void setJoiningDate(final Date value) {
    Date date = new Date(value.getTime());
    this.joiningDate = date;
   }
   /**
    * Department getter.
    * @return department in DTO.
    */
  public DepartmentInDto getDepartment() {
    return department;
  }
  /**
  * Department setter.
  * @param value Department.
  */
  public void setDepartment(final DepartmentInDto value) {
    this.department = value;
  }
  /**
   * toString method.
   * @return userindto object.
   */
  @Override
  public String toString() {
    return "UserInDto [name=" + name + ", email="
       + email + ", password=" + password
       + ", role=" + role + ", contact="
       + contact + "," + " managerId="
       + managerId + ", secretAnswer="
       + secretAnswer + ", joiningDate="
       + joiningDate + ", department=" + department + "]";
  }
  /**
  * No argument constructor.
  */
  public UserInDto() {
  }
  /**
  * All argument constructor.
  * @param nameValue User name.
  * @param emailValue User email.
  * @param passwordValue Password field.
  * @param roleValue User role.
  * @param contactValue User contact.
  * @param managerIdValue managerId.
  * @param secretAnswerValue secret answer field.
  * @param joiningDateValue User joining date.
  * @param departmentValue Department object.
  */
  public UserInDto(
    final @NotBlank(message = "Name is required...") String nameValue,
    final @Email @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@nucleusTeq.com$",
    message = "Email must match the predefined criteria") String emailValue,
    final @NotBlank(message = "Password is required...")
    @Size(min = 8, max = 25) String passwordValue,
    final @NotBlank Designation roleValue,
    final String contactValue,
    final int managerIdValue,
    final @NotBlank(message = "Secret answer is required...")
    String secretAnswerValue,
    final Date joiningDateValue,
    final DepartmentInDto departmentValue) {
      super();
      this.name = nameValue;
      this.email = emailValue;
      this.password = passwordValue;
      this.role = roleValue;
      this.contact = contactValue;
      this.managerId = managerIdValue;
      this.secretAnswer = secretAnswerValue;
      this.joiningDate = new Date(joiningDateValue.getTime());
      this.department = departmentValue;
  }
  /**
   * User In DTO hash code method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(contact, department, email,
    joiningDate, managerId,
    name, password, role, secretAnswer);
  }
  /**
  * User In DTO equals method.
  * @param obj object.
  */
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    UserInDto other = (UserInDto) obj;
    return Objects.equals(contact, other.contact)
    && Objects.equals(department, other.department)
    && Objects.equals(email, other.email)
    && Objects.equals(joiningDate, other.joiningDate)
    && managerId == other.managerId && Objects.equals(name, other.name)
    && Objects.equals(password, other.password) && role == other.role
    && Objects.equals(secretAnswer, other.secretAnswer);
  }
}
