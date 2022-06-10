pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11' 
            args '-v /root/.m2:/root/.m2' 
        }
    }
    stages {
	    stage('SCM checkout'){
			echo"pulling changes from the branch ${params.branch}"
			git url:'https://github.com/javahometech/myweb', branch: "${params.branch}"
		}

    }
}