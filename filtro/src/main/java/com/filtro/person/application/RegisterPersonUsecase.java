package com.filtro.person.application;

import com.filtro.person.domain.entity.Person;
import com.filtro.person.domain.service.PersonService;

public class RegisterPersonUsecase {
      private final PersonService personService;

    public RegisterPersonUsecase(PersonService personService) {
        this.personService = personService;
    }

   public void execute(Person person) {
        personService.RegisterPerson(person);
    }
}
