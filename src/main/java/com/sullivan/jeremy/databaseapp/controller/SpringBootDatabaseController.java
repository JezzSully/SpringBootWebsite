package com.sullivan.jeremy.databaseapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sullivan.jeremy.databaseapp.exception.ResourceNotFoundException;
import com.sullivan.jeremy.databaseapp.model.FilmModel;
import com.sullivan.jeremy.databaseapp.model.SpringBootDataModel;
import com.sullivan.jeremy.databaseapp.repository.FilmRepository;
import com.sullivan.jeremy.databaseapp.repository.SpringBootRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SpringBootDatabaseController 
{
	@Autowired
	SpringBootRepository myRepository;
	
	@Autowired
	FilmRepository filmRepository;
	
	//create
	@PostMapping("/person")
	public SpringBootDataModel createPerson(@Valid @RequestBody SpringBootDataModel mSDM) 
	{
		return myRepository.save(mSDM);
	}
	
	//get
	@GetMapping("person/{id}")
	public SpringBootDataModel getPersonbyID(@PathVariable(value = "id")Long personID)
	{
		return myRepository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("SpringBootDataModel","id",personID));
	}
	
	//get all
	@GetMapping("/person")
	public List<SpringBootDataModel> getAllPeople()
	{
		return myRepository.findAll();
	}
	
	//update person
	@PutMapping("/person/{id}")
	public SpringBootDataModel updatePerson(@PathVariable(value = "id") Long personID,
			@Valid @RequestBody SpringBootDataModel personDetails)
	{
		SpringBootDataModel mSDM = myRepository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("Person","id",personID));
		
		mSDM.setName(personDetails.getName());
		mSDM.setAddress(personDetails.getAddress());
		mSDM.setAge(personDetails.getAge());
		
		SpringBootDataModel updateData = myRepository.save(mSDM);
		return updateData;
	}
	
	//delete
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePeron(@PathVariable(value = "id")Long personID)
	{
		SpringBootDataModel mSDM = myRepository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("Person","id",personID));
		
		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	}
	
	
	
	/////////////////////////////////////////////////////////PROJECT STARTS HERE///////////////////////////////////////////////////////////
	@GetMapping("/film")
	public List<FilmModel> getAllFilms()
	{
		return filmRepository.findAll();
	}
	
	@GetMapping("/film/{FID}")
	public FilmModel getFilmByID(@PathVariable(value = "FID")Long filmID)
	{
		return filmRepository.findById(filmID).orElseThrow(() -> new ResourceNotFoundException("FilmModel","FID",filmID));
	}
	
	@GetMapping("/film/{searchField}={searchCriteria}")
	public List<FilmModel> getFilmBySearch(@PathVariable String searchField, @PathVariable String searchCriteria)
	{
		//return filmRepository.findAllById(searchCriteria).orElseThrow(() -> new ResourceNotFoundException());
		return null;
	}
}
