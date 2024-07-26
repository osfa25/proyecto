package com.filtro.person.application;

import java.util.List;

import com.filtro.person.domain.entity.Person;
import com.filtro.person.domain.service.PersonService;

public class ListAllPersonUseCase {
    private final PersonService personService;

    public ListAllPersonUseCase (PersonService personService) {
        this.personService = personService;
    }
    
    public List<Person> execute(){
        return personService.listAllPersons();
    }
}
