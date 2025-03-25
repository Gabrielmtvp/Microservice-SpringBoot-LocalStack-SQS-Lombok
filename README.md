# Microservice-SpringBoot-LocalStack-SQS-Lombok

## Overview

This project is a microservice built using Spring Boot that interacts with AWS SQS using LocalStack for local testing and development. It also leverages Lombok to reduce boilerplate code in Java classes.

## Features

Spring Boot-based microservice architecture

Integration with AWS SQS for message queuing

LocalStack for simulating AWS services locally

Lombok for simplified Java development

Docker support for running LocalStack

Prerequisites

Java 17+ (ensure it's installed and configured)

Maven 3+

Docker (for running LocalStack)

Installation

1. Clone the Repository

 ```
git clone https://github.com/your-username/Microservice-SpringBoot-LocalStack-SQS-Lombok.git
 cd Microservice-SpringBoot-LocalStack-SQS-Lombok
```

2. Start LocalStack

Ensure that Docker is running, then execute:

```
docker-compose up -d
```
![Screenshot 2025-03-25 at 21 26 16](https://github.com/user-attachments/assets/0221ee3e-70dd-4ac6-bd69-66942ffddf9c)

This will start LocalStack with SQS support.

3. Build the Project

```
mvn clean install
```

4. Run the Application

```
mvn spring-boot:run
```

The application will start on http://localhost:8080.

Configuration

Modify the application.yml to set up SQS queues:

```
localstack:
  host: localhost
aws:
  region: us-east-1
  sqs:
    process-order-queue: http://sqs.us-east-1.localhost.localstack.cloud:4566/000000000000/process-order-queue
  access-key: test -- Add your local aws access key
  secret-key: test -- Add your local aws secret key
```

Testing the SQS Integration

Send a Message

```
curl --location --request POST 'http://localhost:8080/checkout/create-payment'
```

![Screenshot 2025-03-25 at 21 09 02](https://github.com/user-attachments/assets/289681a3-0658-48c4-9a69-67792c1c0e01)

## Technologies Used

✅ Spring Boot - Framework for building Java applications

✅ AWS SQS - Simple Queue Service for messaging

✅ LocalStack - Simulates AWS services locally

✅ Lombok - Java library to minimize boilerplate code

✅ Docker - Containerization platform for LocalStack

✅ Maven - Build and dependency management tool

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contributing

Contributions are welcome! Please submit a pull request or open an issue.
