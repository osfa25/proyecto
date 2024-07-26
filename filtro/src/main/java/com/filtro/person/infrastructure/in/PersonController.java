package com.filtro.person.infrastructure.in;

import java.util.List;

import com.filtro.person.application.DeletePersonByIdUseCase;
import com.filtro.person.application.ListAllPersonUseCase;
import com.filtro.person.application.RegisterPersonUsecase;
import com.filtro.person.application.UpdatePersonByIdUseCase;
import com.filtro.person.domain.entity.Person;
import com.filtro.utils.ConsoleUtils;
import com.filtro.utils.Menus;
import com.filtro.utils.MyScanner;

public class PersonController {
    private final RegisterPersonUsecase registerPersonUsecase;
    private final UpdatePersonByIdUseCase updatePersonByIdUseCase;
    private final ListAllPersonUseCase listAllPersonUseCase;
    private final DeletePersonByIdUseCase deletePersonByIdUseCase;

    public PersonController(RegisterPersonUsecase registerPersonUsecase,
                            ListAllPersonUseCase listAllPersonUseCase,
                            UpdatePersonByIdUseCase updatePersonByIdUseCase2, 
                            DeletePersonByIdUseCase deletePersonByIdUseCase) {
                                
        this.registerPersonUsecase = registerPersonUsecase;
        this.updatePersonByIdUseCase = updatePersonByIdUseCase2;
        this.deletePersonByIdUseCase = deletePersonByIdUseCase;
        this.listAllPersonUseCase = listAllPersonUseCase;
    }

    public void start() {
        while (true) {
            displayMenu();

            int option = ConsoleUtils.option_validation("Choose an option: ", 1, 5);
            switch (option) {
                case 1:
                    ConsoleUtils.cleanScreen();
                    System.out.println("----------------------------------------REGISTER PERSON MENU----------------------------------------");
                    registerPersonLogic();
                    ConsoleUtils.pause();
                    break;
                case 2:
                    ConsoleUtils.cleanScreen();
                    System.out.println("----------------------------------------UPDATE PERSON MENU----------------------------------------");
                    updatePersonLogic();
                    ConsoleUtils.pause();
                    break;
                case 3:
                    ConsoleUtils.cleanScreen();
                    System.out.println("----------------------------------------FIND PERSON MENU----------------------------------------");
                    listAllPersonLogic();
                    ConsoleUtils.pause();
                    break;
                case 4:
                    ConsoleUtils.cleanScreen();
                    System.out.println("----------------------------------------DELETE PERSON MENU----------------------------------------");
                    deletePersonLogic();
                    ConsoleUtils.pause();
                    break;
                case 5:
                    return;
                default:
                    break;
            }
        }
    }

    private void displayMenu() {
        System.out.println("----------------------------------------PERSON MENU----------------------------------------");
        System.out.println("1. Register Person");
        System.out.println("2. Update Person");
        System.out.println("3. List Person");
        System.out.println("4. Delete Person");
        System.out.println("5. Go back");
    }

    public void deletePersonLogic() {
        try {
            System.out.print("Enter Person id to delete: ");
            int id = Integer.parseInt(MyScanner.scanLine());
            deletePersonByIdUseCase.execute(id);
            System.out.println("Person deleted successfully!");
        } catch (Exception e) {
            System.out.println("Error deleting Person: " + e.getMessage());
        }
    }

    public void registerPersonLogic() {
        try {
            System.out.print("Type the Person id: ");
            int id = Integer.parseInt(MyScanner.scanLine());
            System.out.print("Type the Person name: ");
            String name = MyScanner.scanLine();
            if (name.isEmpty()) {
                throw new Exception("You didn't put a name");
            }
            System.out.print("Type the Person lastname: ");
            String lastname = MyScanner.scanLine();
            if (lastname.isEmpty()) {
                throw new Exception("You didn't put a lastname");
            }
            System.out.print("Type the Person city id: ");
            Integer idCity = MyScanner.scanInt();
            System.out.print("Type the Person address: ");
            String address = MyScanner.scanLine();
            System.out.print("Type the Person age: ");
            Integer age = MyScanner.scanInt();
            System.out.print("Type the Person email: ");
            String email = MyScanner.scanLine();
            if (email.isEmpty()) {
                throw new Exception("You didn't put an email");
            }
            System.out.print("Type the Person gender id: ");
            Integer idGender = MyScanner.scanInt();

            registerPersonUsecase.execute(new Person(id, name, lastname, idCity, address, age, email, idGender));
            System.out.println("Person created successfully!");
        } catch (Exception e) {
            System.out.println("Error at creating a person: " + e.getMessage());
        }
    }

    public void updatePersonLogic() {
        try {
            System.out.print("Type the person id: ");
            int id = Integer.parseInt(MyScanner.scanLine());
            List<Person> personList = listAllPersonUseCase.execute();
            if (personList == null) {
                throw new Exception("There is no person with this id");
            }
            Person userPerson = null;
            for (Person person : personList){
                if(person.getId() == id){
                    userPerson = person;
                    break;
                }
            }
            if(userPerson == null){
                throw new Exception("There is no person with this id");
            }
            System.out.println("User person info: ");
            displayPersonDetails(userPerson);
            int op = Menus.classAttributeMenu(userPerson.getClass(), "Choose an attribute to update: ");
            String updateColumns = "";
            switch (op) {
                case 0: // id
                    System.out.print("Type the new person id (current: " + userPerson.getId() + "): ");
                    int newId = Integer.parseInt(MyScanner.scanLine());
                    if (listAllPersonUseCase.execute().stream().anyMatch(p -> p.getId() == newId)) {
                        throw new Exception("There is already a person with this id");
                    }
                    updateColumns = "id = " + newId;
                    updatePersonByIdUseCase.execute();
                    break;
                case 1: // name
                    System.out.print("Type the new person name (current: " + userPerson.getName() + "): ");
                    String newName = MyScanner.scanLine();
                    if (newName.isEmpty()) {
                        throw new Exception("You didn't put a name");
                    }
                    updateColumns = "name = '" + newName + "'";
                    updatePersonByIdUseCase.execute();
                    break;
                case 2: // lastname
                    System.out.print("Type the new person lastname (current: " + userPerson.getLastname() + "): ");
                    String newLastname = MyScanner.scanLine();
                    if (newLastname.isEmpty()) {
                        throw new Exception("You didn't put a lastname");
                    }
                    updateColumns = "lastname = '" + newLastname + "'";
                    updatePersonByIdUseCase.execute();
                    break;
                // Add other cases for other fields
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error at updating the person: " + e.getMessage());
        }
    }

    public void listAllPersonLogic() {
        try {
            System.out.print("Enter person id: ");
            int id = MyScanner.scanInt();
            List<Person> listPerson = listAllPersonUseCase.execute();
            if (listPerson == null) {
                throw new Exception("Invalid person id.");
            }
            for (Person person : listPerson){
                if(person.getId() == id){
                    displayPersonDetails(person);
                    return;
                }
            }
            System.out.println("There is no person with the id: " + id);
            
        } catch (Exception e) {
            System.out.println("Error finding person: " + e.getMessage());
        }
    }

    public void displayPersonDetails(Person person) {
        System.out.println("Person id: " + person.getId());
        System.out.println("Person name: " + person.getName());
        System.out.println("Person lastname: " + person.getLastname());
        System.out.println("Person city id: " + person.getIdCity());
        System.out.println("Person address: " + person.getAddress());
        System.out.println("Person age: " + person.getAge());
        System.out.println("Person email: " + person.getEmail());
        System.out.println("Person gender id: " + person.getIdGender());
        System.out.println("------------------------------------------------------------------------------------------------");
    }
}
