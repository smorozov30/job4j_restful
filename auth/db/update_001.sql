CREATE TABLE person (
    id SERIAL PRIMARY KEY NOT NULL,
    login VARCHAR(2000),
    password VARCHAR(2000)
);

INSERT INTO person (login, password) VALUES ('sergey_morozov', '123');
INSERT INTO person (login, password) VALUES ('ivan', '321');
INSERT INTO person (login, password) VALUES ('s_morozov', '123');

CREATE TABLE employee (
    id SERIAL PRIMARY KEY NOT NULL,
    itn BIGINT,
    name VARCHAR(2000),
    lastname VARCHAR(2000),
    date TIMESTAMP DEFAULT NOW()
);

INSERT INTO employee (itn, name, lastname, date)
    VALUES (111111111111, 'Sergey', 'Morozov', NOW());
INSERT INTO employee (itn, name, lastname, date)
    VALUES (222222222222, 'Ivan', 'Ivanov', NOW());

CREATE TABLE employee_accounts (
    employee_id INT,
    person_id INT UNIQUE,
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (person_id) REFERENCES person (id)
);

INSERT INTO employee_accounts (employee_id, person_id) VALUES (1, 1);
INSERT INTO employee_accounts (employee_id, person_id) VALUES (1, 3);
INSERT INTO employee_accounts (employee_id, person_id) VALUES (2, 2);