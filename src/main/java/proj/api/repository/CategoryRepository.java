package proj.api.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import proj.api.entities.Category;

/**
 * Category repository calling the data from the database.
 */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

  /**
  * Finding category by the type.
  * @param categoryType for getting the type.
  * @return Category type object.
  */
  Optional<Category> findByCategoryType(String categoryType);
  /**
   * Exists method.
   * @param categoryType for getting the type.
   * @return Boolean value.
   */
  Boolean existsByCategoryType(String categoryType);
}
