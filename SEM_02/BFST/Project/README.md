# BFST22Group10
Welcome to X-Plore! 
To run the executable jar file, please open the one that matches your operating system:

MacOS: X-Plore_macOS.jar  
Windows: X-Plore_windows.jar

Requirements:
- Java 17
- 4gb RAM available for the jar

Be aware that the loading time might be up to a minute. The warning about unsupported JavaFX configuration can be disregarded.  
To load faster, you can add the argument -Xmx6g when opening the jar to increase the maximum heap size:

    java -jar -Xmx6g X-Plore_macOS.jar


If the above fails, you can open the app using the source code provided. Open a command line and navigate to the source code directory. Then run the command:

    ./gradlew run