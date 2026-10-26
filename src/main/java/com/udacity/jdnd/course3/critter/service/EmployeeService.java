package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setDaysAvailable(employeeDTO.getDaysAvailable());
        employee.setSkills(employeeDTO.getSkills());
        employee.setName(employeeDTO.getName());
        Employee savedEmployee = employeeRepository.save(employee);


        EmployeeDTO result = new EmployeeDTO();
        result.setDaysAvailable(savedEmployee.getDaysAvailable());
        result.setId(savedEmployee.getId());
        result.setName(savedEmployee.getName());
        result.setSkills(savedEmployee.getSkills());


        return result;
    }

    public EmployeeDTO getEmployee(long employeeId) {
        Employee fetchedEmployee = employeeRepository.findById(employeeId).orElseThrow();
        EmployeeDTO result = new EmployeeDTO();
        result.setSkills(fetchedEmployee.getSkills());
        result.setId(fetchedEmployee.getId());
        result.setName(fetchedEmployee.getName());
        result.setDaysAvailable(fetchedEmployee.getDaysAvailable());

        return result;
    }

    public void setAvailability(long employeeId, Set<DayOfWeek> daysAvailable) {
        Employee fetchedEmployee = employeeRepository.findById(employeeId).orElseThrow();
        fetchedEmployee.setDaysAvailable(daysAvailable);
        employeeRepository.save(fetchedEmployee);

    }


    public List<EmployeeDTO> findEmployeesForService(EmployeeRequestDTO request) {
        Set<EmployeeSkill> skills = request.getSkills();
        DayOfWeek requestedDay = request.getDate().getDayOfWeek();

        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> result = new ArrayList<>();

        for (Employee employee : employees) {

            // Check whether the employee has every requested skill
            boolean hasRequiredSkills = employee.getSkills() != null && employee.getSkills().containsAll(skills);

            // Check whether the employee is available on that weekday
            boolean isAvailable = employee.getDaysAvailable() != null && employee.getDaysAvailable().contains(requestedDay);

            // Only matching employees should be added
            if (hasRequiredSkills && isAvailable) {
                EmployeeDTO employeeDTO = new EmployeeDTO();

                employeeDTO.setId(employee.getId());
                employeeDTO.setName(employee.getName());
                employeeDTO.setSkills(employee.getSkills());
                employeeDTO.setDaysAvailable(employee.getDaysAvailable());

                result.add(employeeDTO);
            }
        }

        return result;
    }

}
