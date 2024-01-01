package proj.api.out.dto;

import java.util.Date;
import java.util.Objects;

import proj.api.entities.Designation;

/**
* DTO class of User.
*/
public class UserOutDto {

  /**
  *  Id field for giving the user id.
  */
  private int userId;
  /**
   *  Name field for giving the name.
   */
  private String name;
  /**
   *  Manager name field for giving the manager name.
   */
  private String managerName;
  /**
   *  Email field for giving the email of the user.
   */
  private String email;
  /**
   *  Role field for giving the role.
   */
  private Designation role;
  /**
   *  Contact field for giving the contact of the user.
   */
  private String contact;
  /**
   * Manager id field for giving the id of the manager assigned.
   */
  private int managerId;
  /**
   *  Joining date field for giving the date of joining.
   */
  private Date joiningDate;
  /**
   *  Department field for giving the department object.
   */
  private DepartmentOutDto department;
  /**
   * User Id getter.
   * @return userId.
   */
   public int getUserId() {
     return userId;
   }
   /**
   * User Id setter.
   * @param value User id.
   */
   public void setUserId(final int value) {
     this.userId = value;
   }
  /**
   * User name getter.
   * @return name.
   */
   public String getManagerName() {
     return managerName;
   }
   /**
   * User name setter.
   * @param value User name.
   */
   public void setManagerName(final String value) {
     this.managerName = value;
   }
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
  * @return department out DTO.
  */
  public DepartmentOutDto getDepartment() {
    return department;
  }
  /**
  * Department setter.
  * @param value Department.
  */
  public void setDepartment(final DepartmentOutDto value) {
    this.department = value;
  }
  /**
   * No argument constructor.
   */
  public UserOutDto() {
  }
  /**
  * All argument constructor.
  * @param userIdValue User id.
  * @param nameValue User name.
  * @param emailValue User email.
  * @param roleValue User role.
  * @param contactValue User contact.
  * @param managerIdValue managerId.
  * @param managerNameValue managerId.
  * @param joiningDateValue User joining date.
  * @param departmentValue Department object.
  */
  public UserOutDto(
    final int userIdValue,
    final String nameValue,
    final String emailValue,
    final Designation roleValue,
    final String contactValue,
    final int managerIdValue,
    final Date joiningDateValue,
    final String managerNameValue,
    final DepartmentOutDto departmentValue) {
      super();
      this.userId = userIdValue;
      this.name = nameValue;
      this.email = emailValue;
      this.role = roleValue;
      this.contact = contactValue;
      this.managerId = managerIdValue;
      this.managerName = managerNameValue;
      this.joiningDate = new Date(joiningDateValue.getTime());
      this.department = departmentValue;
  }
  /**
   * User Out DTO hash code method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(contact, department, email, joiningDate,
    managerId, managerName, name, role, userId);
  }
  /**
  * User Out DTO equals method.
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
    UserOutDto other = (UserOutDto) obj;
    return Objects.equals(contact, other.contact)
    && Objects.equals(department, other.department)
    && Objects.equals(email, other.email)
    && Objects.equals(joiningDate, other.joiningDate)
    && managerId == other.managerId
    && Objects.equals(name, other.name)
    && Objects.equals(managerName, other.managerName)
    && role == other.role && userId == other.userId;
  }
  /**
  * To String method.
  * @return userOutDTO object.
  */
  @Override
  public String toString() {
    return "UserOutDto [userId=" + userId + ", name="
    + name + ", email=" + email
    + ", role=" + role + ", contact="
    + contact + ", managerId="
    + managerId + ", joiningDate="
    + joiningDate + ", department="
    + department + ", managerName=" + managerName + "]";
  }
}
