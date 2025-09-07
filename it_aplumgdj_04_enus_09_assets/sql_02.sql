show databases;                    
create database online_shopping;   
show databases;                    
use online_shopping;			  
show tables;                       

# lets create and insert 10 rows to the table users with valid values
create table users(user_id int,
                   first_name varchar(255)NOT NULL,
                   last_name varchar(255),
                   email_id varchar(255),
                   city varchar(255),
                   phone varchar(255) NOT NULL, 
                   primary key(user_id));

describe users;

      # Inserting the rows into table
insert into users values(10, 'Claudia', 'Sand', 
                         'Claudia.Sand@hotmail.com', 'Hanover', 
                         '+1 555 234 5678');
insert into users values (20, 'Julio', 'Chavez',
                          'chavezj317@gmail.com', 'Savannah', 
                          '+1 555 890 9898' );
insert into users values(15, 'Neil', 'Parks', 
                         'neil_parks82@hotmail.com', 'Pleasanton', 
                         '+1 555 567 3456');
insert into users values(19, 'Gina' , 'Ortiz', 
                         'Gina_Ortiz68@hotmail.com', 'Hempstead', 
                         '+1 555 963 8521');
insert into users values(11, 'Raj' , 'Chawanda', 
                         'raj_chawanda@hotmail.com', 'Springfield', 
                         '+1 555 211 2563');
insert into users values(31, 'Marcia', 'Lin', 
                         'Marcia_Lin89@hotmail.com', 'Springfield', 
                         '+1 555 234 8765');
insert into users values(33, 'Chen', 'Lin', 
                         'Marcia_Lin89@hotmail.com', 'Moab', 
                         '+1 555 111 0357');
insert into users values(34, 'Charles', 'Lin', 
                         'chlin@qenel.com', 'Moab', 
                         '+1 555 111 0357');

insert into users values(24, 'Martha', 'Horowitz', 
                         'horowitzmartha@gmail.com', 'Moab', 
                         '+1 555 034 9032');
insert into users values(35, 'Chantal', NULL, 
                         'Chantal_Sands62@hotmail.com', 'Pittsburgh', 
                         '+1 555 432 1098');


# Basic Queries:
# SELECT
select *  from users;   # display/fetch  all rows from users

select first_name 
from users;  # display only first_name 

select first_name, last_name, city, user_id 
from  users;

## The WHERE clause
select first_name, last_name, city, user_id  
from users 
where user_id=24;

select first_name, last_name, city, user_id 
from users 
where last_name='Lin';

select first_name, last_name, city, user_id 
from users 
where city='Moab';


## Combining WHERE clauses
select first_name, last_name, city, user_id 
from users 
where last_name='Lin' and city = 'Moab';

select first_name, last_name, city, user_id 
from users 
where last_name='Lin' or city = 'Moab';


## The != operator
select first_name, last_name, city, user_id 
from users 
where last_name='Lin';

select first_name, last_name, city, user_id 
from users 
where last_name != 'Lin';


## The LIKE operator
select first_name, last_name, city, user_id 
from users
where first_name like 'Charles';

select first_name, last_name, city, user_id 
from users
where first_name like 'C%';

select first_name, last_name, city, user_id 
from users
where first_name like 'c%';

select first_name, last_name, city, user_id 
from users
where first_name like 'ch%';

select first_name, last_name, city, user_id 
from users
where first_name like 'c%a';

select first_name, last_name, city, user_id 
from users
where first_name like 'c%n%';

select first_name, last_name, city, user_id 
from users
where city like '%d';

## The NOT LIKE operator
select first_name, last_name, city, user_id 
from users
where first_name like 'c%';

select first_name, last_name, city, user_id 
from users
where first_name not like 'c%';


## The IN operator
select first_name, last_name, city, user_id 
from users;

select first_name, last_name, city, user_id 
from users
where city = 'Springfield' 
or city = 'Pittsburgh';

select first_name, last_name, city, user_id 
from users
where city = 'Springfield' 
or city = 'Pittsburgh'
or city = 'Hempstead';

select first_name, last_name, city, user_id 
from users
where city in ('Springfield', 'Pittsburgh', 'Hempstead');

select first_name, last_name, city, user_id 
from users
where city not in ('Springfield', 'Pittsburgh', 'Hempstead');

select first_name, last_name, city, user_id 
from users
where city not in ('Springfield', 'Pittsburgh', 'Hempstead')
and first_name like 'ch%'; 



##################################################################
##################################################################
##################################################################

## Creating a related table
create table gold_users(uid int primary key,
				end_date date not null, 
                        points float);

show tables;

describe gold_users;

insert into gold_users values(10, '2020-11-01', 342);
insert into gold_users values(11, '2019-10-03', 241.2);
insert into gold_users values(15, '2019-12-01', 93);
insert into gold_users values(19, '2023-01-13', 13.8);
insert into gold_users values(21, '2021-04-29', 279.1);

select * from gold_users;


## Nested queries
select uid 
from gold_users;

## Returns the details of gold users
select first_name, last_name, user_id
from users
where user_id in (select uid 
			from gold_users);
                  
## The number of values should match what is expected
select first_name, last_name, user_id
from users
where user_id in (select uid, end_date
			from gold_users);

## Foreign keys
## We observe that there are gold_users who aren't in the regular users table
select * 
from gold_users
where uid not in (select user_id
			from users);

## Insert details of the missing user into the User table 
insert into users values(21, 'Noemie', 'Miller', 
                         'noemie_miller@outlook.com', 'Greenwich', 
                         '+1 555 101 1010');

## Now all gold users are accounted for in the Users table
select * 
from gold_users
where uid not in (select user_id
			from users);

describe gold_users;

## Right-click on the create table statement and choose to "Open value in viewer"
SHOW CREATE TABLE gold_users;

## To prevent gold_users from being added without
## previously being added to the Users, add a foreign key constraint
alter table gold_users
add constraint fk_uid
foreign key (uid) references users(user_id);

describe gold_users;

SHOW CREATE TABLE gold_users;

select user_id
from users;

## Inserting a user who's id is present in Users works
insert into gold_users values(33, '2020-05-28', 201.3);

select * from gold_users;

## There is no user with user_id 38 in the Users table
## This insert will fail
insert into gold_users values(38, '2022-02-14', 49);


##################################################################
##################################################################
##################################################################

select * 
from gold_users;

select * 
from gold_users
where points > 150;

select * 
from gold_users
where points < 150;

select * 
from gold_users
where points < 93;

select * 
from gold_users
where points <= 93;

select * 
from gold_users;

select * 
from gold_users
where points >= 93;

select * 
from gold_users
where points >= 93
and points < 250;

select * 
from gold_users
where points between 93 and 250;

select * 
from gold_users
where points between 93 and 241.2;

select * 
from gold_users
where end_date < '2021-01-01';

select * 
from gold_users
where end_date < '2020-11-01';

select * 
from gold_users
where end_date <= '2020-11-01';

select * 
from gold_users
where end_date >= '2020-11-01' ;

select * 
from gold_users
where end_date between '2020-05-28' and '2021-04-29';

select curdate();

select * 
from gold_users
where end_date < curdate();

select * 
from gold_users
where end_date >= curdate();

select * from gold_users;

select * 
from gold_users 
order by points;

select * 
from gold_users 
order by points desc;

select * 
from gold_users 
order by points desc
limit 3;

select * 
from gold_users 
order by points desc
limit 1;

## Apply order by on the date field
select * 
from gold_users
where end_date >= curdate()
order by end_date;

select * 
from gold_users
where end_date >= curdate()
order by end_date desc;

select * 
from gold_users
where end_date >= curdate()
order by points desc;


## order by based on multiple fields
insert into gold_users values (34, '2020-05-28', 132.9);

select *
from gold_users
order by end_date;

select *
from gold_users
order by end_date, points;

select *
from gold_users
order by end_date, points desc;

select *
from gold_users
order by end_date desc, points desc;

select * 
from users 
order by last_name, first_name;


##################################################################
##################################################################
##################################################################

select * from users;

select * from gold_users;

## Get the name, city of gold users
select first_name, last_name, city
from users
where user_id in (select uid 
				  from gold_users);

## Get the name, city of active gold users
select first_name, last_name, city
from users
where user_id in (select uid 
				  from gold_users
				  where end_date >= curdate()); 

## Joining two tables based on a common value
select *
from users, gold_users
where user_id = uid;

## Select the specific columns which are required
select first_name, last_name, city, points, end_date
from users, gold_users
where user_id = uid;

## When a join condition is not specified, a cross product is generated
select first_name, last_name, city, points, end_date
from users, gold_users;

## Conditions can be applied in the where clause beyond the join condition
select first_name, last_name, city, points, end_date
from users, gold_users
where user_id = uid
and end_date >= curdate();

## It is always a good idea to use aliases for each table in a join
## This is to resolve any ambiguities and to make it known which columns
## are being retrieved from which table
select u.first_name, u.last_name, u.city, g.points, g.end_date
from users u, gold_users g
where u.user_id = g.uid
and g.end_date >= curdate();

## Use the join keyword to explicitly state a join is being performed
## When it is not specified what the join condition is,
## a cross product is calculated
select u.first_name, u.last_name, u.city, g.points, g.end_date
from users u join gold_users g
where g.end_date >= curdate();

select u.first_name, u.last_name, u.city, g.points, g.end_date
from users u join gold_users g on u.user_id = g.uid
where g.end_date >= curdate();

## Inner, Left and Right joins
## Start off by taking a look at what a joing does
select u.first_name, u.last_name, u.city, g.points, g.end_date
from users u join gold_users g on u.user_id = g.uid;

## An inner join is the default type of join
select u.first_name, u.last_name, u.city, g.points, g.end_date
from users u inner join gold_users g on u.user_id = g.uid;

## A left join includes all rows of the left table,
## whether or not there is a matching entry in the right table.
## Unmatched rows will have null for all values in the right table
select u.first_name, u.last_name, u.city, g.points, g.end_date
from users u left join gold_users g on u.user_id = g.uid;

## Do demonstrate a right join, we switch the tables around
## First show the results of a left join
select g.points, g.end_date, u.first_name, u.last_name, u.city
from gold_users g left join users u on u.user_id = g.uid;

## The right join does the inverse of a left join
select g.points, g.end_date, u.first_name, u.last_name, u.city
from gold_users g right join users u on u.user_id = g.uid;

## The order of columns in the select clause has no effect
## on what is treated as the left and right table
select u.first_name, u.last_name, u.city, g.points, g.end_date 
from gold_users g right join users u on u.user_id = g.uid;

## Ordering can be performed on the results of a join
select u.first_name, u.last_name, u.city, g.points, g.end_date 
from gold_users g right join users u on u.user_id = g.uid
order by g.points desc;











