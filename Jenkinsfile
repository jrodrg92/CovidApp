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
    }
}
