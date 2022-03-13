# GetBetterBackEnd
Backend for School project Assignment
This code base is responsible for the functionalies for the medical software which includes:
 - Send Emails
 - Use Api retrieving covid cases from different counries
 - Add, Update, Search and Delete record from a MYSQL Database

#Requirements
JAR files 
 - json-simple-1.1.1.jar
 - mysql-connector-java-8.0.28.jar
 - javax.mail.jar
 - activivation-1.1.1.jar

#Modification to code
 - connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/nameofdatabase","username","password");
 - defaultProps.setProperty("mail.user", "senderEmail");
 - defaultProps.setProperty("mail.password", "passwordForsenderEmail");


#SQL Codes for SQL database
  - Creating database: CREATE DATABASE getbetter_db;
  - Create users Table: CREATE TABLE users(
                     staff_ID int not null AUTO_INCREMENT,
                     username varchar(25) not null,
                     password varchar(25) not null,
                     PRIMARY KEY (staff_ID)
                     );
                     
  - Create patients table: CREATE TABLE PATIENTS(
                            client_ID int not null auto_increment,
                            first_name varchar(25) not null,
                            last_name varchar(25) not null,
                            sex varchar(10) not null,
                            address varchar(100) not null,
                            email varchar(50) not null,
                            dob varchar(50) not null,
                            phone_number varchar(12),
                            primary key(client_ID)
                            );
                            
  - Create medicaldetails table: CREATE TABLE medicaldetails(
                                  client_ID int not null,
                                  temperature int not null,
                                  date varchar(50) not null,
                                  vaccination_status varchar(50) not null,
                                  vac_date_taken varchar(30) not null,
                                  covid_test_taken varchar(30) not null,
                                  covid_test_result varchar(225) not null,
                                  appointment_date varchar(50) not null,
                                  INDEX par_ind (client_ID),
                                  CONSTRAINT fk_patient FOREIGN KEY (client_ID)  
                                  REFERENCES patients(client_ID)  
                                  ON DELETE CASCADE  
                                  ON UPDATE CASCADE  
                                  );
      
