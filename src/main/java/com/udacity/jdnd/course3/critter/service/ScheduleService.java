package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final PetRepository petRepository;
    private final EmployeeRepository employeeRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, PetRepository petRepository, EmployeeRepository employeeRepository){
        this.scheduleRepository = scheduleRepository;
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
    }
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());
        Set<Employee> employees = new HashSet<>(
                employeeRepository.findAllById(scheduleDTO.getEmployeeIds())
        );
        schedule.setEmployees(employees);
        Set<Pet> pets = new HashSet<>(petRepository.findAllById(scheduleDTO.getPetIds()));
        schedule.setPets(pets);

        Schedule savedSchedule = scheduleRepository.save(schedule);
        ScheduleDTO result = getScheduleDTO(savedSchedule);

        return result;

    }

    private static ScheduleDTO getScheduleDTO(Schedule savedSchedule) {
        List<Long> petIds = new ArrayList<>();
        for ( Pet pet : savedSchedule.getPets()){
            petIds.add(pet.getId());
        }
        List<Long> employeeIds = new ArrayList<>();
        for (Employee employee : savedSchedule.getEmployees()){
            employeeIds.add(employee.getId());
        }


        ScheduleDTO result = new ScheduleDTO();
        result.setId(savedSchedule.getId());
        result.setPetIds(petIds);
        result.setEmployeeIds(employeeIds);
        result.setActivities(savedSchedule.getActivities());
        result.setDate(savedSchedule.getDate());
        return result;
    }


    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> fetchedSchedule = scheduleRepository.findAll();
        List<ScheduleDTO> result = new ArrayList<>();
        for (Schedule schedule : fetchedSchedule){
            ScheduleDTO scheduleDTO = getScheduleDTO(schedule);
            result.add(scheduleDTO);

        }
        return  result;
    }

    public List<ScheduleDTO> getScheduleForPet(long petId) {
        List<Schedule> fetchedSchedules = scheduleRepository.findByPets_Id(petId);
        List<ScheduleDTO> result = new ArrayList<>();
        for (Schedule schedule : fetchedSchedules){
            ScheduleDTO scheduleDTO = getScheduleDTO(schedule);
            result.add(scheduleDTO);

        }
        return  result;
    }

    public List<ScheduleDTO> getScheduleForEmployee( long employeeId) {
        List<Schedule> fetchedSchedules = scheduleRepository.findByEmployees_Id(employeeId);
        List<ScheduleDTO> result = new ArrayList<>();
        for (Schedule schedule : fetchedSchedules){
            ScheduleDTO scheduleDTO = getScheduleDTO(schedule);
            result.add(scheduleDTO);

        }
        return  result;
    }

    public List<ScheduleDTO> getScheduleForCustomer( long customerId) {
        List<Schedule> fetchedSchedules = scheduleRepository.findByPets_Owner_Id(customerId);
        List<ScheduleDTO> result = new ArrayList<>();
        for (Schedule schedule : fetchedSchedules){
            ScheduleDTO scheduleDTO = getScheduleDTO(schedule);
            result.add(scheduleDTO);

        }
        return  result;

    }



    }
