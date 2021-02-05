CREATE TABLE person (
    id SERIAL PRIMARY KEY NOT NULL,
    login VARCHAR(2000),
    password VARCHAR(2000)
);

CREATE TABLE employee (
    id SERIAL PRIMARY KEY NOT NULL,
    itn BIGINT,
    name VARCHAR(2000),
    lastname VARCHAR(2000),
    date TIMESTAMP
);

CREATE TABLE employee_accounts (
    employee_id INT,
    person_id INT UNIQUE,
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (person_id) REFERENCES person (id)
);