@echo off
:: If you already have a valid JAVA_HOME environment variable set, feel free to comment the below two lines
set JAVA_HOME=C:\Users\usama\.jdks\openjdk-20
set path=%JAVA_HOME%\bin;%path%
set path=C:\Users\usama\.m2\repository\allure\allure-2.22.0\bin;%path%
allure serve allure-results -h localhost
pause
exit