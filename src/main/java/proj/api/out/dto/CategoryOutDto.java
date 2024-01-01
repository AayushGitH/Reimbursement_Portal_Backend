package proj.api.out.dto;

import java.util.Objects;

/**
* DTO class of Category.
*/
public class CategoryOutDto {
  /**
  * Category id field.
  */
  private int categoryId;
  /**
  * Category type field.
  */
  private String categoryType;

  /**
   * Category limit field.
   */
  private long categoryLimit;
  /**
   * Category id getter.
   * @return category id.
   */
  public int getCategoryId() {
    return categoryId;
  }
  /**
  * Category id setter.
  * @param value category id.
  */
  public void setCategoryId(final int value) {
    this.categoryId = value;
  }
  /**
  * Category type getter.
  * @return category type.
  */
  public String getCategoryType() {
    return categoryType;
  }
  /**
  * Category type setter.
  * @param value category type.
  */
  public void setCategoryType(final String value) {
    this.categoryType = value;
  }
  /**
  * Category limit getter.
  * @return category type.
  */
  public long getCategoryLimit() {
    return categoryLimit;
  }
  /**
  * Category limit setter.
  * @param value category limit.
  */
  public void setCategoryLimit(final long value) {
    this.categoryLimit = value;
  }
  /**
  * No argument constructor.
  */
  public CategoryOutDto() {
  }
  /**
  * All argument constructor.
  * @param categoryIdValue Category id.
  * @param categoryTypeValue Category type.
  * @param categoryLimitValue Category limit.
  */
  public CategoryOutDto(final int categoryIdValue,
    final String categoryTypeValue,
    final long categoryLimitValue) {
      super();
      this.categoryId = categoryIdValue;
      this.categoryType = categoryTypeValue;
      this.categoryLimit = categoryLimitValue;
  }
  /**
   * Category Out DTO hash code method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(categoryId, categoryLimit, categoryType);
  }
  /**
  * Category Out DTO equals method.
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
    CategoryOutDto other = (CategoryOutDto) obj;
    return categoryId == other.categoryId
    && categoryLimit == other.categoryLimit
    && Objects.equals(categoryType, other.categoryType);
  }
  /**
  * Category Out DTO toString method.
  * @return categoryOutDTO object.
  */
  @Override
  public String toString() {
    return "CategoryOutDto [categoryId=" + categoryId
    + ", categoryType=" + categoryType
    + ", categoryLimit=" + categoryLimit + "]";
  }
}
