package proj.api.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * Entity class of Category.
 */
@Entity
@Table(name = "category")
public class Category {
  /**
  * Category id field to store the id.
  */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int categoryId;
  /**
  * Category Type field to store the type of the category.
  */
  @Column(length = 50, nullable = false, unique = true)
  private String categoryType;
  /**
   * Category Limit field to store the limit.
   */
  @Column(nullable = false)
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
  public Category() {
  }
  /**
   * Category toString method.
   * @return category object.
   */
  @Override
  public String toString() {
    return "Category [categoryId=" + categoryId + ", categoryType="
    + categoryType + ", categoryLimit=" + categoryLimit + "]";
  }
  /**
   * All argument constructor.
   * @param categoryIdValue Category id.
   * @param categoryTypeValue Category type.
   * @param categoryLimitValue Category limit.
   */
  public Category(final int categoryIdValue,
    final String categoryTypeValue,
    final long categoryLimitValue) {
      super();
      this.categoryId = categoryIdValue;
      this.categoryType = categoryTypeValue;
      this.categoryLimit = categoryLimitValue;
  }
  /**
   * Category hash code.
   */
  @Override
  public int hashCode() {
    return Objects.hash(categoryId, categoryLimit, categoryType);
  }
  /**
  * Category equals method.
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
    Category other = (Category) obj;
    return categoryId == other.categoryId
    && categoryLimit == other.categoryLimit
    && Objects.equals(categoryType, other.categoryType);
  }
}
