pipeline {
    agent any
    
    tools {
        maven "3.6.3"
    }
    
    environment {
        imagename = "/source/Dockerfile"
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
                    dockerImage = docker.build imagename
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
