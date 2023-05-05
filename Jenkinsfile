pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "maven_3_6_3"
    }

    stages {
        stage('Test Y Creacion De Jar') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/jmateo95/InterPackage-Tracking']])
                sh 'ssh root@167.172.108.85 "cd /home/Interpackage/InterPackage-Tracking && git pull origin main && mvn clean install -DskipTests"'
            }
        }

        stage('Detener El Servicio Docker') {
            steps {
                sh 'ssh root@167.172.108.85 "docker stop docker-interpackage-service-tracking-1"'
                sh 'ssh root@167.172.108.85 "docker rm docker-interpackage-service-tracking-1"'
            }
        }

        stage ('Desplegar Imgen de Docker'){
            steps{
                script{
                    sh 'ssh root@167.172.108.85 "cd /home/Interpackage/docker && docker-compose up -d --build interpackage-service-tracking"'
                }
            }  
        }
    }
}