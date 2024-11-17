# Jest Airlines - Airline Ticket Sales System ✈️

This project is a fictional airline ticket sales system.

## 🛠️ Technologies Used

- Java Spring
- Docker
- PostgreSQL
- Stripe API
- AWS (EC2)
- Spring Security (JWT)
- Swagger
- JUnit and Mockito

## 🧭 Application Workflow
- User Registration and Login: Users can create an account and log in securely.
  
- Flight Selection: A single route, Salvador -> São Paulo, is pre-configured as an example.

- Seat Selection: Users can choose their desired seats.

- Reservation Creation: The initial status of the reservation is "Payment Pending."
  
- Stripe Payment: After payment, the Stripe webhook updates the reservation to "Approved," and the selected seats are marked as reserved.

## 📝 API Documentation
The application uses Swagger to document all available endpoints. Access the interactive documentation interface via the /swagger-ui.html endpoint after starting the project.

## 🔧 How to Run the Project Locally

1. Clone the Repository:
```bash
git clone https://github.com/your-username/jest-airlines.git
```
2. Set Up the Database with Docker:
Make sure Docker is installed and run the following command:
```bash
docker run --name postgres-jest -e POSTGRES_PASSWORD=password -e POSTGRES_USER=user -e POSTGRES_DB=jest_airlines -p 5432:5432 -d postgres
```
3. Configure Stripe:
- Create an account on Stripe and obtain your API keys.
- Add these keys to the application.properties file.

4. Build and Run the Application:
```bash
./mvnw spring-boot:run
```
## 🚀 Deployment on AWS
The project is configured for deployment using an EC2 instance on AWS.

### 📌 Project Goals
This project was developed to practice:

- Integration with external APIs (Stripe).
- Unit testing with JUnit and Mockito.
- Security configuration with Spring Security (JWT).
- Deployment on cloud environments (AWS EC2).

