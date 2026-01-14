# csd-402
Coursework for the class "CSD-402: Java for Programmers"

# How to run

## Straight from Gradle Wrapper

The most reliable way to run these exercises is to do a gradle build and run.

Please note that command line arguments are required to select the assignment, so change "module1" to "module2" etc. as needed.

```
gradlew clean build run --args="module1"
```

Note: Whatever your system gradle is, it's probably not appropriate for this project. *Always* run gradle projects from the gradle wrapper (gradlew).

## Manual Run

Alternatively, one could just do `gradlew clean build`, navigate to the `build/libs/` directory, and run

```
java -jar csd402-1.0.0-all.jar module1
```

Note: The non-shadowed `csd402-1.0.0.jar` does not contain all dependencies required to run the assignments. You MUST run the shadowed "all" jar if using this method.

## Importing to IDE

Many Java IDEs, including IntelliJ IDEA, recognize gradle projects as first-class citizens. You may have success doing things this way, but instructions will vary according to your preferred IDE, and this method is not officially supported. Just remember you must configure your IDE to pass in command-line arguments to select the desired assignment.
