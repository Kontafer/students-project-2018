pipelineJob("CI_Job") {
	triggers {
		scm('H/5 * * * *')
	}
	definition {
		cpsScm {
			scm {
				git {
					remote {
						url("https://github.com/Kontafer/students-project-2018.git")
						credentials("jenkins-github")
					}
					branch("refs/tags/*")
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
			branch('refs/tags/*')
			branchFilter('.*')
	    defaultValue('latest')
	    listSize('0')
	    selectedValue('DEFAULT')
	    sortMode('DESCENDING_SMART')
	    type('PT_TAG')
			description('')
			tagFilter('*')
			useRepository("https://github.com/Kontafer/students-project-2018.git")
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
					branch('refs/tags/*')
				}
			}
			scriptPath('Jenkins/CD_job.groovy')
		}
	}
}
