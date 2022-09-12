drop database if exists student_management;
create database student_management;
use student_management;

create table Class (
	class_id int primary key,
    class_name varchar(30)
);

create table Teacher (
	teacher_id int primary key,
    teacher_name varchar(30),
    teacher_country varchar(20),
    teacher_dob date
);