# Margaux's Petitions

**Margaux's Petitions** is a Spring Boot-based web application that allows users to create, manage, and sign petitions. This project was developed as part of a course assignment, with a focus on implementing best practices for software development, testing, and deployment using tools like GitHub, Jenkins, and Docker.

## Features
- **Create Petitions**: Users can create petitions with unique titles and descriptions.
- **View All Petitions**: View a list of all petitions stored in the application.
- **Search Petitions**: Search for petitions by title, with support for case-insensitive and substring matches.
- **Sign Petitions**: Add your name and email as a signature to support a petition.

## Technologies Used
- **Spring Boot**: Backend framework for building the application.
- **Maven**: Build automation tool to manage dependencies and build processes.
- **Java 17**: Programming language used for the application.
- **Docker**: Containerization for deployment.
- **Jenkins**: CI/CD pipeline for building, testing, and deploying the application.
- **GitHub**: Version control for source code management.

## Application Architecture
### Core Classes
1. **Petition**: Represents a petition with a unique ID, title, description, and a list of signatures. Handles the addition of signatures and manages petition properties.
2. **PetitionController**: Manages HTTP endpoints for creating, viewing, searching, and signing petitions. Maps user actions to appropriate views and interfaces with the service layer.
3. **PetitionService**: Contains business logic for managing petitions. Handles petition creation, retrieval, searching, and signature addition while ensuring unique titles. Stores petitions in an in-memory list for simplicity, making it easy to transition to a database in the future.

### Configuration
The `application.properties` file configures:
- The application name as `margauxspetitions`.
- The server to run on port `8081`.
- The context path as `/margauxspetitions`.

## Deployment
### Docker
A `Dockerfile` is provided for containerizing the application. It uses a Tomcat image and deploys the application as a `.war` file. Key configurations include:
- Modifying Tomcat's webapps directory.
- Copying the compiled `.war` file.
- Exposing port `9090` for the container.

### CI/CD with Jenkins
The `Jenkinsfile` defines a pipeline with the following stages:
1. **GetProject**: Clones the repository.
2. **Build**: Cleans and compiles the project using Maven.
3. **Package**: Packages the application as a `.war` file.
4. **Archive**: Archives the `.war` file as a build artifact.
5. **Deploy**: Builds and runs a Docker container for the application, with user approval.

## Testing
### Unit Tests
- **PetitionTest**: Tests petition creation, property updates, and integrity of getter and setter methods.
- **PetitionServiceTest**: Tests business logic for petition creation, retrieval, searching, and signing, ensuring robust error handling and functionality.

## Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/mnauton/margauxspetitions.git
   cd margauxspetitions
   ```

2. Build and run the application using Maven:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

3. Access the application:  
   Open [http://localhost:8081/margauxspetitions](http://localhost:8081/margauxspetitions) in your browser.

4. Run the application in Docker:
   ```bash
   docker build -t margauxspetitions .
   docker run -p 9090:8080 margauxspetitions
   ```

## Future Improvements
- Integrate a database for persistent petition storage.
- Enhance UI for better user experience.
- Implement user authentication for secure petition management.

## License
This project is developed for educational purposes as part of a university assignment.