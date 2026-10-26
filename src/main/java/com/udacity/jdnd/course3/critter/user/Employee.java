package com.udacity.jdnd.course3.critter.user;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.util.Set;
@Entity
public class Employee {
    @Id
    @GeneratedValue
    private long id;

    private String name;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;



    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
