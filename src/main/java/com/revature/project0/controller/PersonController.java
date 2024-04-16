package com.revature.project0.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project0.model.Person;
import com.revature.project0.repo.PersonRepo;

@RestController

public class PersonController {
    @Autowired

    private PersonRepo repo;

	// Add Person
    @PostMapping("/addPerson")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
	    // Save name entered in table Person and return values (id and name) in the Person object personObj 
        Person personObj = repo.save(person);
		// Return created object personObj and status OK)
		return new ResponseEntity<>(personObj, HttpStatus.OK);
    }

	// Get Person By Id
	@GetMapping("/getPersonById/{id}")
	public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
		java.util.Optional<Person> personData = repo.findById(id);
		// If person with the id is found, return all values from this person
		if (personData.isPresent()) {
			return new ResponseEntity<>(personData.get(), HttpStatus.OK);
		} 
		// In case person is not found, reteurn HttpStatus NOT_FOUND
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	// Get All Persons
	@SuppressWarnings("finally")
    @GetMapping("/getAllPersons")
	public ResponseEntity<ArrayList<Person>> getAllPersons(){
		try {
			// Select all persons and store the retrieved values in the object personList
            ArrayList<Person> personList = new ArrayList<>(repo.findAll());
			// If no person found, return HttpStatus NO_CONTENT
			if (personList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		// If person/s are found, return object personList with the list of the persons selected from table Person
		return new ResponseEntity<>(personList, HttpStatus.OK);
	} catch (Exception ex) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
	// Update Person By ID
	/*
    @PostMapping("/updatePersonById/{id}")
	public ResponseEntity<Person> updatePersonById(@PathVariable Long id, @RequestBody Person newPersonData){
	    // Copy person data retrieved into object OldPersonData
        java.util.Optional<Person> oldPersonData = repo.findById(id);
		// If data from the person with the id is found/present, continue with the statements within the if statements
		if (oldPersonData.isPresent()) {
			// Copy person data from object oldPersonData into object updatePersonData
			Person updatePersonData = oldPersonData.get();
			// Get new name from the object newPersonData and replace the old name in object updatePersonData
			updatePersonData.setName(newPersonData.getName());
			// Save person data from object updatePersonData in table Person and copy data from objct updatePersonData into objct personObj
			Person personObj = repo.save(updatePersonData);
			// Return object personObj and also HttpStatus OK			
			return new ResponseEntity<>(personObj, HttpStatus.OK);
		} 
		// In case person is not found, return HttpStatus NOT_FOUND
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	} 
	*/
	// Delete Person By Id0
	@DeleteMapping("deletePersonByID/{id}")
	public ResponseEntity<HttpStatus> deletePersonByID(@PathVariable Long id){
		repo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}	
}
