pipeline {
    agent any
    
    tools {
        maven "3.6.3"
    }
    
    environment {
        imagename = "/source/dockerfile"
    }

    stages {
        stage('Build') {
            steps {
                sh "mvn -version"
                sh "cd source && mvn -B -DskipTests clean package"
            }
        }
        stage('Test') {
            steps {
                sh "cd source && mvn test"
            }
        }
        stage('Building image') {
              steps{
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
