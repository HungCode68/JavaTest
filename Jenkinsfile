pipeline {
    agent any
    
    environment {
        CATALINA_HOME = 'C:\\apache-tomcat-9.0.85'
    }

    stages {
        stage('Clone') {
            steps {
                echo 'Cloning source code from GitHub'
                git branch: 'main', url: 'https://github.com/HungCode68/JavaTest.git'
            }
        }

        stage('Build WAR') {
            steps {
                echo 'Compiling and packaging WAR file'
                bat '''
                    mkdir build
                    javac -d build -cp "%CATALINA_HOME%\\lib\\servlet-api.jar" -sourcepath src src\\**\\*.java
                    xcopy Web\\* build /E /I /Y
                    cd build
                    jar -cvf UniversitySystem.war *
                '''
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                echo 'Deploying WAR file to Tomcat (IIS will reverse proxy)'
                bat '''
                    copy build\\UniversitySystem.war "%CATALINA_HOME%\\webapps\\" /Y
                '''
            }
        }

        stage('Restart Tomcat') {
            steps {
                echo 'Restarting Tomcat server'
                bat '''
                    call "%CATALINA_HOME%\\bin\\shutdown.bat"
                    timeout /t 5
                    call "%CATALINA_HOME%\\bin\\startup.bat"
                '''
            }
        }
    }
}
