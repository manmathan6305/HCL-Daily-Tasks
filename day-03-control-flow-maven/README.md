# Day 3: Control Flow & Maven

## Overview
We're looking at Java control flow (loops, conditionals) and setting up projects with Maven.

## Topics
- Control flow: if/else, switch statements.
- Loops: for, while, do-while.
- Branching: break, continue.
- Maven project structure and pom.xml.
- Maven build lifecycle.

## Exercises
1. Build an ATM simulator in `src/main/java/ATMSimulator.java`.
2. Implement a console menu with do-while and switch statements.
3. Validate user inputs.
4. Compile and package the project:
   ```bash
   mvn clean package
   ```
5. Run the application:
   ```bash
   java -cp target/atm-simulator-1.0-SNAPSHOT.jar ATMSimulator
   ```
