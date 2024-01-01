package proj.api.in.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import proj.api.entities.Category;
import proj.api.in.dto.CategoryInDto;

@ExtendWith(MockitoExtension.class)
public class CategoryInDtoTest {

	@Test
  @DisplayName("CategoryInDTO getter and setter test")
  public void getterAndSetter() {
  	CategoryInDto category = new CategoryInDto();
  	int id = 201;
  	category.setCategoryId(id);
  	assertNotNull(category.getCategoryId());
  	assertEquals(id, category.getCategoryId());
  	
  	Long categoryLimit = 14000L;
  	category.setCategoryLimit(categoryLimit);
  	assertNotNull(category.getCategoryLimit());
  	assertEquals(categoryLimit, category.getCategoryLimit());
  	
  	String categoryType = "Food";
  	category.setCategoryType(categoryType);
  	assertNotNull(category.getCategoryType());
  	assertEquals(categoryType, category.getCategoryType());
  }
	
	@Test
  @DisplayName("Category in DTO toString method test")
  public void toStringTest() {
  	String expected = "CategoryInDto [categoryId=201, categoryType=Food, categoryLimit=14000]";
  	CategoryInDto category = new CategoryInDto();
  	category.setCategoryId(201);
  	category.setCategoryLimit(14000);
  	category.setCategoryType("Food");
  	
  	assertEquals(expected, category.toString());
  }

	@Test
  @DisplayName("Category In DTO entity hashCode and equals method test")
  public void hashCodeAndEqualsTest() {

  	CategoryInDto category1 = new CategoryInDto(101, "FOOD", 12000L);
  	assertEquals(category1, category1);
  	assertEquals(category1.hashCode(), category1.hashCode());
  	
  	assertNotEquals(category1, new Category());
  	assertNotEquals(category1, new Object());
  	
  	assertNotNull(category1);
  	assertNotNull(category1.hashCode());
  	assertFalse(category1.equals(null));
  	
  	CategoryInDto category2 = new CategoryInDto(101, "FOOD", 12000L);
  	assertEquals(category1, category2);
  	assertEquals(category1.hashCode(), category2.hashCode());
  	
  	category2.setCategoryId(200);
  	assertFalse(category1.equals(category2));
  	
  	category2.setCategoryType("TRAVEL");
  	assertFalse(category1.equals(category2));
  	
  	category2.setCategoryLimit(50000);
  	assertFalse(category1.equals(category2));
  }
}
