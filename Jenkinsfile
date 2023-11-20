pipeline {
    agent any
    environment{

    }
    stages {
        stage('Clone Repository') {
            steps{
                git branch: 'ITGO_BACKEND', credentailsId: 'github', url: 'https://github.com/jeonjuho23/ITGO_BACKEND.git'
        }
        stage('Set env') {
            steps{
                withCredentails([file(credentialsId: 'application', variable: 'application')]) {
                    script {
                        sh 'cp $application src/main/resources/application.yaml'
                    }
                }
            }
        }
        stage('Docker down') {
            steps {
                echo "Docker down"
                sh "docker stop itgoserver"
                echo "Docker Image delete"
                sh "docker rmi itgoserver"
            }
        }
        stage('Docker build') {
            steps {
                echo "Docker itgoserver build"
                sh "docker build -t itgoserverimg ./"
            }
            post {
                success {
                    echo "Success"
                }
                failure {
                    echo "Fail"
                }
            }
        }
        stage('Docker deploy') {
            steps {
                echo "Docker deploy"
                sh "docker run -d --rm -p 8080:8080 --name itgoserver itgoserverimg:latest"
            }
            post {
                success {
                    echo "Deploy Success"
                }
                failure {
                    echo "Deploy Fail"
                }
            }
        }
    }
}