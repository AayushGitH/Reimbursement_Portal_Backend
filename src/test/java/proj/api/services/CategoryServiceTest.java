package proj.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import proj.api.entities.Category;
import proj.api.entities.Department;
import proj.api.exception.AlreadyExistException;
import proj.api.exception.ResourceNotFoundException;
import proj.api.in.dto.CategoryInDto;
import proj.api.in.dto.DepartmentInDto;
import proj.api.out.dto.CategoryOutDto;
import proj.api.repository.CategoryRepository;
import proj.api.services.impl.CategoryServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
  @Mock
  private CategoryRepository categoryRepository;
  
  @InjectMocks
  private CategoryServiceImpl categoryService;
  
  @Test
  @DisplayName("Testing the saving category method")
  void testsaveCategorySuccess() {
    // Arrange
	Category category = new Category(201, "Food", 50000);
	CategoryInDto categoryInDto = new CategoryInDto(201,"Food", 50000);
	Category finalCategory = this.categoryService.categoryInDtoToCategory(categoryInDto);
	CategoryOutDto finalCategoryOutDto = this.categoryService.categoryToCategoryOutDto(finalCategory);
	  
	// Act
	when(categoryRepository.save(any(Category.class))).thenReturn(category);
	CategoryOutDto saveCategory = categoryService.saveCategorys(categoryInDto);
	  
	// Assert
	assertThat(saveCategory).isNotNull();
	assertThat(saveCategory.getCategoryLimit()).isEqualTo(finalCategoryOutDto.getCategoryLimit());
  }
  
  @Test
  void testsaveCategoryFailure() {
    // Arrange
	CategoryInDto categoryInDto = new CategoryInDto(201,"Food", 50000);
	  
	// Act
	when(categoryRepository.existsByCategoryType("FOOD")).thenReturn(true);
	  
	// Assert
	assertThrows(AlreadyExistException.class, ()->categoryService.saveCategorys(categoryInDto));
  }
  
  @Test
  @DisplayName("Testing the fetching all category method")
  void testgetAllCategories() {
    // Arrange
	Category category1 = new Category(101, "Food", 10000);
	Category category2 = new Category(102, "Travel", 15000);
	List<Category> categoryList = Arrays.asList(category1,category2);
	CategoryOutDto categoryOutDto1 = this.categoryService.categoryToCategoryOutDto(category1);
	
    // Act
	when(categoryRepository.findAll()).thenReturn(categoryList);
	List<CategoryOutDto> allCategories = this.categoryService.getAllCategories();
    
    // Assert
	assertThat(allCategories.size()).isEqualTo(2);
  }
  
  @Test
  @DisplayName("Testing the fetching all category method")
   void testdeleteCategorySuccess() {
	 
    // Arrange
	Category category = new Category(1001, "Travel", 50000);
	  
	// Act
	when(categoryRepository.findByCategoryType("Travel")).thenReturn(Optional.ofNullable(category));
	  
	// Assert
	assertAll(() -> categoryService.deleteCategory("Travel"));
   }
  
  @Test
  void testdeleteCategoryFailure() {	  
	// Act
	when(categoryRepository.findByCategoryType("Travel")).thenReturn(Optional.empty());
	  
	// Assert
	assertThrows(ResourceNotFoundException.class, ()->categoryService.deleteCategory("Travel"));
  }
  
	@Test
	void test_categoryindtoToCategory() {
		// Arrange
		CategoryInDto categoryInDto = new CategoryInDto(101,"Food", 12000);
		Category category = new Category(101, "Food", 12000);
		
		// Act
		Category finalCategory = this.categoryService.categoryInDtoToCategory(categoryInDto);

		// Assert
		assertThat(finalCategory.getCategoryType()).isEqualTo(category.getCategoryType());
	}
	/**
	 * Model mapper test method.
	 */
	@Test
	void test_categoryToCategoryOutDto() {
		// Arrange
		Category category = new Category(101, "Food", 12000);
		CategoryOutDto categoryOutDto = new CategoryOutDto(102,"Food", 12000);
		
		// Act
		CategoryOutDto finalCategoryOutDto = this.categoryService.categoryToCategoryOutDto(category);

		// Assert
		assertThat(finalCategoryOutDto.getCategoryType()).isEqualTo(categoryOutDto.getCategoryType());
	}

}
