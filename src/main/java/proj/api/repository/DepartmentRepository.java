package proj.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import proj.api.entities.Department;

/**
 * Department repository calling the data from the database.
 */
public interface DepartmentRepository extends
JpaRepository<Department, Integer> {
  /**
  * Exists method.
  * @param departmentName for getting the type.
  * @return Boolean value.
  */
  Boolean existsByDepartmentName(String departmentName);
}
