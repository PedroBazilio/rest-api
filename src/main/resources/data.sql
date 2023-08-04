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


insert into department (title) VALUES
                                    ('Financeiro'),
                                    ('Comercial'),
                                    ('Desenvolvimento');

insert into person (name, department_id) VALUES
                                             ('Camila', 1),
                                             ('Pedro', 2),
                                             ('Fabiano', 3),
                                             ('Raquel', 3),
                                             ('Patricia', 3),
                                             ('Joaquim', 1);

insert into task (title, description, due_date, department_id, duration, person_id, finished) VALUES
                                          ('Validar NF Janeiro', 'Validar notas recebidas no mês de Janeiro', '2022-02-15 00:00:00', 1, 14, 1, true),
                                          ('Liberação da versão 1.24', 'Disponibilizar pacote para testes', '2022-05-10 00:00:00', 3, 2, 3, false),
                                          ('Pagamento 01/2022', 'Realizar pagamento dos fornecedores', '2022-01-31 00:00:00', 1, 6, 1, true),
                                          ('Bug 401', 'Corrigir bug 401 na versão 1.20', '2022-02-01 00:00:00', 3, 2, 4, true),
                                          ('Bug 399', 'Corrigir bug 399 na versão 1.20', '2022-01-28 00:00:00', 3, 6, 5, true),
                                          ('Reunião B', 'Reunião com cliente B para apresentação do produto', '2022-01-31 00:00:00', 2, 5, 2, true),
                                          ('Validar NF Fevereiro', 'Validar notas recebidas no mês de Fevereiro', '2022-03-15 00:00:00', 1, 14, 6, false);

insert into task(title, description, due_date, department_id, duration, finished) VALUES
                                                                                     ('Bug 352', 'Corrigir bug 352 na versão 1.25', '2022-05-10 00:00:00', 3, 25, true),
                                                                                     ('Reunião A', 'Reunião com cliente A para apresentação do produto', '2022-02-05 00:00:00', 2, 5, true),
                                                                                     ('Reunião final', 'Fechamento contrato', '2022-03-28 00:00:00', 2, 6, true);

