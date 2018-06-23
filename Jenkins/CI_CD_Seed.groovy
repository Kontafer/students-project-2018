pipelineJon("CI-Job") {
	definition {
		spsScm {
			scm {
				git {
					remote {
						url("https://github.com/Kontafer/students-project-2018.git")
						credentials("jenkins-github")
					}
					branch("master")
				}
			}
			scriptPath("Jenkins/CI_job.groovy")
		}
	}
}
