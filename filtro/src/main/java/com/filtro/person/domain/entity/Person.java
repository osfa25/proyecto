package com.filtro.person.domain.entity;
    
public class Person {
    Integer id;
    String name;
    String lastname;
    Integer idCity;
    String address;
    Integer age;
    String email;
    Integer idGender;

    public Person() {
    }

    public Person(Integer id, String name, String lastname, Integer idCity, String address, Integer age, String email, Integer idGender) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.idCity = idCity;
        this.address = address;
        this.age = age;
        this.email = email;
        this.idGender = idGender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdGender() {
        return idGender;
    }

    public void setIdGender(Integer idGender) {
        this.idGender = idGender;
    }

    @Override
    public String toString() {
        return " id: " + id +
               " | name: " + name +
               " | lastname: " + lastname +
               " | idCity: " + idCity +
               " | address: " + address +
               " | age: " + age +
               " | email: " + email +
               " | idGender: " + idGender;
    }
}

