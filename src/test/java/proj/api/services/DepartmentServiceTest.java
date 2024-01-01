package proj.api.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import proj.api.entities.Department;
import proj.api.exception.AlreadyExistException;
import proj.api.exception.ResourceNotFoundException;
import proj.api.in.dto.DepartmentInDto;
import proj.api.out.dto.DepartmentOutDto;
import proj.api.repository.DepartmentRepository;
import proj.api.services.impl.DepartmentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
	
	@Mock
	private DepartmentRepository departmentRepository;
	
	@InjectMocks
	private DepartmentServiceImpl departmentService;

	
	@Test
	void test_saveDepartmentSuccess() {
		// Arrange
		Department department = new Department(0, "HR", null);
		DepartmentInDto departmentInDto = new DepartmentInDto(0, "HR");
//		DepartmentOutDto departmentOutDto = new DepartmentOutDto(0, "HR");
		Department newDepartment = this.departmentService.departmentInDtoToDepartment(departmentInDto);
		DepartmentOutDto newDepartmentOutDto = this.departmentService.departmentToDepartmentOutDto(department);

		// Act
		when(departmentRepository.existsByDepartmentName("HR")).thenReturn(false);
		when(departmentRepository.save(any(Department.class))).thenReturn(department);
		DepartmentOutDto saveDepartment = departmentService.saveDepartment(departmentInDto);

		// Assert
		assertThat(newDepartment.getDepartmentName()).isEqualTo(saveDepartment.getDepartmentName());
		assertThat(saveDepartment.getDepartmentName()).isEqualTo(newDepartmentOutDto.getDepartmentName());
		assertThat(saveDepartment).isNotEqualTo(department);
	}
	
	@Test
	void test_saveDepartmentFailure() {
		// Arrange
		DepartmentInDto departmentInDto = new DepartmentInDto(101, "HR");

		// Act
		when(departmentRepository.existsByDepartmentName("HR")).thenReturn(true);

		// Assert
		assertThrows(AlreadyExistException.class, ()->departmentService.saveDepartment(departmentInDto));
	}

	
	@Test
	void test_getDepartmentByIdSuccess() {
		// Arrange
		Department department = new Department(123, "HR", null);

		// Act
		when(departmentRepository.findById(123)).thenReturn(Optional.ofNullable(department));
		Department expecteddept = departmentService.getDepartmentById(123);

		// Assert
		assertThat(department).isEqualTo(expecteddept);
	}
	
	@Test
	void test_getDepartmentByIdFailure() {
		// Act
		when(departmentRepository.findById(123)).thenReturn(Optional.empty());

		// Assert
		assertThrows(ResourceNotFoundException.class, ()->departmentService.getDepartmentById(123));
	}

	
	@Test
	void test_getAllDepartments() {
		// Arrange
		Department department1 = new Department(123, "HR", null);
		Department department2 = new Department(234, "IT", null);
		List<Department> departmentlist = Arrays.asList(department1,department2);
//		DepartmentOutDto departmentOutDto1 = new DepartmentOutDto(123, "HR");
//		DepartmentOutDto departmentOutDto2 = new DepartmentOutDto(123, "IT");
		DepartmentOutDto departmentOutDto1 = this.departmentService.departmentToDepartmentOutDto(department1);
		DepartmentOutDto departmentOutDto2 = this.departmentService.departmentToDepartmentOutDto(department2);
//		List<DepartmentOutDto> departmentOutDtos = Arrays.asList(departmentOutDto1,departmentOutDto2);

		// Act
		when(departmentRepository.findAll()).thenReturn(departmentlist);
//		when(modelMapper.map(department1, DepartmentOutDto.class)).thenReturn(departmentOutDto1);
//		when(modelMapper.map(department2, DepartmentOutDto.class)).thenReturn(departmentOutDto2);
		List<DepartmentOutDto> allDepartments = departmentService.getAllDepartments();

		// Assert
		assertThat(allDepartments.size()).isEqualTo(2);
		assertThat(allDepartments.get(0).getDepartmentName()).isEqualTo(departmentOutDto1.getDepartmentName());
		assertThat(allDepartments.get(1).getDepartmentName()).isEqualTo(departmentOutDto2.getDepartmentName());
	}
	
	@Test
	void test_departmentindtoTodepartment() {
		// Arrange
		DepartmentInDto departmentInDto = new DepartmentInDto(101, "HR");
		Department department = new Department(201,"HR",null);
		
		// Act
		Department finalDepartment = this.departmentService.departmentInDtoToDepartment(departmentInDto);

		// Assert
		assertThat(finalDepartment.getDepartmentName()).isEqualTo(department.getDepartmentName());
	}
	
	@Test
	void test_departmentToDepartmentOutDto() {
		// Arrange
		Department department = new Department(201,"HR",null);
		DepartmentOutDto departmentOutDto = new DepartmentOutDto(201, "HR");
		
		// Act
		DepartmentOutDto finalDepartment = this.departmentService.departmentToDepartmentOutDto(department);

		// Assert
		assertThat(finalDepartment.getDepartmentName()).isEqualTo(departmentOutDto.getDepartmentName());
	}

}
