package proj.api.services;

import java.util.List;
import proj.api.in.dto.CategoryInDto;
import proj.api.out.dto.CategoryOutDto;

/**
 * Category service for declaring the category repository methods.
 */
public interface CategoryService {

  /**
  * Save category method declaration.
  *
  * @param category Category type parameter.
  * @return CategoryDto.
  */
  CategoryOutDto saveCategorys(CategoryInDto category);

  /**
   * Get all categories method declaration.
   *
   * @return List of Category.
   */
  List<CategoryOutDto> getAllCategories();

  /**
   * Delete a category method declaration.
   * @param categoryType for getting the type of category.
   */
  void deleteCategory(String categoryType);
}
