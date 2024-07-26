package com.filtro.person.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.filtro.person.domain.entity.Person;
import com.filtro.person.domain.service.PersonService;

import resources.DatabaseConfig;

public class PersonRepository implements PersonService {
    
    
    public List<Person> listAllPersons() {
        String sql = "SELECT id, name, lastname, idCity, address, age, email, idGender FROM persons";
        List<Person> persons = new ArrayList<>();

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Person person = new Person(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("lastname"),
                    resultSet.getInt("idCity"),
                    resultSet.getString("address"),
                    resultSet.getInt("age"),
                    resultSet.getString("email"),
                    resultSet.getInt("idGender")
                );
                persons.add(person);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    
    public void registerPerson(Person person) {
        String sql = "INSERT INTO persons (id, name, lastname, idCity, address, age, email, idGender) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, person.getId());
            statement.setString(2, person.getName());
            statement.setString(3, person.getLastname());
            statement.setInt(4, person.getIdCity());
            statement.setString(5, person.getAddress());
            statement.setInt(6, person.getAge());
            statement.setString(7, person.getEmail());
            statement.setInt(8, person.getIdGender());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public void updatePersonById(String updateColumns, int id) {
        String sql = "UPDATE persons SET " + updateColumns + " WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   
    public Boolean deletePersonById(Integer id) {
        String sql = "DELETE FROM persons WHERE id = ?";

        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void RegisterPerson(Person person) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'RegisterPerson'");
    }


    @Override
    public void updatePersontById(String updateColumns, Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePersontById'");
    }


    @Override
    public Boolean deletePersonTypeById(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePersonTypeById'");
    }


    
    
}


