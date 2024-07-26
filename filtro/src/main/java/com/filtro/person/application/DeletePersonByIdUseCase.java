package com.filtro.person.application;

import com.filtro.person.domain.service.PersonService;

public class DeletePersonByIdUseCase {
    private final PersonService personService;

    public  DeletePersonByIdUseCase(PersonService personService) {
        this.personService = personService;
    }

    public Boolean execute(Integer id) {
        return personService.deletePersonTypeById(id);
    }
} 

