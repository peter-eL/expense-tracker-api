# Expense tracker API


Using Spring boot, PostgreSQL and JSON Web Tokens

## Setup and Installation

**Setting up postgreSQL in docker**

    docker container run --name postgresdb -e POSTGRES_PASSWORD=admin -d -p 5432:5432 postgres
    docker container cp expensetracker_db.sql postgresdb:/
    docker container exec -it postgresdb bash
    psql -U postgres --file expensetracker_db.sql
    ./mvnw spring-boot:run
    
Runs on port 8080.