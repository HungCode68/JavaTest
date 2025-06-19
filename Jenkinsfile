pipeline {
    agent any
    
    stages {
        stage('Clone') {
            steps {
                git branch: 'main', url: 'https://github.com/HungCode68/JavaTest.git'
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building the application...'
                // Thêm các lệnh build của bạn ở đây
            }
        }
        
        stage('Test') {
            steps {
                echo 'Running tests...'
                // Thêm các lệnh test của bạn ở đây
            }
        }
        
        stage('Deploy') {
            steps {
                echo 'Deploying application...'
                // Thêm các lệnh deploy của bạn ở đây
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
