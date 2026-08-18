# tasklist-api
simple tasklist API using spring
frontend repo: https://github.com/HirokiSW/tasklist-frontend

API endpoints

GET commands
- /api/tasks
	- gets all tasks
- /api/tasks/(id)
	- gets specified task given id
- /api/tasks/filter/complete?complete=(bool)
	- gets all tasks that are complete/incomplete
- /api/tasks/sort/due
	- gets all tasks sorted by due date in ascending order
- /api/tasks/sort/created
	- gets all tasks sorted by creation date in ascending order

POST commands
- /api/tasks
	- creates and saves a new task given a request body and returns it
		- title: required
		- description: optional
		- dueDate: optional

PUT commands
- /api/tasks/(id)
	- updates specified task and returns it
		- title: optional
		- description: optional
		- dueDate: optional
- /api/tasks/(id)/complete
	- sets complete flag of specified task to true and returns it

DELETE commands
- http://localhost:8080/api/tasks/(id)
	- deletes specified task
