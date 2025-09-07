## Download MySQL:
# https://dev.mysql.com/downloads/mysql/ 

## Install the package
## Navigate to System preferences and look for MySQL. 
## When you click on it, you should see it running
## Turn it off and then back on


## Navigate to the shell and look for the mysql process
## This gives you an idea of where mysql is installed
ps -ef | grep mysql

which mysqld

# Head to the MySQL directory
cd /usr/local/mysql

ls -n

cd bin

./mysql --version

## Login to mysql. You will be prompted for the password
./mysql -u root -p 

# View all databases. The ones listed now are the built-in ones
show databases;

# View variables. The output is really large
SHOW GLOBAL VARIABLES;

SHOW GLOBAL VARIABLES LIKE 'PORT';

show databases;

create database first_database;

show databases;

use first_database;

show tables;

create table person(user_id int,
		    first_name varchar(255),
		    last_name varchar(255),
		    email_id varchar(255),
		    city varchar(255),
		    phone varchar(255));
show tables;

describe person;

select * from person;

insert into person values(10,
			  'Claudia',
		          'Sand',
			  'Claudia.Sand@hotmail.com',
			  'Hanover',
			  '+1 555 234 5678');

select * from person;

## Exit the MySQL Shell. We will head over to the 
exit

## Open MySQL Workbench. It comes in a dark theme by default
## Close down MySQL Workbench

## Disable the dark theme by running this from the shell
defaults write com.oracle.workbench.MySQLWorkbench NSRequiresAquaSystemAppearance -bool yes

## Re-open MySQL Workbench. It no longer uses the dark theme

## Follow the steps below to create a new connection to the DB Server from MySQL Workbench

 MySQL Workbench -> Acts as a "graphical user interface", it is a client that will connects to my sql server and you can use that interface to create queries

 Downloading steps:

   step 1: Access to this link (https://dev.mysql.com/downloads/workbench/)
   
   step 2: select operating system - > click on "MAC OS" (in case if you are using MacBook) and click on  "download"  -> to continue click on "NO thanks, Just start my download"

   step 3: click and drag the icon "MySQL workbench" into the "Application folder"
   step 4: go to "Launchpad" on your machine and click on "MySQL Workbench" - > "open" to open MySQL Workbench

   step 5: Once MySQL workbench opens -> go to database  menu and click on manage connection
           you can click on new and can create new connection 
           or else you can click on the available default connection

   step 6: username will be "username"  and enter password for your username
           click on ok button -> click on close

   step 7: by double clicking on local instance 3306 you can open a editor -> here you can create database 
           -> by using that database you can create a queries and you can see the results down below