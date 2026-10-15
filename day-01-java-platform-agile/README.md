# Day 1: Java Platform Basics & Agile/Scrum

## Overview
Today covers how Java works under the hood and the core concepts of the Agile/Scrum methodology.

## Topics
- Java Architecture: JDK, JRE, JVM.
- Java compilation flow: source -> compiler -> bytecode -> JVM.
- JVM Architecture.
- Agile & Scrum framework: sprints, standups, retrospectives, user stories.

## Exercises
1. Install JDK 21.
2. Review and compile `src/PlatformInfo.java` using the terminal:
   ```bash
   cd src
   javac PlatformInfo.java
   java PlatformInfo
   ```
3. Run `javap -c PlatformInfo` to check the bytecode.
4. Watch class loading with `java -verbose:class PlatformInfo`.
5. Write some user stories for a sample software project.

## Screenshots & Execution Verification
- **Java Version Verification:** [JavaVersion.png](./Screenshorts/JavaVersion.png)
- **JVM PlatformInfo Output:** [Java-Platform-Info.png](./Screenshorts/Java-Platform-Info.png)
- **Bytecode Inspection (`javap -c`):** [bytcode.png](./Screenshorts/bytcode.png)
