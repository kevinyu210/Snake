# Snake

This is my attempt at recreating the classic snake game in Java. The purpose of creating this game was to practice using IntelliJ.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine. I personally use Windows Subsystem for Linux (WSL) on Windows 10 but this should work for macOS as well.

### Ingredients

* JDK - I have jdk-8u181 installed
* [Xming](http://pjdecarlo.com/2016/06/xming-bash-on-ubuntu-on-windows-x11-window-system-running-from-windows-10-subsystem-for-linux.html) - Run Graphical Linux applications (For Windows users)

### Linux

First clone the repo by running this on your command line:
```
git clone https://github.com/kevinyu210/snake
```
Then `cd snake/Drawing` and make PLAY executable by running:
```
chmod +x PLAY
```
Finally, run:
```
./PLAY
```
### Windows (CMD)

First download the files from https://github.com/kevinyu210/snake
Then cd into the snake/Drawing directory and run these commands:
```
javac -d out/production/snake -sourcepath src src/com/company/Main.java
```
```
java -classpath out/production/snake com.company.Main
```

## Tools used:
* [IntelliJ](https://www.jetbrains.com/idea/) - Java IDE