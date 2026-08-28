@echo off
REM Compile all Java files in the source directory
echo Compiling Java files...
javac src\Main\*.java -d src\Main
if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
) else (
    echo Compilation failed!
    exit /b 1
)
