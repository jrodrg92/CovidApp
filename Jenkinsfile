node {
  stage('SCM') {
    checkout scm
  }
  stage('build & SonarQube analysis') {
      node {
	    	def mvn = tool 'maven';
		    withSonarQubeEnv() {
		      sh "${mvn}/bin/mvn clean package sonar:sonar -Dsonar.projectKey=pruebagit"
		    }
      }
  }
  stage('Quality Gate'){
      timeout(time: 1, unit: 'HOURS') {
          def qg = waitForQualityGate()
          if (qg.status != 'OK') {
              error "Pipeline aborted due to quality gate failure: ${qg.status}"
          }
      }
  }
  stage('Deploy') {
      echo 'Deploying....'
          
  }
}