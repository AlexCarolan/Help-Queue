pipeline {
    agent any
    
    tools {
        maven "3.6.3"
    }

    stages {
        stage('Build') {
            steps {
                sh "mvn -version"
                sh "cd /source && mvn clean compile"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
