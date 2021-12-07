TASK MANAGEMENT APP


Description:

The sample-docker-app is an application for task management helping a user to create, update and delete tasks.

A user can enter the name of the task and the select time to be scheduled when creating a new task.

If the user wants to update an existing task, he can select the radio button in the UI for the corresponding task and update the date to a new date and submit. Please note that only the schedule date of the task can be modified and not the name.

If a user wants to remove a task, he needs to select the radio button of the corresponding task and click on the Delete button.

Technical features:

The back end of the application has Java Spring Boot REST services.
UI is developed using React JS.

Build and Deployment:
Please go to the main folder of the application and perform below commands.
Building the back end java spring boot app:

1) Build the application using Docker

docker build —tag sample-docker:latest .

2) Run the docker image using below command which will map the port 8080 of container to the port 8080 of the host

docker run --rm —p 8080:8080 sample-docker:latest

Building the front end React application:

Please go to web folder in the application and perform below commands:

Build the application using Docker

docker build -f Dockerfile -t webui .

2) Run the docker image using below command which will map the port 3000 of the host to the port 3001 of container

docker run —rm -v ${PWD}:/app it -v /app/node_modules -p 3001:3000 webui

Accessing the application:

Once the services are running,

Access the URL  http://localhost:3001




