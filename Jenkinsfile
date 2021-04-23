pipeline {
    agent any
    
    tools {
        maven "3.6.3"
    }
    
    environment {
        dockerImage = ""
    }

    stages {
        stage('Build') {
            steps {
                script {
                    dockerImage = docker.build("alexcarolan/help-queue")
                }
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
