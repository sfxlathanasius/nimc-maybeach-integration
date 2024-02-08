pipeline {
    agent { label 'master' }
    environment {
        //variable for image name
        PROJECT = "maybeach-integration"
        APP = "maybeach-integration"
    }
    tools {
        maven 'mvn339'
        jdk 'jdk17'
    }
    stages {
        stage ('Initialize') {
            steps {
                deleteDir()
                googlechatnotification (url: 'https://chat.googleapis.com/v1/spaces/AAAAim8J8lI/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=V-pTtcSuqaUAeEgfM3NMwDuAkYjcQGDB3MSTC6Ks9og%3D', message: "Initializing build process for *${env.JOB_NAME}* , CURRENT_BRANCH: *${env.GIT_BRANCH}*")
            }   
        }
        stage ('Clean WorkSpace') {
            steps {
                checkout scm
            }  
        }
        
        stage ('Build') {
            steps {
                script {
                            env.OLD_VERSION = sh(returnStdout: true, script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout')
                            env.VERSION = env.OLD_VERSION + '-' + env.BUILD_NUMBER
                        }
                        sh 'mvn clean package'
                echo 'Running build automation'
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                googlechatnotification (url: 'https://chat.googleapis.com/v1/spaces/AAAAim8J8lI/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=V-pTtcSuqaUAeEgfM3NMwDuAkYjcQGDB3MSTC6Ks9og%3D', message: "Build process for *${env.JOB_NAME}* , CURRENT_BRANCH: *${env.GIT_BRANCH}*")
                  }
               }

        stage('SonarCloud analysis') {
                tools {
                    jdk 'jdk11' 
                }
                
                steps {
                    withSonarQubeEnv('SonarCloud') {
                        sh 'mvn sonar:sonar ' + 
                        '-Dproject.settings=./sonar-project.properties'
                        }
                    googlechatnotification (url: 'https://chat.googleapis.com/v1/spaces/AAAAim8J8lI/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=V-pTtcSuqaUAeEgfM3NMwDuAkYjcQGDB3MSTC6Ks9og%3D', message: "SonarCloud Code analysis process for *${env.JOB_NAME}* , CURRENT_BRANCH: *${env.GIT_BRANCH}*")
                }
        }

        stage("deploy") {
                steps {
                    build job: 'nimc-deploy-spring',
                        parameters: [
                            string(name: 'ENVIRONMENT', value: 'production'),
							string(name: 'JENKINS_JOB', value: 'nimc-maybeach-integration'),
                            string(name: 'INSTANCE', value: 'maybeach'),
                            string(name: 'CURRENT_COMPONENT', value: 'maybeach-integration'),
                            string(name: 'PROJECT', value: 'maybeach-integration'),
                            string(name: 'SERVICE', value: 'maybeach')
                        ]
                googlechatnotification (url: 'https://chat.googleapis.com/v1/spaces/AAAAim8J8lI/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=V-pTtcSuqaUAeEgfM3NMwDuAkYjcQGDB3MSTC6Ks9og%3D', message: "Deploy stage for *${env.JOB_NAME}* , CURRENT_BRANCH: *${env.GIT_BRANCH}*")
            }
        }    
    }
}