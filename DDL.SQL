-- Crear base de datos
CREATE DATABASE IF NOT EXISTS library;
USE library;

-- Tabla: authors
CREATE TABLE authors (
    id_author INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    nationality VARCHAR(50),
    a_status BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla: users
CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20),
    u_status BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla: books
CREATE TABLE books (
    id_book INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    year INT,
    id_author INT NOT NULL,
    b_status ENUM('A','U','L') NOT NULL DEFAULT 'A',

    CONSTRAINT fk_book_author
        FOREIGN KEY (id_author)
        REFERENCES authors(id_author)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- Tabla: loans
CREATE TABLE loans (
    id_loan INT AUTO_INCREMENT PRIMARY KEY,
    loan_date DATE NOT NULL,
    return_date DATE,
    id_user INT NOT NULL,
    id_book INT NOT NULL,

    CONSTRAINT fk_loan_user
        FOREIGN KEY (id_user)
        REFERENCES users(id_user)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT fk_loan_book
        FOREIGN KEY (id_book)
        REFERENCES books(id_book)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);