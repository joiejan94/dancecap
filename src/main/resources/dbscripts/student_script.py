from random import *

first_name = ['Johanna', 'Aly', 'Toby', 'Molly', 'Briana', 'Jessie', 'Christina', 'Dominique', 'Kim', 'David', 'Lou', 'Andy', 'David', 'Dawn']
last_name = ['Jan', 'Grassi', 'Driscoll', 'Towne', 'Prettyman', 'Lamet', 'Iervoline', 'Buchanan', 'Fresco', 'Lawless', 'Novocin', 'Bellamy', 'Berk', 'Rossi']

majors = ['Computer Science','Math','Physics','Exercise Science','History','Edudation','English','Chemical Engineering','Mechanical Engineering','Economics','Statistics','Sociology','Psychology','Art','Leadership','Hospitality','Finance','Biology','Chemistry','Foreign Language']
years = [2016,2017,2018,2019]

def insert_student(id, major,year):
	query = "INSERT INTO `dancecap`.`Student` (`id`,`major`,`year`) VALUES (" + str(id) + ", '" + major + "', " + str(year) + ");"
	return query

target = open('students.sql', 'w')

for i in range(100):
	idx = 701000000 + i
	major = majors[randint(0,len(majors)-1)]
	year = years[randint(0,3)]
	# print(insert_student(idx,major,year))
	target.write(insert_student(idx,major,year) + '\n')
	
target.close()

