pipeline {
    agent any // Specifies that any available agent can be used to run the pipeline

    stages {
        stage('Clone') {
            steps {
                echo 'Cloning source code'
                // This step remains the same as 'git' is universal.
                // Ensure your actual Java web project repository is used here.
                git branch:'main', url: 'https://github.com/HungCode68/JavaTest.git'
            }
        }

        stage('Restore Package') {
            steps {
                echo 'Restoring Maven dependencies'
                // In Java projects using Maven, 'mvn dependency:resolve' explicitly downloads
                // all project dependencies. Other commands like 'mvn compile' or 'mvn package'
                // would also implicitly resolve dependencies if not already present.
                bat 'mvn dependency:resolve'
            }
        }

        stage('Build') {
            steps {
                echo 'Building Java project with Maven'
                // 'mvn compile' compiles the source code of your Java project.
                bat 'mvn compile'
            }
        }

        stage('Test') {
            steps {
                echo 'Running unit tests'
                // 'mvn test' executes the unit tests defined in your project.
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging Java web application into WAR file'
                // 'mvn package' builds the project and creates the distributable archive.
                // For a Java web application, this typically results in a '.war' (Web Application Archive)
                // file located in the 'target/' directory.
                bat 'mvn package'
            }
        }

        stage('Deploy to Staging Folder') {
            steps {
                echo 'Copying WAR file to a staging deployment folder'
                // This step is analogous to your .NET 'dotnet publish -o ./publish'.
                // It copies the generated WAR file from the 'target' directory to a local staging folder.
                script {
                    // Find the generated WAR file. Maven typically names it after artifactId-version.war
                    def warFile = findFiles(glob: 'target/*.war')
                    if (warFile.length > 0) {
                        def warFilePath = warFile[0].path
                        echo "Found WAR file: ${warFilePath}"

                        // Define your staging directory. Adjust the path as needed.
                        def stagingDir = "D:\\wwwroot\\mytest"
                        bat "if not exist \"${stagingDir}\" mkdir \"${stagingDir}\"" // Create staging directory if it doesn't exist
                        bat "copy \"${warFilePath}\" \"${stagingDir}\\\" /Y" // Copy the WAR file
                        echo "WAR file copied to ${stagingDir}"
                    } else {
                        error 'No WAR file found in target directory after packaging! Ensure your project builds a .war file.'
                    }
                }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                echo 'Deploying application to Tomcat webapps directory'
                // This stage replaces the 'Deploy to IIS' part. For Java web applications,
                // you typically deploy by copying the WAR file to the 'webapps' directory of an Apache Tomcat server.
                script {
                    def warFileInStaging = findFiles(glob: 'D:\\wwwroot\\mytest\\*.war')
                    if (warFileInStaging.length > 0) {
                        def warFileName = warFileInStaging[0].name
                        echo "Deploying ${warFileName} to Tomcat..."

                        // IMPORTANT: Adjust this path to your actual Tomcat 'webapps' directory.
                        // Example paths:
                        // "C:\\Program Files\\Apache Software Foundation\\Tomcat\\webapps"
                        // "D:\\Apache Software Foundation\\Tomcat\\webapps"
                        def tomcatWebappsPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat\\webapps"

                        // Ensure a clean deployment by deleting any existing WAR file and its unpacked directory
                        // Tomcat usually auto-deploys WAR files placed here. Deleting ensures the latest version is used.
                        bat "del /Q \"${tomcatWebappsPath}\\\${warFileName}\"" // Delete existing WAR file
                        bat "rmdir /S /Q \"${tomcatWebappsPath}\\\${warFileName.replace('.war','')}\"" // Delete unpacked directory
                        echo "Cleaned up previous deployment of ${warFileName}."

                        // Copy the WAR file from the staging folder to Tomcat's webapps directory
                        bat "copy \"${warFileInStaging[0].path}\" \"${tomcatWebappsPath}\\\" /Y"
                        echo "WAR file deployed to Tomcat: ${tomcatWebappsPath}\\${warFileName}"

                        // Optional: If Tomcat does not auto-deploy or requires a restart for changes to take effect,
                        // you might need to add commands here to restart the Tomcat service.
                        // Example (if Tomcat is registered as a Windows service named 'Tomcat9'):
                        // bat 'net stop Tomcat9'
                        // bat 'net start Tomcat9'
                        // This requires Jenkins agent to have permissions to manage services.
                    } else {
                        error 'No WAR file found in staging directory to deploy to Tomcat!'
                    }
                }
            }
        }
    }
}