pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
	    stage('SCM checkout'){
			git url:'https://github.com/javahometech/myweb', branch: "${params.branch}"
		}

    }
}