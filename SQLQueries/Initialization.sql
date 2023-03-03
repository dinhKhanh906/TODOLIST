create database ToDoList
use ToDoList

create table Task
(
	id INT IDENTITY	PRIMARY KEY,
	grName NVARCHAR(15) NOT NULL foreign key references Gr,
	title NVARCHAR(15) NOT NULL,
	detail NVARCHAR(300),
	createTime DATETIME NOT NULL DEFAULT GETDATE(), -- fomat YYYY-MM-DD HH:MI:SS
	deadline DATETIME NOT NULL,
	reminder BIT NOT NULL CHECK (reminder = 0 or reminder = 1) DEFAULT 0,
)

drop table Task

create table Gr
(
	grName NVARCHAR(15) primary key,
)

drop table Gr

INSERT INTO Gr VALUES ('HOME')
INSERT INTO Task (grName, title, detail, deadline, reminder) VALUES ('HOME', 'clean house', 'soooo clean', '2022-12-01', 1)
select * from Task