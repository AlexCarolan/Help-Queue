pipeline {
    agent any
    
    tools {
        maven "3.6.3"
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
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
