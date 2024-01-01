package proj.api.in.dto;

import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * In DTO class of Category.
 */
public class CategoryInDto {
  /**
  * Category Id field.
  */
  private int categoryId;
  /**
   * Category Type field to store the type of the category.
   */
  @NotBlank(message = "Category type is required")
  @Pattern(message = "Category must contain only letters",
  regexp = "^[a-zA-Z ]*$")
  private String categoryType;
  /**
   * Category Limit field to store the limit.
   */
  @NotNull(message = "Category limit is required")
  @Min(value = 99, message = "Limit amount must be greater than 100")
  private Long categoryLimit;
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
  public CategoryInDto() {
  }
  /**
  * All argument constructor.
  * @param categoryIdValue Category id.
  * @param categoryTypeValue Category type.
  * @param categoryLimitValue Category limit.
  */
  public CategoryInDto(final int categoryIdValue,
    final @NotBlank String categoryTypeValue,
    final @NotNull long categoryLimitValue) {
      super();
      this.categoryId = categoryIdValue;
      this.categoryType = categoryTypeValue;
      this.categoryLimit = categoryLimitValue;
  }
  /**
   * Category In DTO hash code method.
   */
  @Override
  public int hashCode() {
    return Objects.hash(categoryId, categoryLimit, categoryType);
  }
  /**
  * Category In DTO equals method.
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
    CategoryInDto other = (CategoryInDto) obj;
    return Objects.equals(categoryId, other.categoryId)
    && Objects.equals(categoryLimit, other.categoryLimit)
    && Objects.equals(categoryType, other.categoryType);
  }
  /**
  * Category In DTO toString method.
  * @return categoryInDTO object.
  */
  @Override
  public String toString() {
    return "CategoryInDto [categoryId=" + categoryId
    + ", categoryType=" + categoryType + ", categoryLimit="
    + categoryLimit + "]";
  }
}
