package com.lta.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lta.springdatajpa.model.User;

@Repository
public interface IUserRepository extends CrudRepository<User, Integer>{
}
