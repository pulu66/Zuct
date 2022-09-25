--
-- Database: zuct_db
--

CREATE DATABASE zuct_db;

-- ENTITIES

--
-- Schema entity user
--

CREATE TABLE IF NOT EXISTS "user" (
	mail varchar(130) ,
	name varchar(130) ,
	password varchar(130)  NOT NULL,
	roles varchar(130) ,
	surname varchar(130) ,
	username varchar(130)  NOT NULL,
	
	_id SERIAL PRIMARY KEY

);


-- Security

ALTER TABLE "user" ALTER COLUMN "password" TYPE varchar(128);

INSERT INTO "user" (username, password, _id) VALUES ('admin', '62f264d7ad826f02a8af714c0a54b197935b717656b80461686d450f7b3abde4c553541515de2052b9af70f710f0cd8a1a2d3f4d60aa72608d71a63a9a93c0f5', 1);

CREATE TABLE IF NOT EXISTS "roles" (
	role varchar(30) ,
	
	-- RELAZIONI

	_user INTEGER  NOT NULL REFERENCES "user"(_id),
	_id SERIAL PRIMARY KEY 

);
INSERT INTO "roles" (role, _user, _id) VALUES ('ADMIN', '1', 1);

--
-- Schema entity courses
--

CREATE TABLE IF NOT EXISTS "courses" (
	course_code varchar(30)  NOT NULL,
	course_name varchar(130)  NOT NULL,
	
	_id SERIAL PRIMARY KEY

);

--
-- Schema entity lecturers
--

CREATE TABLE IF NOT EXISTS "lecturers" (
	l_first_name varchar(130)  NOT NULL,
	l_gender varchar(130)  NOT NULL,
	l_last_name varchar(130)  NOT NULL,
	l_middle_name varchar(130) ,
	l_phone numeric ,
	
	_id SERIAL PRIMARY KEY

);

--
-- Schema entity programs
--

CREATE TABLE IF NOT EXISTS "programs" (
	pr_description varchar(130)  NOT NULL,
	pr_name varchar(130)  NOT NULL,
	
	_id SERIAL PRIMARY KEY

);

--
-- Schema entity students
--

CREATE TABLE IF NOT EXISTS "students" (
	date_of_birth date  NOT NULL,
	first_name varchar(130)  NOT NULL,
	last_name varchar(130)  NOT NULL,
	middle_name varchar(130) ,
	phone numeric ,
	sex varchar(130)  NOT NULL,
	
	_id SERIAL PRIMARY KEY

);




-- relation 1:m _contains courses - programs
ALTER TABLE courses ADD COLUMN _contains INTEGER  REFERENCES "programs"(_id);

-- relation 1:m _teaches courses - lecturers
ALTER TABLE courses ADD COLUMN _teaches INTEGER  REFERENCES "lecturers"(_id);

-- relation 1:m _contain lecturers - programs
ALTER TABLE lecturers ADD COLUMN _contain INTEGER  REFERENCES "programs"(_id);

-- relation 1:m _enrolls students - programs
ALTER TABLE students ADD COLUMN _enrolls INTEGER  REFERENCES "programs"(_id);

-- relation m:m _takes students - courses
CREATE TABLE IF NOT EXISTS "students__takes" (
    _id SERIAL PRIMARY KEY,
    id_students INTEGER  NOT NULL REFERENCES "students"(_id),
    id_courses INTEGER  NOT NULL REFERENCES "courses"(_id)
);

-- relation m:m _taught students - lecturers
CREATE TABLE IF NOT EXISTS "students__taught" (
    _id SERIAL PRIMARY KEY,
    id_students INTEGER  NOT NULL REFERENCES "students"(_id),
    id_lecturers INTEGER  NOT NULL REFERENCES "lecturers"(_id)
);
