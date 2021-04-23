pipeline {
    agent any
    
    tools {
        maven "3.6.3"
    }
    
    environment {
        dockerImage = ""
        registryCredential = "alexcarolan-dockerhub"
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
                script {
                    docker.withRegistry( '', registryCredential ) {
											dockerImage.push("$BUILD_NUMBER")
											dockerImage.push('latest')
										}
								}
					  } 
				}
    }
}
