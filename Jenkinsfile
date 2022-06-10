node {
  stage('SCM') {
    checkout scm
  }
  stage('build & SonarQube analysis') {
      node {
          withSonarQubeEnv('sonarscanner') {
             sh 'mvn clean package sonar:sonar'
          
      }
  }
}