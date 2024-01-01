package proj.api.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entity class of User.
 */
@Entity
@Table(name = "users")
public class User {
  /**
   * User id field to store the id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int userId;
  /**
   * User name field to store the name of the user.
   */
  @Column(length = 100, nullable = false)
  private String name;
  /**
   * User email field to store the user email.
   */
  @Column(nullable = false, unique = true)
  private String email;
  /**
   * Password field to store the password.
   */
  @Column(nullable = false)
  private String password;
  /**
   * User role field to store the designation of the user.
   */
  @Column(nullable = false)
  @Enumerated
  private Designation role;
  /**
   * Contact field to store the users contact.
   */
  @Column(length = 15)
  private String contact;
  /**
   * Manager id field to store the id of the manager.
   */
  private int managerId;
  /**
   * Secret answer field to store the secret answer.
   */
  private String secretAnswer;
  /**
   * Joining date field to store the joining date.
   */
  @Temporal(TemporalType.DATE)
  private Date joiningDate;
  /**
   * Department field to link the user to the department.
   */
  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "departmentId")
  private Department department;
  /**
   * Reimburse list to link it with the particular user.
   */
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Reimburse> requests;
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
  * @return Department.
  */
  public Department getDepartment() {
    return department;
  }
  /**
  * Department setter.
  * @param value Department.
  */
  public void setDepartment(final Department value) {
    this.department = value;
  }
  /**
  * Reimbursements getter.
  * @return list of requests.
  */
  public List<Reimburse> getRequests() {
    return requests;
  }
  /**
  * Reimbursements setter.
  * @param value List of requests.
  */
  public void setRequests(final List<Reimburse> value) {
    this.requests = value;
  }
  /**
  * No argument constructor.
  */
  public User() {
  }
  /**
   * To String method.
   * @return user object.
   */
  @Override
  public String toString() {
    return "User [userId=" + userId + ", name=" + name + ", email="
    + email + ", password=" + password + ", role="
    + role + ", contact=" + contact + ", managerId="
    + managerId + ", secretAnswer=" + secretAnswer
    + ", joiningDate=" + joiningDate + "]";
  }
  /**
  * All argument constructor.
  * @param userIdValue User id.
  * @param nameValue User name.
  * @param emailValue User email.
  * @param passwordValue Password field.
  * @param roleValue User role.
  * @param contactValue User contact.
  * @param managerIdValue managerId.
  * @param secretAnswerValue secret answer field.
  * @param joiningDateValue User joining date.
  * @param departmentValue Department object.
  * @param requestsValue list of requests.
  */
  public User(
    final int userIdValue,
    final String nameValue,
    final String emailValue,
    final String passwordValue,
    final Designation roleValue,
    final String contactValue,
    final int managerIdValue,
    final String secretAnswerValue,
    final Date joiningDateValue,
    final Department departmentValue,
    final List<Reimburse> requestsValue) {
      super();
      this.userId = userIdValue;
      this.name = nameValue;
      this.email = emailValue;
      this.password = passwordValue;
      this.role = roleValue;
      this.contact = contactValue;
      this.managerId = managerIdValue;
      this.secretAnswer = secretAnswerValue;
      this.joiningDate = new Date(joiningDateValue.getTime());
      this.department = departmentValue;
      this.requests = requestsValue;
    }
  /**
   * User object hashCode method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(contact, department, email,
    joiningDate, managerId,
    name, password, requests,
    role, secretAnswer, userId);
  }
  /**
  * User equals method.
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
    User other = (User) obj;
    return Objects.equals(contact, other.contact)
    && Objects.equals(department, other.department)
    && Objects.equals(email, other.email)
    && Objects.equals(joiningDate, other.joiningDate)
    && managerId == other.managerId && Objects.equals(name, other.name)
    && Objects.equals(password, other.password)
    && Objects.equals(requests, other.requests) && role == other.role
    && Objects.equals(secretAnswer, other.secretAnswer)
    && userId == other.userId;
  }
}
