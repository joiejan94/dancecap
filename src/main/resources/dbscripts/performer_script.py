from random import *

first_name = ['Johanna', 'Aly', 'Toby', 'Molly', 'Briana', 'Jessie', 'Christina', 'Dominique', 'Kim', 'David', 'Lou', 'Andy', 'David', 'Dawn']
last_name = ['Jan', 'Grassi', 'Driscoll', 'Towne', 'Prettyman', 'Lamet', 'Iervoline', 'Buchanan', 'Fresco', 'Lawless', 'Novocin', 'Bellamy', 'Berk', 'Rossi']

Style = ['JAZZ','HIPHOP','BALLET','CONTEMPORARY','MODERN','TAP','THEATRE','OTHER']
dances = ['Human Journey','(re:)','Madhouse','Dancers & Tappers','Mazz Swift','Colorboat','Same Story, Different Countries','National Water Dance','Silly Things','I Cant Lose','Work Song','Sparkling Diamonds','Say You Wanna Dance','Sorry','We Dont Have To','Home','The Buzz']
def insert_performer(id, dance):
	query = "INSERT INTO `dancecap`.`Performer` (`id`,`dance`) VALUES (" + str(id) + ", '" + dance + "');"
	return query

target = open('performers.sql', 'w')

for dance in dances:
	numDancers = randint(5,15)
	for idx in sample(range(701000000,701000099), numDancers):
		target.write(insert_performer(idx,dance) + '\n')
	
target.close()

