pipelineJob("CI-Job") {
	definition {
		cpsScm {
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

pipelineJob("CD_job") {
	parameters {
		gitParameterDefinition {
			name('IMAGE_TAG')
			branch('master')
	    defaultValue('latest')
	    listSize('0')
	    selectedValue('DEFAULT')
	    sortMode('DESCENDING_SMART')
	    type('PT_TAG')
			description('')
			tagFilter('*')
			useRepository(gitUrl)
			quickFilterEnabled(false)
	    }
	}

	triggers {
		upstream('CI_job', 'SUCCESS')
	}

	definition {
		cpsScm {
			scm {
				git {
					remote {
						url("https://github.com/Kontafer/students-project-2018.git")
						credentials('jenkins-github')
					}
					branch('master')
				}
			}
			scriptPath('Jenkins/CD_job.groovy')
		}
	}
}
