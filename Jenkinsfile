pipeline {
    agent any
    
    tools {
        maven "3.6.3"
    }
    
    environment {
        dockerImage = ""
        registryCredential = "dockerhub_id"
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
                sh "cd source && mvn test"
            }
        }
        stage('Upload Image') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
			dockerImage.push("$BUILD_NUMBER")
			dockerImage.push('latest')
		    }
	        }
	    } 
        }
        stage('Deploy Image') {
            steps {
                echo "deploy"
		container('kubectl') {
		    sh "kubectl get pods"
	        }
            }
        }
    }
}
