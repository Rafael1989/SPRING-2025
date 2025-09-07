      # Constraints, primary key and foreign key constraints
      # Lets create 2 more  little complicated tables with constraints
create table products(prod_id int not null unique,
                      prod_name varchar(255),
                      brand varchar(255),
                      price float,
                      prod_category varchar(255),
                      PRIMARY KEY (prod_Id));

show tables;

describe products;

insert into products values( 101, 'Sonical M120', 
                            'Sonical', 200, 'mobiles');
insert into products values( 151, 'Sonical L34', 
                            'Sonical', 799, 'laptops');
insert into products values( 105, 'Sonical P03', 
                            'Sonical', 199, 'printers');
insert into products values( 107, 'Fambic M43',  
                            'Fambic', 500, 'mobiles');
insert into products values( 121, 'Fambic M23',  
                            'Fambic', 400, 'mobiles');
insert into products values( 122, 'Fambic T342',  
                            'Fambic', 310, 'televisions');
insert into products values( 123, 'Diallonic M901',  
                            'Diallonic', 810, 'laptops');
insert into products values( 125, 'Diallonic T03',  
                            'Diallonic', 290, 'televisions');
insert into products values( 126, 'Diallonic T04',  
                            'Diallonic', 450, 'televisions');

select * from products;

select brand 
from products;

## The DISTINCT keyword
select distinct brand 
from products;

select distinct prod_category 
from products;

## GROUP BY
select city, count(*) 
from users   
group by city
order by city;

select city, count(*) 
from users   
group by city
order by count(*) desc;

## t will display last_name and it will count how many user_id  are there for this particualr last_name
select last_name, count(*) from users 
group by last_name
order by count(*) desc;

## A column in the select clause must fulfil one of these criteria:
## 1. It should be the group by column
## 2. It should be an aggregate function 
select last_name, first_name, count(*) from users 
group by last_name
order by count(*) desc;


select prod_category, prod_name, brand, price
from products;

select prod_category, count(*)
from products
group by prod_category;

select brand, count(*)
from products
group by brand;

# The AVG function
select prod_category, avg(price)
from products
group by prod_category;

select brand, avg(price)
from products
group by brand;

## The ROUND function
select brand, round(avg(price), 2)
from products
group by brand;

## The MAX function
select brand, max(price)
from products
group by brand;

## The MIN function
select brand, min(price)
from products
group by brand;


select brand, avg(price)
from products
group by brand;

select brand, sum(price)
from products
group by brand;

select brand, sum(price) / count(*)
from products
group by brand;

## Renaming columns
select brand, sum(price) / count(*) as average
from products
group by brand;

select brand as 'Product Brand', 
       sum(price) / count(*) as 'Average Price'
from products
group by brand;


##################################################################
##################################################################
##################################################################


## The HAVING clause
select * from products;

select prod_category, avg(price)
from products
group by prod_category;

## The WHERE clause cannot be used with aggregate operations
select prod_category, avg(price)
from products
where avg(price) > 250
group by prod_category;

## The placement of WHERE does not matter
select prod_category, avg(price)
from products
group by prod_category
where avg(price) > 250;

## The HAVING clause is meant to be used with aggregators
select prod_category, avg(price)
from products
group by prod_category
having avg(price) > 250;

select brand, count(*)
from products
group by brand;

select brand, count(*)
from products
group by brand
having count(*) > 2;

select brand, sum(price), avg(price)
from products
group by brand;

select brand, sum(price), avg(price)
from products
group by brand
having sum(price) < 1300;

## The values in the SELECT clause need not consider the HAVING clause
select brand, avg(price)
from products
group by brand
having sum(price) < 1300;

## Multiple conditions in the HAVING clause
select brand, sum(price), avg(price)
from products
group by brand
having sum(price) < 1300 
and avg(price) >400;

## The some and all functions
select * from products;

select price
from products
where prod_category = 'televisions';

select *
from products
where prod_category = 'mobiles'
and price > some (select price
                  from products
                  where prod_category = 'televisions');
                  
select *
from products
where prod_category = 'mobiles'
and price > all (select price
                 from products
                 where prod_category = 'televisions');


##################################################################
##################################################################
##################################################################

select * from products;

select * from users;

create table orders(order_id int,
                    pid int,
                    uid int,
                    qty int,
                    ship_add varchar(255),
                    ship_date date,
                    ship_status varchar(255),
                    primary key(order_id, pid),
                    foreign key (pid) references products(prod_id),
                    foreign key (uid) references users(user_id) );
describe orders;

insert into orders values(201, 101, 10, 1, 'Pittsburgh', 
                          '2019-11-11', 'shipped');
insert into orders values(208, 105, 15, 13, 'Pleasanton',  
                          '2019-12-23','ordered');
insert into orders values(212, 151, 19, 5, 'Buffalo',  
                          '2019-11-15', 'payment failed');
insert into orders values(204, 151, 11, 10, 'Springfield',  
                          '2019-11-30', 'ordered');
insert into orders values(215, 121, 31, 2, 'Springfield',  
                          '2019-01-30', 'shipped');
insert into orders values(201, 107, 10, 3, 'Pittsburgh',  
                          '2019-11-11', 'shipped');
insert into orders values(208, 121, 15, 2, 'Pleasanton',  
                          '2019-12-23', 'shipped');
insert into orders values(204, 107, 11, 3, 'Springfield',  
                          '2019-11-30', 'shipped');

select * from orders;

## Joining the Users and Orders table
select u.first_name, u.last_name, o.qty, o.ship_status
from users u inner join orders o on u.user_id = o.uid;

## Joining 3 tables
select u.first_name, u.last_name, 
       p.prod_name, p.price,
       o.qty, o.ship_status
from users u inner join orders o on u.user_id = o.uid
             inner join products p on o.pid = p.prod_id;
             
## Creating an alias for the aggregate price
select u.first_name, u.last_name, 
       p.prod_name, p.price,
       o.qty, o.ship_status,
       p.price * o.qty as agg_price
from users u inner join orders o on u.user_id = o.uid
             inner join products p on o.pid = p.prod_id;
             
## The agg_price alias is not recognized by the WHERE clause
select u.first_name, u.last_name, 
       p.prod_name, p.price,
       o.qty, o.ship_status,
       p.price * o.qty as agg_price
from users u inner join orders o on u.user_id = o.uid
             inner join products p on o.pid = p.prod_id
where agg_price > 1000;     

select u.first_name, u.last_name, 
       p.prod_name, p.price,
       o.qty, o.ship_status,
       p.price * o.qty as agg_price
from users u inner join orders o on u.user_id = o.uid
             inner join products p on o.pid = p.prod_id
where p.price * o.qty > 1000;

select o.order_id, o.qty, p.price, o.qty * p.price as agg_price
from orders o inner join products p on o.pid = p.prod_id;

select o.order_id, sum(o.qty * p.price) as order_total
from orders o inner join products p on o.pid = p.prod_id
group by o.order_id;


##################################################################
##################################################################
##################################################################


show index from orders;

show index from users;

show index from products;

describe products;

create index prod_cat_index on products (prod_category);

show index from products;

## This shows how such an index can be defined at the time of table creation
show create table products;

drop index prod_cat_index on products;

show index from products;

## Creating a descending order index
## An ordered index can perform a sort in memory
select prod_category, prod_name, price
from products
order by price desc;

create index prod_price_desc_index
       on products (price desc);
             
show index from products;

describe users;

show index from users;

## Creating a composite index
create index user_name_index 
       on users (last_name, first_name);

show index from users;


##################################################################
##################################################################
##################################################################


select prod_name, brand, prod_category, price
from products
where price > 400;

create view expensive_products as 
select prod_name, brand, prod_category, price
from products
where price > 400;

select * from expensive_products;

insert into products values( 129, 'Fambic T393',  
                            'Fambic', 510, 'televisions');

select * from expensive_products;

select prod_name, brand, prod_category, price
from products
where price > 400;

show tables;

show full tables;

select prod_name, price
from expensive_products;

select prod_name, price
from expensive_products
where price < 600;

select * from expensive_products;

select brand, avg(price)
from expensive_products
group by brand;

select brand, avg(price)
from products
group by brand;


select o.order_id, sum(o.qty * p.price) as order_total
from orders o inner join products p on o.pid = p.prod_id
group by o.order_id;

create view expensive_orders as
select o.order_id, sum(o.qty * p.price) as order_total
from orders o inner join products p on o.pid = p.prod_id
group by o.order_id;

select * from expensive_orders;

select * from expensive_orders
where order_total > 3000;

drop view expensive_products;

show full tables;

drop view expensive_orders;

show full tables;

create view expensive_products as 
select prod_name, brand, prod_category, price
from products
where price > 400;

select * from expensive_products;

insert into expensive_products values(129, 'Fambic T393',  
                                      'Fambic', 510, 'televisions');

##################################################################
##################################################################
##################################################################

describe orders;

select order_id, pid, uid, ship_add, order_date
from orders; 

insert into orders values(215, 107, 34, 2, 
                          'Redmond', '2019-12-17', 
                          'ordered');       
        
select order_id, pid, uid, ship_add, order_date
from orders;

delete from orders
where order_id = 215 and pid = 107;

select order_id, uid, ship_add, order_date
from orders;

select order_id, pid, qty, ship_status
from orders;
        
drop table orders;

create table orders(order_id int,
                    uid int,
                    ship_add varchar(255),
                    order_date date,
                    primary key (order_id),
                    foreign key (uid) references users(user_id));
                  
create table order_product(order_id int, 
                           pid int, 
                           qty int, 
                           ship_status varchar(255),
                           primary key (order_id, pid),
                           foreign key (order_id) references orders (order_id),
                           foreign key (pid) references products (prod_id));
        
insert into orders values(201, 10, 'Pittsburgh', '2019-11-11');

insert into order_product values (201, 101, 1, 'shipped');  
insert into order_product values (201, 107, 3, 'shipped'); 

insert into orders values(208, 15, 'Pleasanton', '2019-12-23'); 

insert into order_product values (208, 105, 13, 'ordered');
insert into order_product values (208, 121, 2, 'shipped');                     

insert into orders values(204, 11, 'Springfield', '2019-11-30');                          
insert into order_product values (204, 151, 10, 'ordered');
insert into order_product values (204, 107, 3, 'shipped');                         

insert into orders values(212, 19, 'Buffalo','2019-11-15');
insert into order_product values (212, 151, 5, 'payment failed'); 

insert into orders values(215, 31, 'Springfield', '2019-01-30');                          
insert into order_product values (215, 121, 2, 'shipped');   


select * from orders;

select * from order_product;
        
select op.order_id, sum(op.qty * p.price)
from order_product op inner join products p on op.pid = p.prod_id
group by op.order_id;



















