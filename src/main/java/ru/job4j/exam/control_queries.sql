CREATE TABLE company(
    id INTEGER NOT NULL,
    name CHARACTER VARYING,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person(
    id INTEGER NOT NULL,
    name CHARACTER VARYING,
    company_id INTEGER REFERENCES company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES (1, 'Company 1'), (2, 'Company 2'), (3, 'Company 3'), (4,'Company 4'), (5,'Company 5');
INSERT INTO person (id, name, company_id) VALUES (1, 'Person 1', 1), (2, 'Person 2', 2), (3, 'Person 3', 3), (4, 'Person 4', 4),
(5, 'Person 5', 5), (6, 'Person 6', 1), (7, 'Person 7', 2), (8, 'Person 8', 3), (9, 'Person 9', 4), (10, 'Person 10', 5);

SELECT p.name as person, c.name as company
FROM person as p
JOIN company as c
ON p.company_id = c.id
WHERE company_id != 5;