package proj.api.out.dto;

import java.util.Objects;

/**
 * DTO class of Department.
 */
public class DepartmentOutDto {

  /**
  * Department id field.
  */
  private int departmentId;
  /**
  * Department Name field.
  */
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
    public DepartmentOutDto() {
    }
    /**
     * All arguments constructor.
     * @param departmentIdValue id field.
     * @param departmentNameValue name field.
     */
    public DepartmentOutDto(final int departmentIdValue,
      final String departmentNameValue) {
      super();
      this.departmentId = departmentIdValue;
      this.departmentName = departmentNameValue;
    }
    /**
     * Department Out DTO hash code method.
     */
    @Override
    public int hashCode() {
      return Objects.hash(departmentId, departmentName);
    }
    /**
    * Department Out DTO equals method.
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
      DepartmentOutDto other = (DepartmentOutDto) obj;
      return departmentId == other.departmentId
      && Objects.equals(departmentName, other.departmentName);
    }
    /**
    * toString method.
    * @return departmentOutDTO object.
    */
    @Override
    public String toString() {
      return "DepartmentOutDto [departmentId=" + departmentId
      + ", departmentName=" + departmentName + "]";
    }
}
