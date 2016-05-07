from random import *

first_name = ['Johanna', 'Aly', 'Toby', 'Molly', 'Briana', 'Jessie', 'Christina', 'Dominique', 'Kim', 'David', 'Lou', 'Andy', 'David', 'Dawn']
last_name = ['Jan', 'Grassi', 'Driscoll', 'Towne', 'Prettyman', 'Lamet', 'Iervoline', 'Buchanan', 'Fresco', 'Lawless', 'Novocin', 'Bellamy', 'Berk', 'Rossi']

Style = ['JAZZ','HIPHOP','BALLET','CONTEMPORARY','MODERN','TAP','THEATRE','OTHER']

def insert_person(id, name, style):
	query = "INSERT INTO `dancecap`.`Person` (`id`,`name`,`style`) VALUES (" + str(id) + ", '" + name + "', '" + style + "');"
	return query

target = open('persons.sql', 'w')

for i in range(100):
	idx = 701000000 + i
	name = first_name[randint(0,13)] + " " + last_name[randint(0,13)]
	style = Style[randint(0,7)]
	target.write(insert_person(idx,name,style) + '\n')
	
target.close()

