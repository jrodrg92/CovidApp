pipeline {
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
              withSonarQubeEnv('sonarscanner') {
                sh 'mvn clean package sonar:sonar'
              }
            }
        }
        stage("Quality Gate") {
	        steps {
	          timeout(time: 1, unit: 'HOURS') {
	            waitForQualityGate abortPipeline: true
	          }
	        }
        }
        stage('Deliver') {
            steps {
                sh './jenkins/scripts/deliver.sh'
            }
        }
    }
}