package com.filtro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.filtro.person.application.DeletePersonByIdUseCase;
import com.filtro.person.application.ListAllPersonUseCase;
import com.filtro.person.application.RegisterPersonUsecase;
import com.filtro.person.application.UpdatePersonByIdUseCase;
import com.filtro.person.domain.service.PersonService;
import com.filtro.person.infrastructure.in.PersonController;
import com.filtro.person.infrastructure.out.PersonRepository;
import com.filtro.utils.ConsoleUtils;
import com.filtro.utils.Menus;

public class Main {
   public static void main(String[] args) {
        // Service declaration
        PersonService personService = new PersonRepository();

        // Use case section
        RegisterPersonUsecase registerPersonUseCase = new RegisterPersonUsecase(personService);
        ListAllPersonUseCase listAllPersonUseCase = new ListAllPersonUseCase(personService);
        UpdatePersonByIdUseCase updatePersonByIdUseCase = new UpdatePersonByIdUseCase(personService);
        DeletePersonByIdUseCase deletePersonByIdUseCase = new DeletePersonByIdUseCase(personService);

        // Controller section
        PersonController personController = new PersonController(registerPersonUseCase, listAllPersonUseCase, updatePersonByIdUseCase, deletePersonByIdUseCase);

        String userRole = "ADMIN";
        int[] holderAccess = {1, 2, 3, 4};
        List<String> useCases = Arrays.asList(
            "Register Person",  // 1
            "List All Persons", // 2
            "Update Person by ID",  // 3
            "Delete Person by ID"   // 4
        );
        List<String> userPermissions = new ArrayList<>();
        for (int i : holderAccess) {
            userPermissions.add(useCases.get(i - 1));
        }
        userPermissions.add("Exit");

        while (true) {
            ConsoleUtils.cleanScreen();
            System.out.println("------------------------" + userRole + " MENU--------------------------------------");
            String menuChoose = Menus.mainMenu(userPermissions, "User menus: ");
            switch (menuChoose) {
                case "Register Person": // 1
                    ConsoleUtils.cleanScreen();
                    System.out.println("----------------------------------------REGISTER PERSON MENU----------------------------------------");
                    personController.registerPersonLogic();
                    ConsoleUtils.pause();
                    break;
                case "List All Persons": // 2
                    ConsoleUtils.cleanScreen();
                    System.out.println("----------------------------------------LIST ALL PERSONS MENU----------------------------------------");
                    personController.listAllPersonLogic();
                    ConsoleUtils.pause();
                    break;
                case "Update Person by ID": // 3
                    ConsoleUtils.cleanScreen();
                    System.out.println("----------------------------------------UPDATE PERSON MENU----------------------------------------");
                    personController.updatePersonLogic();
                    ConsoleUtils.pause();
                    break;
                case "Delete Person by ID": // 4
                    ConsoleUtils.cleanScreen();
                    System.out.println("----------------------------------------DELETE PERSON MENU----------------------------------------");
                    personController.deletePersonLogic();
                    ConsoleUtils.pause();
                    break;
                case "Exit":
                    System.out.println("Have a good day!");
                    return;
                default:
                    break;
            }
        }
    }
}