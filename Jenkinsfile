pipeline {
    agent any
    
    tools {
        maven "3.6.3"
    }
    
    environment {
        imagename = ""
    }

    stages {
        stage('Build') {
            steps {
                app = docker.build("alexcarolan/help-queue")
            }
        }
        stage('Test') {
            steps {
                echo 'Deploying....'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
