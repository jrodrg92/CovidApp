pipeline {
    agent any
    tools { 
        maven 'maven-3.8.1' 
        jdk 'jdk11' 
    }
    stages {
        stage ('Build') {
            steps {
                sh 'mvn --help'
            }
        }
    }
}