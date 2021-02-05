INSERT INTO person (login, password) VALUES ('sergey_morozov', '123');
INSERT INTO person (login, password) VALUES ('ivan', '321');
INSERT INTO person (login, password) VALUES ('s_morozov', '123');


INSERT INTO employee (itn, name, lastname, date)
    VALUES (111111111111, 'Sergey', 'Morozov', NOW());
INSERT INTO employee (itn, name, lastname, date)
    VALUES (222222222222, 'Ivan', 'Ivanov', NOW());

INSERT INTO employee_accounts (employee_id, person_id) VALUES (1, 1);
INSERT INTO employee_accounts (employee_id, person_id) VALUES (1, 3);
INSERT INTO employee_accounts (employee_id, person_id) VALUES (2, 2);