package com.lta.springdatajpa;

import java.util.Date;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lta.springdatajpa.model.Photo;
import com.lta.springdatajpa.model.PhotoMetadata;
import com.lta.springdatajpa.model.Tag;
import com.lta.springdatajpa.model.User;
import com.lta.springdatajpa.repository.IPhotoMetadataRepository;
import com.lta.springdatajpa.repository.IPhotoRepository;
import com.lta.springdatajpa.repository.ITagRepository;
import com.lta.springdatajpa.repository.IUserRepository;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(IPhotoRepository photoRepository, IUserRepository userRepository, ITagRepository tagRepository, IPhotoMetadataRepository photoMetadataRepository) {
		
		return args -> {

			boolean IS_CREATE = false;
			
			if (IS_CREATE) {
			
				// CREATE
				User user1 = new User();
				user1.setName("user 1");
				
				userRepository.save(user1);
				
				for(int i = 0; i < 50; i++) {
					
					Photo photo = new Photo();
					photo.setTitle("my title " + i);
					photo.setDescription("my description");
					photo.setUrl("my url " + i);
					photo.setUser(user1);
					
					photoRepository.save(photo);
				}
				
				User user2 = new User();
				user2.setName("user 2");
				
				userRepository.save(user2);
				
				for(int i = 50; i < 100; i++) {
					
					Photo photo = new Photo();
					photo.setTitle("my title " + i);
					photo.setDescription("my description");
					photo.setUrl("my url " + i);
					photo.setUser(user2);
					
					photoRepository.save(photo);
				}				
			}
			
			// READ ALL
			Iterable<Photo> allPhotos = photoRepository.findAll();
			
			// READ BY ID
			Optional<Photo> singlePhoto = photoRepository.findById(300);
			
			if (singlePhoto.isEmpty()) {
				
				System.out.println("Photo not found");
				
			} else {
				
				Photo realPhoto = singlePhoto.get();
				
				System.out.println("BY ID - photo id : " + realPhoto.getId());
			}
			
			// UPDATE
			Optional<Photo> photoToUpdate = photoRepository.findById(3);
			
			if (photoToUpdate.isEmpty() == false) {
				
				Photo realPhoto = photoToUpdate.get();
				
				realPhoto.setTitle("new title");
				realPhoto.setDescription("new description !!!!");
				
				photoRepository.save(realPhoto);
				
				Photo updatedPhoto = photoRepository.findById(3).get();
				
				System.out.println("BY ID - photo id : " + updatedPhoto.getId() 
					+ " - title : " + updatedPhoto.getTitle() 
					+ " - description : " + updatedPhoto.getDescription());
				
			} else {
				
				System.out.println("Photo not found");
			}	
			
			// DELETE
			// deleting by object
			Optional<Photo> photoToDelete = photoRepository.findById(3);
			
			if (photoToDelete.isEmpty() == false) {
				
				photoRepository.delete(photoToDelete.get());
			}
			
			// deleting by id
			try {
				
				photoRepository.deleteById(1);
				
			} catch(EmptyResultDataAccessException ex) {
				
				System.out.println("Item not found");
			}
			
			// FIND BY TITLE
			Photo photoByTitle = photoRepository.findByTitle("my title 1").get();
			
			// FIND BY DESCRIPTION
			Iterable<Photo> photosByDescription = photoRepository.findByDescription("my description");
			
			// FIND BY TITLE AND DESCRIPTION
			Photo photoByTitleAndDescription = photoRepository.findByTitleAndDescription("my title 1", "my description").get();
			
			// QUERY BY TITLE AND DESCRIPTION
			Photo photoQueryByTitleAndDescription = photoRepository.loadUsingTitleAndDescription("my title 1", "my description").get();

			// QUERY BY TITLE AND DESCRIPTION NATIVE QUERY
			Photo photoQueryByTitleAndDescriptionNativeQuery = photoRepository.loadUsingTitleAndDescriptionNativeQuery("my title 1", "my description").get();
			
			User user2 = userRepository.findById(2).get();
			
			photoQueryByTitleAndDescriptionNativeQuery.setUser(user2);
			
			photoRepository.save(photoQueryByTitleAndDescriptionNativeQuery);
			
			// ADD TAG TO PHOTO
			Tag tag1 = new Tag();
			tag1.setText("my tag " + System.currentTimeMillis());
			
			tagRepository.save(tag1);
			
			Tag tag2 = new Tag();
			tag2.setText("my tag " + System.currentTimeMillis());
			
			tagRepository.save(tag2);	
			
			Photo photoAddTag = photoRepository.findByTitle("my title 10").get();
			
			photoAddTag.getTags().add(tag1);
			photoAddTag.getTags().add(tag2);
			
			photoRepository.save(photoAddTag);
			
			// REMOVE TAG FROM PHOTO
			Photo photoRemoveTag = photoRepository.findByTitle("my title 10").get();
			
			Tag tagToRemove = photoRemoveTag.getTags().iterator().next();
			
			photoRemoveTag.getTags().remove(tagToRemove);
			
			photoRepository.save(photoRemoveTag);
			
			// ADD METADATA TO PHOTO
			Photo photoAddMetadata = photoRepository.findByTitle("my title 10").get();
			
			if (photoAddMetadata.getPhotoMetadata() == null) {
				
				PhotoMetadata metadata = new PhotoMetadata();
				metadata.setWidth(100);
				metadata.setHeight(500);
				metadata.setCreated(new Date());
				
				metadata.setPhoto(photoAddMetadata);
				
				photoMetadataRepository.save(metadata);
				
				photoAddMetadata = photoRepository.findByTitle("my title 10").get();
			}
			
			System.out.println("-----------------------------------------");
		};
	}	
}
