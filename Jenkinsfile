node {
  stage('SCM') {
    checkout scm
  }
  stage('build') {
      node {
	    	def mvn = tool 'maven';
		    withSonarQubeEnv() {
		      sh "${mvn}/bin/mvn clean install -X"
		    }
      }
  }
  stage('Deploy') {
      echo 'Deploying....'
          
  }
}