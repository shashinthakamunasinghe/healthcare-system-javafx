@echo off
echo Starting Healthcare System...
echo.

REM Set JavaFX path (adjust this path to your JavaFX installation)
set JAVAFX_PATH=E:\Downloads\openjfx-24.0.2_windows-x64_bin-sdk\javafx-sdk-24.0.2\lib

REM Set project paths
set PROJECT_DIR=%~dp0
set SRC_DIR=%PROJECT_DIR%src
set BIN_DIR=%PROJECT_DIR%bin
set RESOURCES_DIR=%PROJECT_DIR%resources

REM Create bin directory if it doesn't exist
if not exist "%BIN_DIR%" mkdir "%BIN_DIR%"

echo Compiling Java files...

REM Start with essential files that should exist
set JAVA_FILES=

REM Check and add application files
if exist "%SRC_DIR%\application\Main.java" (
    set JAVA_FILES=%JAVA_FILES% "%SRC_DIR%\application\Main.java"
    echo Found: Main.java
)
if exist "%SRC_DIR%\application\SceneController.java" (
    set JAVA_FILES=%JAVA_FILES% "%SRC_DIR%\application\SceneController.java"
    echo Found: SceneController.java
)

REM Check and add controller files
if exist "%SRC_DIR%\controllers\MainMenuController.java" (
    set JAVA_FILES=%JAVA_FILES% "%SRC_DIR%\controllers\MainMenuController.java"
    echo Found: MainMenuController.java
)
if exist "%SRC_DIR%\controllers\AdminDashboardController.java" (
    set JAVA_FILES=%JAVA_FILES% "%SRC_DIR%\controllers\AdminDashboardController.java"
    echo Found: AdminDashboardController.java
)
if exist "%SRC_DIR%\controllers\PatientDashboardController.java" (
    set JAVA_FILES=%JAVA_FILES% "%SRC_DIR%\controllers\PatientDashboardController.java"
    echo Found: PatientDashboardController.java
)

REM Check and add model files
if exist "%SRC_DIR%\models\Doctor.java" (
    set JAVA_FILES=%JAVA_FILES% "%SRC_DIR%\models\Doctor.java"
    echo Found: Doctor.java
)
if exist "%SRC_DIR%\models\Patient.java" (
    set JAVA_FILES=%JAVA_FILES% "%SRC_DIR%\models\Patient.java"
    echo Found: Patient.java
)
if exist "%SRC_DIR%\models\Appointment.java" (
    set JAVA_FILES=%JAVA_FILES% "%SRC_DIR%\models\Appointment.java"
    echo Found: Appointment.java
)

REM Check and add utility files
if exist "%SRC_DIR%\utils\CSVHelper.java" (
    set JAVA_FILES=%JAVA_FILES% "%SRC_DIR%\utils\CSVHelper.java"
    echo Found: CSVHelper.java
)

echo.
echo Compiling found files...

REM Compile only the files that exist
javac --module-path "%JAVAFX_PATH%" --add-modules javafx.controls,javafx.fxml ^
      -cp "%BIN_DIR%" ^
      -d "%BIN_DIR%" ^
      %JAVA_FILES%

if %ERRORLEVEL% neq 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!
echo Running Healthcare System...
echo.

REM Run the application
java --module-path "%JAVAFX_PATH%" --add-modules javafx.controls,javafx.fxml ^
     -cp "%BIN_DIR%;%RESOURCES_DIR%" ^
     application.Main

if %ERRORLEVEL% neq 0 (
    echo Application failed to start!
) else (
    echo Application closed successfully.
)

pause