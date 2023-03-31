DROP DATABASE IF EXISTS mediscreendb;

CREATE DATABASE mediscreendb;

USE mediscreendb;

CREATE TABLE patients
(

    id           INTEGER      NOT NULL AUTO_INCREMENT,
    last_name    VARCHAR(155) NOT NULL,
    first_name   VARCHAR(155) NOT NULL,
    birthdate    DATE         NOT NULL,
    gender       VARCHAR(155) NOT NULL,
    address      VARCHAR(255),
    phone_number VARCHAR(155),

    PRIMARY KEY (id)
    );


INSERT INTO patients(last_name, first_name, birthdate, gender, address, phone_number) VALUE
    ('patient01', 'firstNameTest', '2023-01-01', 'H', 'Toulouse', '0000000'),
    ('patient02', 'firstNameTest', '2023-01-02', 'F', 'Toulouse', '0000000'),
    ('patient03', 'firstNameTest', '2023-01-03', 'O', 'Toulouse', '0000000'),
    ('patient04', 'firstNameTest', '2023-01-04', 'H', 'Toulouse', '0000000');




