pipeline {
	stages {
	  stage('SCM') {
	  	steps {
	    	checkout scm
	    }
	  }
	  stage('Build') { 
	    steps {
	        sh 'mvn -B -DskipTests clean package' 
	    }
	  }
	  stage('SonarQube Analysis') {
	    steps {
	    	def mvn = tool 'maven';
		    withSonarQubeEnv() {
		      sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=pruebagit"
		    }
	    }
	  }
	}
}