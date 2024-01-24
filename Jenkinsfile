pipeline {
    agent any
    tools {
        maven 'maven 3.9.6'
        jdk 'Java 1.11'
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
                sh 'mvn sonar:sonar \
                                -Dsonar.projectKey=MyFirstTest \
                                -Dsonar.host.url=http://localhost:9000 \
                                -Dsonar.login=sqa_b52bc1b3c1aae81af93b19b146841df6d354538e'
            }
          }
    }
}
