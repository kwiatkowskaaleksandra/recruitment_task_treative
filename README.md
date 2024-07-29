# Recruitment Task for Treative

## Project Overview

The task involves creating a simplified application to simulate the spread of an epidemic, such as COVID-19. The application allows users to create simulations with initial parameters and observe the results over a specified period.

## Project Structure

The project is divided into two main parts:

1. **Backend (`treative_backend`)**: Built using Spring Boot and PostgreSQL, this part handles the logic and data storage for the simulation.
2. **Frontend (`treative_frontend`)**: Developed using Angular, this part provides a user interface for creating and viewing simulations.

## Technologies Used

- **Backend**:
  - Spring Boot
  - PostgreSQL
  - JPA/Hibernate

- **Frontend**:
  - Angular 17

## Getting Started

### Prerequisites

Make sure you have the following installed on your system:

- Java 17
- Node.js 21.7.3
- PostgreSQL

### Setting Up the Backend

1. Navigate to the `treative_backend` directory:

    ```bash
    cd treative_backend
    ```

2. Configure the database connection in `application.properties` file.

3. Build and run the backend application:

    ```bash
    ./mvnw spring-boot:run
    ```

### Setting Up the Frontend

1. Navigate to the `treative_frontend` directory:

    ```bash
    cd treative_frontend
    ```

2. Install the dependencies:

    ```bash
    npm install
    ```

3. Serve the frontend application:

    ```bash
    ng serve
    ```

### Running the Application

Once both backend and frontend are up and running, you can access the application in your web browser at `http://localhost:4200`.

## Simulation Parameters

Users can create simulations by specifying the following initial parameters:

- **N**: Name of the simulation (string)
- **P**: Population size (integer)
- **I**: Initial number of infected individuals (integer)
- **R**: Infection rate (double)
- **M**: Mortality rate (double)
- **Ti**: Days until recovery (integer)
- **Tm**: Days until death (integer)
- **Ts**: Total days of simulation (integer)

## Simulation Results

The simulation generates daily results, which include:

- Number of infected individuals
- Number of healthy individuals
- Number of recovered individuals
- Number of deceased individuals
