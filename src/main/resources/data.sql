-- -- Create the department table
-- CREATE TABLE IF NOT EXISTS department (
--                     id BIGINT PRIMARY KEY,
--                     title VARCHAR(255) NOT NULL
-- );
--
-- -- Create the person table
-- CREATE TABLE IF NOT EXISTS  person (
--                     id BIGINT PRIMARY KEY,
--                     name VARCHAR(255) NOT NULL,
--                     department_id BIGINT REFERENCES department(id)
-- );
--
-- -- Create the task table
-- CREATE TABLE IF NOT EXISTS  task (
--                       id BIGINT PRIMARY KEY,
--                       title VARCHAR(255) NOT NULL,
--                       description TEXT,
--                       due_date TIMESTAMP,
--                       department_id BIGINT REFERENCES department(id),
--                       duration INTEGER,
--                       person_id BIGINT REFERENCES person(id),
--                       finished BOOLEAN
-- );


insert into department (id, title) VALUES
                                    (1, 'Financeiro'),
                                    (2, 'Comercial'),
                                    (3, 'Desenvolvimento');

insert into person (id, name, department_id) VALUES
                                             (1, 'Camila', 1),
                                             (2, 'Pedro', 2),
                                             (3, 'Fabiano', 3),
                                             (4, 'Raquel', 3),
                                             (5, 'Patricia', 3),
                                             (6, 'Joaquim', 1);

insert into task (id, title, description, due_date, department_id, duration, person_id, finished) VALUES
                                          (1001, 'Validar NF Janeiro', 'Validar notas recebidas no mês de Janeiro', '2022-02-15 00:00:00', 1, 14, 1, true),
                                          (1003,'Liberação da versão 1.24', 'Disponibilizar pacote para testes', '2022-05-10 00:00:00', 3, 2, 3, false),
                                          (1006, 'Pagamento 01/2022', 'Realizar pagamento dos fornecedores', '2022-01-31 00:00:00', 1, 6, 1, true),
                                          (1007, 'Bug 401', 'Corrigir bug 401 na versão 1.20', '2022-02-01 00:00:00', 3, 2, 4, true),
                                          (1008, 'Bug 399', 'Corrigir bug 399 na versão 1.20', '2022-01-28 00:00:00', 3, 6, 5, true),
                                          (1009, 'Reunião B', 'Reunião com cliente B para apresentação do produto', '2022-01-31 00:00:00', 2, 5, 2, true),
                                          (1010, 'Validar NF Fevereiro', 'Validar notas recebidas no mês de Fevereiro', '2022-03-15 00:00:00', 1, 14, 6, false);

insert into task(id ,title, description, due_date, department_id, duration, finished) VALUES
                                                                                     (1002, 'Bug 352', 'Corrigir bug 352 na versão 1.25', '2022-05-10 00:00:00', 3, 25, true),
                                                                                     (1004, 'Reunião A', 'Reunião com cliente A para apresentação do produto', '2022-02-05 00:00:00', 2, 5, true),
                                                                                     (1005, 'Reunião final', 'Fechamento contrato', '2022-03-28 00:00:00', 2, 6, true);
