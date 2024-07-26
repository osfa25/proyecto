package com.filtro.person.domain.service;

import java.util.List;

import com.filtro.person.domain.entity.Person;

public interface PersonService {

    void RegisterPerson(Person person);

    void updatePersontById(String updateColumns, Integer id);

    Boolean deletePersonTypeById(Integer id);

    List<Person> listAllPersons();

    
}
