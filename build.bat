@echo off
REM Compile all Java files in the Main directory
echo Compiling Java files...
javac Main\*.java -d Main
if %ERRORLEVEL% EQU 0 (
    echo Compilation successful!
) else (
    echo Compilation failed!
    exit /b 1
)
