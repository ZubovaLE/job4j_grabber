create table company(
    id integer not null,
    name CHARACTER VARYING,
    constraint company_pkey primary key (id)
);

create table person(
    id integer not null,
    name CHARACTER VARYING,
    company_id integer references company(id),
    constraint person_pkey primary key (id)
);

insert into company (id, name) values (1, 'Company 1'), (2, 'Company 2'), (3, 'Company 3'), (4,'Company 4'), (5,'Company 5');
insert into person (id, name, company_id) values (1, 'Person 1', 1), (2, 'Person 2', 2), (3, 'Person 3', 3), (4, 'Person 4', 4),
(5, 'Person 5', 5), (6, 'Person 6', 1), (7, 'Person 7', 2), (8, 'Person 8', 3), (9, 'Person 9', 4), (10, 'Person 10', 5), (11, 'Person 11', 1);

insert into person (id, name, company_id) values (12, 'Person 12', 2), (13, 'Person 13', 3);

--select the names of all persons who are not in the company with id = 5;
select name from person where company_id != 5;

--select a company name for each person
select p.name as person, c.name as company
from person as p
join company as c
on p.company_id = c.id;

--select the company name with the maximum number of people + number of people in this company
select c.name as company, count(p.id) as count_of_people
from company as c
join person as p
on p.company_id = c.id
group by c.name;