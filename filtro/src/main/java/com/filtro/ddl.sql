DROP DATABASE IF EXISTS sgpzf;
CREATE DATABASE sgpzf;
USE sgpzf;

CREATE TABLE sgpzf.stack (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);


CREATE TABLE sgpzf.skill (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);


CREATE TABLE sgpzf.stack_skill (
    idskill INT,
    idstack INT,
    idstatus INT,
    PRIMARY KEY (idskill, idstack),
    FOREIGN KEY (idskill) REFERENCES sgpzf.skill(id),
    FOREIGN KEY (idstack) REFERENCES sgpzf.stack(id)
);



CREATE TABLE sgpzf.gender (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);





CREATE TABLE sgpzf.country (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE sgpzf.region (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    idcountry INT,
    FOREIGN KEY (idcountry) REFERENCES sgpzf.country(id)
);

CREATE TABLE sgpzf.city (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    idregion INT,
    FOREIGN KEY (idregion) REFERENCES sgpzf.region(id)
);

CREATE TABLE sgpzf.persons (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    idcity INT,
    address VARCHAR(100),
    age INT,
    email VARCHAR(100),
    idgender INT,
    FOREIGN KEY (idcity) REFERENCES sgpzf.city(id),
    FOREIGN KEY (idgender) REFERENCES sgpzf.gender(id)
);

CREATE TABLE sgpzf.persons_skill (
    id INT PRIMARY KEY,
    registration_date DATE,
    idperson INT,
    idskill INT,
    FOREIGN KEY (idperson) REFERENCES sgpzf.persons(id),
    FOREIGN KEY (idskill) REFERENCES sgpzf.skill(id)
);


