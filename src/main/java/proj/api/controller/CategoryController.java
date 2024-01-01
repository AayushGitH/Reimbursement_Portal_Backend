package proj.api.controller;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.api.in.dto.CategoryInDto;
import proj.api.out.dto.CategoryOutDto;
import proj.api.services.CategoryService;
import proj.api.utils.Constants;

/**
* Controller for firing the url's related Category.
*/
@CrossOrigin("*")
@RestController
@RequestMapping(value = Constants.CATEGORY_BASE_URL)
public class CategoryController {

  /**
  * The categoryService object.
  */
  @Autowired
  private CategoryService categoryService;

  /**
   * The logger object.
   */
  private final Logger logger = LoggerFactory
  .getLogger(CategoryController.class);

  /**
   * Save the category method.
   *
   * @param category for saving category.
   * @return ResponseEntity(CategoryOutDto)
   */
  @PostMapping("/create")
  public ResponseEntity<CategoryOutDto> saveCategory(
  final @Valid @RequestBody CategoryInDto category) {
  logger.info("Entered the saving category method with {} category", category);
  CategoryOutDto saveCategory = categoryService.saveCategorys(category);
  logger.info("Successfully added the category... {}", category);
  return ResponseEntity.status(HttpStatus.CREATED)
  .body(saveCategory);
  }

  /**
   * Get all category method.
   *
   * @return List of CategoryOutDto.
   */
  @GetMapping("/getAll")
  public ResponseEntity<List<CategoryOutDto>> getAllCategories() {
  logger.info("Entered the all categories finding method...");

  List<CategoryOutDto> allCategories = this.categoryService.getAllCategories();

  logger.info("Successfully fetched the categories...");
  return ResponseEntity.status(HttpStatus.OK)
  .body(allCategories);
  }

  /**
   * Save the category method.
   *
   * @param categoryType for deleting category.
   * @return message.
   */
  @DeleteMapping("/delete/{categoryType}")
  public ResponseEntity<Map<String, String>> deleteCategory(
  final @PathVariable("categoryType") String categoryType) {
  logger.info("Deleting the category - {}", categoryType);

  this.categoryService.deleteCategory(categoryType);

  logger.info("Successfully deleted the category... {}", categoryType);
  Map<String, String> map = new HashMap<String, String>();
  map.put("message", Constants.DELETED_CATEGORY);
  return ResponseEntity.status(HttpStatus.OK)
  .body(map);
  }
}
