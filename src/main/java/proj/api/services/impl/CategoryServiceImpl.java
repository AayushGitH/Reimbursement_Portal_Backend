package proj.api.services.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj.api.entities.Category;
import proj.api.exception.AlreadyExistException;
import proj.api.exception.ResourceNotFoundException;
import proj.api.in.dto.CategoryInDto;
import proj.api.out.dto.CategoryOutDto;
import proj.api.repository.CategoryRepository;
import proj.api.services.CategoryService;
import proj.api.utils.ErrorConstants;

/**
 * Category service implementation class.
 * for defining the methods from Category service.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

  /**
  * The categoryRepository object.
  */
  @Autowired
  private CategoryRepository categoryRepository;
  /**
   * The logger object.
   */
  private final Logger logger = LoggerFactory
  .getLogger(CategoryServiceImpl.class);
  /**
   * Create category method.
   * @param category Category type parameter.
   * @return CategoryOutDto object.
   */
  @Override
  public CategoryOutDto saveCategorys(final CategoryInDto category) {
    logger.info("Category received is {}", category);
    String upperCaseCategory = category.getCategoryType()
    .toUpperCase(Locale.ENGLISH);
    category.setCategoryType(upperCaseCategory);
    Boolean exists = this.categoryRepository.existsByCategoryType(
    category.getCategoryType());
    if (exists) {
      throw new AlreadyExistException(ErrorConstants.CATEGORY_ALREADY_EXISTS);
    }
    Category tosaveCategory = this.categoryInDtoToCategory(category);
    Category savedCategory = this.categoryRepository.save(tosaveCategory);
    logger.info("Category is stored successfully !!");
    return this.categoryToCategoryOutDto(savedCategory);
  }
  /**
   * Get all categories method definition.
   *
   * @return List of Categories.
   */
  @Override
  public List<CategoryOutDto> getAllCategories() {
    logger.info("All categories fetching method is calling...");
    List<Category> categoryList = this.categoryRepository.findAll();
    List<CategoryOutDto> categoryOutDtoList = categoryList.stream().map(
    category -> this.categoryToCategoryOutDto(category))
    .collect(Collectors.toList());
    logger.info("All categories is successfully fetched...");
    return categoryOutDtoList;
  }
  /**
   * Delete the category by id.
   * @param categoryType for getting the category type.
   */
  @Override
  public void deleteCategory(final String categoryType) {
    logger.info("Entered into the method of deleting the category...");
    Category category = this.categoryRepository
    .findByCategoryType(categoryType).orElseThrow(
    () -> new ResourceNotFoundException("Category", categoryType));
    logger.info("Successfully fetched the category...");
    this.categoryRepository.deleteById(category.getCategoryId());
  }
  /**
   * Category model mapper.
   * @param categoryInDto for getting the categoryindto.
   * @return category.
   */
  public Category categoryInDtoToCategory(final CategoryInDto categoryInDto) {
    Category category = new Category();
    category.setCategoryId(categoryInDto.getCategoryId());
    category.setCategoryLimit(categoryInDto.getCategoryLimit());
    category.setCategoryType(categoryInDto.getCategoryType());
    return category;
  }
  /**
   * Category model mapper.
   * @param category for getting the category.
   * @return categoryoutdto.
   */
  public CategoryOutDto categoryToCategoryOutDto(final Category category) {
    CategoryOutDto categoryOutDto = new CategoryOutDto();
    categoryOutDto.setCategoryId(category.getCategoryId());
    categoryOutDto.setCategoryLimit(category.getCategoryLimit());
    categoryOutDto.setCategoryType(category.getCategoryType());
    return categoryOutDto;
  }
}
