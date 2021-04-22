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
                script {
                    dockerImage = docker build . -t help-queue -f source/dockerfile
                  }
             }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
