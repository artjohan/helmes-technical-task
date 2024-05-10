# Description

This is the repository for the Sector Selector technical task for Helmes.

### Features:
- Provides the user with a form to enter their name and current working sectors
- Validates the data
- Saves the users data to the database
- Refills the form with the users data from the database as a response
- Lets the user edit their data during the same session

One session lasts until the close/reload of the page.

# Instructions
To clone the project, run the following command

`git clone https://github.com/artjohan/helmes-technical-task`

## Starting the database

The database uses PostgreSQL and is containerized using Docker

To build the Docker image run the following command in the backend directory:

`docker build -t postgres_image .`

Then run the following command to start the container:

`docker run -d -p 5432:5432 --name postgres_container postgres_image`

This will open the database at port 5432, so make sure that is available

After the Docker container has been created, it can be stopped and started without losing any data by running the following commands:

`docker stop postgres_image`

`docker start postgres_image`

## Running the backend

This project uses Java 21, so make sure your Java version is up to date

To run the backend, navigate to the backend folder and run:

`mvn clean install -DskipTests`

Then navigate into the target folder and run:

`java -jar technical-task-backend-0.0.1-SNAPSHOT.jar`

This will start the backend application at port 8080, so make sure that is also open


## Running the frontend

Make sure your Node.js version is up to date

To run the frontend, navigate to the frontend folder and run:

`npm install`

This installs all the required dependencies

Once that is finished, then you can simply start the application by running:

`npm start`

The application will start at [http://localhost:3000](http://localhost:3000) in your browser, so make sure that port is available

If there are any issues starting the application feel free to contact technical support at artjohanaaspollu@gmail.com, I will get back to you as soon as possible

# Technologies used

- Java 21
- Node.js
- NPM
- Spring Boot
- Docker
- PostgreSQL

# Author

[Art Johan Aaspõllu](http://github.com/artjohan)
