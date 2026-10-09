# Maven Basics

## 1. What is Maven?
Maven is a build automation and project management tool primarily used for Java projects. It standardizes how a project is built, handles dependencies (external libraries), and manages the project's documentation.

## 2. Standard Directory Structure
Maven expects projects to have a specific folder layout:
```text
my-app/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/        # Your main Java source code
    │   └── resources/   # Configuration files, properties, etc.
    └── test/
        ├── java/        # Your test source code (e.g., JUnit)
        └── resources/   # Test-specific configuration
```

## 3. The POM.xml File
The `pom.xml` (Project Object Model) is the core of a Maven project. It contains information about the project and configuration details used by Maven to build the project, such as:
*   Project coordinates (`groupId`, `artifactId`, `version`)
*   Java compiler version
*   Dependencies (external libraries like Spring or JUnit)
*   Plugins (for building, testing, packaging)

## 4. Maven Build Lifecycle
Maven is built around the concept of a build lifecycle. The most common phases are:
*   `clean`: Deletes the `target` directory (removes old compiled files).
*   `compile`: Compiles the source code in `src/main/java`.
*   `test`: Runs the unit tests in `src/test/java`.
*   `package`: Packages the compiled code into a distributable format, like a JAR or WAR file.
*   `install`: Installs the package into the local Maven repository, for use as a dependency in other projects locally.

*Common Command:* `mvn clean package`

## 5. Maven Profiles
Profiles allow you to customize the build for different environments (e.g., `dev` vs `prod`). You can change properties, dependencies, or plugins depending on the active profile. Activate a profile using `-P`: `mvn clean package -P prod`.
