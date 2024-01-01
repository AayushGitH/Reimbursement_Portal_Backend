package proj.api.services;

import java.util.List;
import proj.api.entities.Department;
import proj.api.in.dto.DepartmentInDto;
import proj.api.out.dto.DepartmentOutDto;

/**
 * Department service for declaring the department repository methods.
 */
public interface DepartmentService {

  /**
   * Save department method declaration.
  *
  * @param department Department type parameter.
  * @return Department.
  */
  DepartmentOutDto saveDepartment(DepartmentInDto department);
  /**
   * Get department method declaration.
   *
   * @param departmentId Id for getting the department.
   * @return Department
   */
  Department getDepartmentById(int departmentId);

  /**
   * Get all departments method declaration.
   *
   * @return List of Departments.
   */
  List<DepartmentOutDto> getAllDepartments();
}
