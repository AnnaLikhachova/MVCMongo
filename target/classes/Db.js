##### COMMANDS TO CREATE A SAMPLE DATABASE & COLLECTION #####
> use mydb

> db.users.insertMany([{"id" : "123", name : "anna", password: "anna", email: "no"}, {"id": "1", name: "no", password: "no", email: "no"}])

##### COMMAND TO DISPLAY THE DOCUMENTS OF A COLLECTION #####
> db.mycollection.find()