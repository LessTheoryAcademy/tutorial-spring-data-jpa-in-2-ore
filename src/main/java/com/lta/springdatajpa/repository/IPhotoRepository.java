package com.lta.springdatajpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lta.springdatajpa.model.Photo;

@Repository
public interface IPhotoRepository extends CrudRepository<Photo, Integer>{

	public Optional<Photo> findByTitle(String title);
	
	public Iterable<Photo> findByDescription(String description);
	
	public Optional<Photo> findByTitleAndDescription(String title, String description);
	
	@Query("select p from Photo p where p.title = ?1 and p.description = ?2")
	public Optional<Photo> loadUsingTitleAndDescription(String title, String description);
	
	@Query(value = "select * from photos where title = ?1 and description = ?2", nativeQuery = true)
	public Optional<Photo> loadUsingTitleAndDescriptionNativeQuery(String title, String description);
}
