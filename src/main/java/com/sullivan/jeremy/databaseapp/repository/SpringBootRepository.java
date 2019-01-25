package com.sullivan.jeremy.databaseapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sullivan.jeremy.databaseapp.model.SpringBootDataModel;

@Repository
public interface SpringBootRepository extends JpaRepository<SpringBootDataModel,Long> 
{
	
}
