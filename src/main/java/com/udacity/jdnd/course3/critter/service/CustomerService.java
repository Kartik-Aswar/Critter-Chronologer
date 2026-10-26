package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;

    public CustomerService(CustomerRepository customerRepository, PetRepository petRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
    }

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

        Customer customer = new Customer();

        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());

        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO result = new CustomerDTO();

        result.setId(savedCustomer.getId());
        result.setName(savedCustomer.getName());
        result.setPhoneNumber(savedCustomer.getPhoneNumber());
        result.setNotes(savedCustomer.getNotes());

        return result;
    }

    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> result = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setNotes(customer.getNotes());
        List<Pet> pets = petRepository.findByOwner_Id(customer.getId());
            List<Long> petIds = pets.stream()
                    .map(Pet::getId)
                    .toList();

            dto.setPetIds(petIds);


        result.add(dto);
    }
        return result;
    }


    public CustomerDTO getOwnerByPet(long petId){
        Pet pet =  petRepository.findById(petId).orElseThrow();
        Customer customer = pet.getOwner();

        CustomerDTO result = new CustomerDTO();
        result.setName(customer.getName());
        result.setPhoneNumber(customer.getPhoneNumber());
        result.setNotes(customer.getNotes());
        result.setId(customer.getId());
        List<Pet> pets = petRepository.findByOwner_Id(customer.getId());

        List<Long> petIds = pets.stream()
                .map(Pet::getId)
                .toList();

        result.setPetIds(petIds);

        return result;

    }

    






























}
