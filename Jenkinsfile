pipeline {
    agent any
    tools {
        maven 'maven 3.9.6'
        jdk 'Java 1.11'
        dockerTool 'docker'
    }
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage("build & SonarQube analysis") {
            agent any
            steps {
                sh 'mvn clean install -X sonar:sonar \
                                -Dsonar.projectKey=covid \
                                -Dsonar.host.url=http://192.168.1.143:9000 \
                                -Dsonar.login=sqa_b52bc1b3c1aae81af93b19b146841df6d354538e \
                                -Dsonar.sources=src/main/java/ \
                                -Dsonar.java.binaries=target/classes'
            }
        }
        stage('Docker Build') {
        	agent any
            steps {
             	sh 'docker build -t shanem/spring-petclinic:latest .'
            }
        }
    }
}
