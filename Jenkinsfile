pipeline {
    agent any
    stages {
        stage('Clone Repository') {
            steps{
                git branch: 'master', credentialsId: 'github', url: 'https://github.com/jeonjuho23/ITGO_BACKEND.git'
            }
            post {
                success {
                    echo "clone success"
                }
                failure {
                    echo "clone failure"
                }
            }
        }
        stage('Set env') {
            steps{
                withCredentials([file(credentialsId: 'application', variable: 'application')]) {
                    script {
                        sh 'cp $application ITGO_BACKEND/src/main/resources/application.yaml'
                    }
                }
            }
            post {
                success {
                    echo "set env success"
                }
                failure {
                    echo "set env failure"
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
            post {
                success {
                    echo "docker down success"
                }
                failure {
                    echo "docker down failure"
                }
            }
        }
        stage('Docker build') {
            steps {
                echo "Docker itgoserver build"
                sh "docker build -t itgoserverimg ./"
            }
            post {
                success {
                    echo "docker build Success"
                }
                failure {
                    echo "docker bild Fail"
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
                    echo "docker Deploy Success"
                }
                failure {
                    echo "docker Deploy Fail"
                }
            }
        }
    }
}