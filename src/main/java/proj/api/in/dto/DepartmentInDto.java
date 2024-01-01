package proj.api.in.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
* DTO class of Department.
*/
public class DepartmentInDto {

  /**
  * Department id field.
  */
  private int departmentId;
  /**
  * Department Name field.
  */
  @NotBlank(message = "Department name is required...")
  @Pattern(message = "Department must contain only letters",
  regexp = "^[a-zA-Z]*$")
  private String departmentName;
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
     * No argument constructor.
     */
    public DepartmentInDto() {
    }
    /**
     * All arguments constructor.
     * @param departmentIdValue id field.
     * @param departmentNameValue name field.
     */
    public DepartmentInDto(final int departmentIdValue,
      final @NotBlank(message = "Department name is required...")
      String departmentNameValue) {
        super();
        this.departmentId = departmentIdValue;
        this.departmentName = departmentNameValue;
    }
    /**
     * Department In DTO hash code method.
     */
    @Override
    public int hashCode() {
      return Objects.hash(departmentId, departmentName);
    }
    /**
    * Department In DTO equals method.
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
      DepartmentInDto other = (DepartmentInDto) obj;
      return departmentId == other.departmentId
      && Objects.equals(departmentName, other.departmentName);
    }
    /**
    * toString method.
    * @return departmentInDTO object.
    */
    @Override
    public String toString() {
      return "DepartmentInDto [departmentId=" + departmentId
      + ", departmentName=" + departmentName + "]";
    }
}
