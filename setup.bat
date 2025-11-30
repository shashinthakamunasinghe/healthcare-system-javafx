@echo off
echo Healthcare System Setup
echo.

REM Create necessary directories
if not exist "bin" mkdir bin
if not exist "data" mkdir data

REM Create empty CSV files if they don't exist
if not exist "data\doctors.csv" (
    echo Creating doctors.csv...
    echo ID,Name,Specialization,Phone,Email > data\doctors.csv
)

if not exist "data\patients.csv" (
    echo Creating patients.csv...
    echo ID,Name,Age,Phone,Email,Address > data\patients.csv
)

if not exist "data\appointments.csv" (
    echo Creating appointments.csv...
    echo ID,PatientID,DoctorID,Date,Time,Status > data\appointments.csv
)

echo Setup complete!
echo You can now run the application using run.bat
pause