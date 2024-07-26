package com.filtro.person.application;

import com.filtro.person.domain.service.PersonService;

public class UpdatePersonByIdUseCase {
    
     private final PersonService personService;

    public UpdatePersonByIdUseCase  (PersonService personService) {
        this.personService = personService;
    }

    public Boolean execute(String updateColumns, Integer id) {
        personService.updatePersontById(updateColumns, id);
        return null;
    }

    public void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
}
