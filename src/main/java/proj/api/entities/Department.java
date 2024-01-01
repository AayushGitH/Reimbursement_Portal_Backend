package proj.api.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

/**
 * Entity class of Department.
 * To store the departments.
 */
@Entity
@Table(name = "department")
public class Department {

  /**
  * Department id field to store the id.
  */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int departmentId;
  /**
   * Department Name field to store the name of the department.
   */
  @Column(length = 50, nullable = false, unique = true)
  private String departmentName;
  /**
   * User object for linking the users to the particular department.
   */
  @JsonManagedReference
  @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
  private List<User> users;
  /**
  * Department id getter.
  * @return department id.
  */
  public int getDepartmentId() {
    return departmentId;
  }
  /**
  * Department id setter.
  * @param value department id.
  */
  public void setDepartmentId(final int value) {
    this.departmentId = value;
  }
  /**
  * Department name getter.
  * @return department name.
  */
  public String getDepartmentName() {
    return departmentName;
  }
  /**
  * Department name setter.
  * @param value department name.
  */
  public void setDepartmentName(final String value) {
    this.departmentName = value;
  }
  /**
  * Users getter.
  * @return users.
  */
  public List<User> getUsers() {
    return users;
  }
  /**
  * Users list setter.
  * @param value users list.
  */
  public void setUsers(final List<User> value) {
    this.users = value;
  }
  /**
   * No argument constructor.
   */
  public Department() {
  }
  /**
   * toString method.
   * @return department object.
   */
  @Override
  public String toString() {
    return "Department [departmentId=" + departmentId
    + ", departmentName="
    + departmentName + ", users=" + users + "]";
  }
  /**
   * All arguments constructor.
   * @param departmentIdValue id field.
   * @param departmentNameValue name field.
   * @param usersValue users field.
   */
  public Department(final int departmentIdValue,
    final String departmentNameValue,
    final List<User> usersValue) {
      super();
      this.departmentId = departmentIdValue;
      this.departmentName = departmentNameValue;
      this.users = usersValue;
  }
  /**
   * Department hash code.
   */
  @Override
  public int hashCode() {
    return Objects.hash(departmentId, departmentName, users);
  }
  /**
  * Department object equals method.
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
    Department other = (Department) obj;
    return departmentId == other.departmentId
    && Objects.equals(departmentName, other.departmentName)
    && Objects.equals(users, other.users);
  }
}
