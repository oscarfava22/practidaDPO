DROP DATABASE IF EXISTS Restaurant;
CREATE DATABASE Restaurant;
USE Restaurant;

DROP TABLE IF EXISTS Mesa;
DROP TABLE IF EXISTS Reserva;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS Comanda;
DROP TABLE IF EXISTS Plato;

CREATE TABLE Mesa(
    id_mesa int,##long,
    num_comensales int,
    PRIMARY KEY(id_mesa)
);

CREATE TABLE Cliente(
	password varchar(255),
    nombre varchar(255),
    PRIMARY KEY (password)
);

CREATE TABLE Plato(
	id_plato int,##long,
    nombre varchar(255),
    unidades int,
    id_tipus int,
    PRIMARY KEY (id_plato)    
);

CREATE TABLE Reserva(
	id_reserva int,##long,
    id_mesa int,##long,
    data date, ## año mes y dia
    dataConcreta DateTime, ## Hora minuto y segundo
    password varchar(255),
    PRIMARY KEY(id_reserva),
    FOREIGN KEY (password) REFERENCES Cliente(password), 
    FOREIGN KEY (id_mesa) REFERENCES Mesa(id_mesa)
);

CREATE TABLE Comanda(
    id_plato int,##long,
    password varchar(255),
    tiempo Date,
    tiempoConcreto DateTime,    
    PRIMARY KEY (id_plato, password, tiempo),
    FOREIGN KEY (password) REFERENCES Cliente(password), 
    FOREIGN KEY (id_plato) REFERENCES Plato(id_plato)
);

select * from Mesa;