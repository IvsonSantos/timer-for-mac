Walkthrough - Java Version Fix
I have resolved the UnsupportedClassVersionError by downgrading the project's Java target version to match the environment and implemented a workaround for the JavaFX runtime issue.

Changes Made
Backend
pom.xml
Downgraded <java.version> from 23 to 21 to match the local Java runtime (Java 21).
Main.java
 [NEW]
Created a wrapper 
Main
 class. This is a common workaround for JavaFX applications when run from the classpath (like via Maven or a shadow jar), as it bypasses the JavaFX runtime check that occurs when the main class extends javafx.application.Application.
Git Configuration
.gitignore
 [NEW]
Created a root 
.gitignore
 that properly excludes:
backend/target/ (Java/Maven build output)
frontend/node_modules/ (Node dependencies)
frontend/dist/ (Angular build output)
.angular/ cache directory
IDE and OS-specific files (.DS_Store, etc.)
I also cleaned up the git stage by unstaging all files and restaging them to respect the new rules. The compiled assets are no longer tracked.

Verification Results
Success Highlights
Compilation: The project now compiles successfully using Java 21.
Runtime: The application starts without the version error and correctly initializes the Spring Boot context and JavaFX launcher.
Execution Log
text
2026-02-22T12:09:39.227+01:00  INFO 10202 --- [           main] com.example.timer.Main                   : Starting Main using Java 21.0.6...
...
2026-02-22T12:09:40.812+01:00  INFO 10202 --- [JavaFX-Launcher] o.s.boot.SpringApplication               : Started application in 1.812 seconds

Comment
⌥⌘M
