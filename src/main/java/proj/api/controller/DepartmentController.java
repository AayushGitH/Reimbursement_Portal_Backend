package proj.api.controller;

import javax.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import proj.api.in.dto.DepartmentInDto;
import proj.api.out.dto.DepartmentOutDto;
import proj.api.services.DepartmentService;
import proj.api.utils.Constants;

/**
* Controller for firing the url's related department.
*/
@CrossOrigin
@RestController
@RequestMapping(value = Constants.DEPARTMENT_BASE_URL)
public class DepartmentController {

  /**
  * The departmentService object.
  */
  @Autowired
  private DepartmentService departmentService;

  /**
   * The logger object.
   */
  private final Logger logger = LoggerFactory
  .getLogger(CategoryController.class);
  /**
   * Save department method.
   * @param department request
   * @return DepartmentOutDto.
   */
  @PostMapping("/create")
  public ResponseEntity<DepartmentOutDto> saveDepartment(
  final @Valid @RequestBody DepartmentInDto department) {
  logger.info("Entered the saving department method with "
  + "department - {}", department);

  DepartmentOutDto saveDepartment = this.departmentService
  .saveDepartment(department);

  logger.info("Successfully saved the department - {}", department);
  return ResponseEntity.status(HttpStatus.CREATED)
  .body(saveDepartment);
  }
  /**
   * Save department method.
   * @return List of departments.
   */
  @GetMapping("/getAll")
  public ResponseEntity<List<DepartmentOutDto>> getAllDepartments() {
  logger.info("Entered the getting all departments method");

  List<DepartmentOutDto> allDepartments =
  this.departmentService.getAllDepartments();
  logger.info("Successfully fetched all department");
  return ResponseEntity.status(HttpStatus.OK)
  .body(allDepartments);
  }

}
