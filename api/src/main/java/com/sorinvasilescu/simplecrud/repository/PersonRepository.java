package com.sorinvasilescu.simplecrud.repository;

import com.sorinvasilescu.simplecrud.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {

}
