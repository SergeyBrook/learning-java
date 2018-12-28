
rem Run from project root: ./bin/build.cmd
@echo off
cls

if not exist .\app\ mkdir .\app\
cd .\src

javac -d ..\app\ Item.java Bug.java Task.java UserStory.java UseCase.java Data.java App.java
