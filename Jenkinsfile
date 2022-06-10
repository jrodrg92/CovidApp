node {
  stage('SCM') {
    checkout scm
  }
  stage('Build') { 
    steps {
        sh 'mvn -B -DskipTests clean package' 
    }
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=pruebagit"
    }
  }
  stage("Quality Gate"){
      timeout(time: 1, unit: 'HOURS') {
          def qg = waitForQualityGate()
          if (qg.status != 'OK') {
              error "Pipeline aborted due to quality gate failure: ${qg.status}"
          }
      }
  }
}
