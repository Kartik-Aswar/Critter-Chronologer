package com.udacity.jdnd.course3.critter.repository;


import com.udacity.jdnd.course3.critter.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

    List<Schedule> findByPets_Id(Long petId);
    List<Schedule> findByEmployees_Id(Long employeeId);
    List<Schedule> findByPets_Owner_Id(Long customerid);

}
