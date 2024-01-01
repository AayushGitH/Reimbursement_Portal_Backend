package proj.api.services.impl;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj.api.entities.Department;
import proj.api.exception.AlreadyExistException;
import proj.api.exception.ResourceNotFoundException;
import proj.api.in.dto.DepartmentInDto;
import proj.api.out.dto.DepartmentOutDto;
import proj.api.repository.DepartmentRepository;
import proj.api.services.DepartmentService;
import proj.api.utils.ErrorConstants;

/**
 * Department service implementation class.
 * for defining the methods from Department service.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

  /**
   * The departmentRepository object.
   */
  @Autowired
  private DepartmentRepository departmentRepository;

  /**
   * The logger object.
   */
  private final Logger logger = LoggerFactory
  .getLogger(DepartmentServiceImpl.class);

  /**
   * Save department method definition for saving the department.
   * @param department for saving the department.
   * @return DepartmentDto
   */
  @Override

  public DepartmentOutDto saveDepartment(final DepartmentInDto department) {
  logger.info("Department received is {}", department);
  String upperCaseDepartment = department.getDepartmentName()
  .toUpperCase(Locale.ENGLISH);
  department.setDepartmentName(upperCaseDepartment);
  Boolean exists = this.departmentRepository.existsByDepartmentName(
  department.getDepartmentName());
  if (exists) {
    throw new AlreadyExistException(ErrorConstants.DEPARTMENT_ALREADY_EXISTS);
  }
  Department tosaveUser = this.departmentInDtoToDepartment(department);
  Department saveDepartment = this.departmentRepository.save(tosaveUser);
  logger.info("Department is stored successfully !!");
  return this.departmentToDepartmentOutDto(saveDepartment);
  }

  /**
   * Reading department by id method definition.
   * @param departmentId Id for request.
   * @return Department type.
   */
  @Override
  public Department getDepartmentById(final int departmentId) {
    logger.info("Get all departments method is executing...");
    Department department = this.departmentRepository
    .findById(departmentId).orElseThrow(() ->
    new ResourceNotFoundException("Department", String.valueOf(departmentId)));
    logger.info("Successfully fetched the department...");
    return department;
  }

  /**
   * Reading all departments method definition.
   * @return List of departments.
   */
  @Override
  public List<DepartmentOutDto> getAllDepartments() {
  logger.info("Entered the get all departments method...");
  List<Department> allDepartments = this.departmentRepository.findAll();
  List<DepartmentOutDto> fetchedDepartments = allDepartments.stream().map(
  department -> this.departmentToDepartmentOutDto(department))
  .collect(Collectors.toList());
  logger.info("Successfully fetched the departments...");
  return fetchedDepartments;
  }
  /**
   * Department model mapper.
   * @param departmentInDto In dto object.
   * @return department.
   */
  public Department departmentInDtoToDepartment(
  final DepartmentInDto departmentInDto) {
    Department department = new Department();
    department.setDepartmentId(departmentInDto.getDepartmentId());
    department.setDepartmentName(
    departmentInDto.getDepartmentName());
    return department;
  }
  /**
   * Department model mapper.
   * @param department to departmentoutdto object.
   * @return departmentoutdto.
   */
  public DepartmentOutDto departmentToDepartmentOutDto(
  final Department department) {
    DepartmentOutDto departmentOutDto = new DepartmentOutDto();
    departmentOutDto.setDepartmentId(department.getDepartmentId());
    departmentOutDto.setDepartmentName(department.getDepartmentName());
    return departmentOutDto;
  }
}
