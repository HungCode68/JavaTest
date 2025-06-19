pipeline {
    agent any
    
    stages {
        stage('Clone') {
            steps {
                echo 'Cloning source code'
                git branch:'main', url: 'https://github.com/HungCode68/JavaTest.git'
            }
        }
        
        stage('Restore Package') {
            steps {
                echo 'Restore packages'
                bat 'dotnet restore'
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building project'
                bat 'dotnet build --configuration Release'
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running tests'
                bat 'dotnet test --no-build --verbosity normal'
            }
        }
        
        stage('Publish') {
            steps {
                echo 'Publishing application'
                bat 'dotnet publish -c Release -o ./publish'
            }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deploying to folder'
                bat 'xcopy "%WORKSPACE%\\publish" /E /Y /I /R "D:\\wwwroot\\mytest"'
            }
        }
        
        stage('Deploy to IIS') {
            steps {
                powershell '''
                    Import-Module WebAdministration
                    if (-not (Get-Website -Name "MySite" -ErrorAction SilentlyContinue)) {
                        New-Website -Name "MyTest" -Port 81 -PhysicalPath "D:\\wwwroot\\mytest"
                        Write-Host "Website MyTest created successfully"
                    } else {
                        Write-Host "Website MyTest already exists"
                    }
                '''
            }
        }
    }
}