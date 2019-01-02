rem Run from project root: ./bin/build.cmd
@echo off
cls

if not exist .\app\ mkdir .\app\

cd .\src\model
javac -d ..\..\app\ Item.java Bug.java Task.java UserStory.java UseCase.java ItemStore.java

cd ..\providers
javac -d ..\..\app\ InputProvider.java ConsoleInputProvider.java

cd ..\
javac -d ..\app\ --class-path ..\app\ IssueTrackerInputProvider.java IssueTracker.java
