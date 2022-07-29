package com.lta.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lta.springdatajpa.model.Tag;
import com.lta.springdatajpa.model.User;

@Repository
public interface ITagRepository extends CrudRepository<Tag, Integer>{
}
