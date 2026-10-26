package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PetService {

    private final PetRepository petRepository;
    private final CustomerRepository customerRepository;

    public PetService(CustomerRepository customerRepository ,PetRepository petRepository){
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public PetDTO savePet(PetDTO petDTO){
        Pet pet = new Pet();
        pet.setType(petDTO.getType());
        pet.setNotes(petDTO.getNotes());
        pet.setName(petDTO.getName());
        pet.setBirthDate(petDTO.getBirthDate());

        Customer owner = customerRepository.findById(petDTO.getOwnerId()).orElseThrow();
        pet.setOwner(owner);

        Pet savedPet = petRepository.save(pet);

        PetDTO result = new PetDTO();

        result.setId(savedPet.getId());
        result.setType(savedPet.getType());
        result.setName(savedPet.getName());
        result.setOwnerId(savedPet.getOwner().getId());
        result.setBirthDate(savedPet.getBirthDate());
        result.setNotes(savedPet.getNotes());


        return result;

    }

    public PetDTO getPet(long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow();
        PetDTO fetchedPet = new PetDTO();
        fetchedPet.setId(pet.getId());
        fetchedPet.setType(pet.getType());
        fetchedPet.setName(pet.getName());
        fetchedPet.setNotes(pet.getNotes());
        fetchedPet.setBirthDate(pet.getBirthDate());
        fetchedPet.setOwnerId(pet.getOwner().getId());

        return fetchedPet;
    }

    public List<PetDTO> getPets(){
        List <Pet> pets = petRepository.findAll();
        List<PetDTO> petList = new ArrayList<>();
        for (Pet pet : pets){
        PetDTO result = new PetDTO();
            result.setOwnerId(pet.getOwner().getId());
            result.setBirthDate(pet.getBirthDate());
            result.setType(pet.getType());
            result.setNotes(pet.getNotes());
            result.setName(pet.getName());
            result.setId(pet.getId());

            petList.add(result);
        }
        return petList;
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        List<Pet> pets = petRepository.findByOwner_Id(ownerId);

        List<PetDTO> ownerPets = new ArrayList<>();
        for (Pet pet : pets){
            PetDTO result = new PetDTO();
            result.setOwnerId(pet.getOwner().getId());
            result.setBirthDate(pet.getBirthDate());
            result.setType(pet.getType());
            result.setNotes(pet.getNotes());
            result.setName(pet.getName());
            result.setId(pet.getId());

            ownerPets.add(result);
        }
        return ownerPets;
    }



}
