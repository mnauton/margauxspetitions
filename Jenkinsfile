pipeline {
    agent any

    parameters {
        string(name: 'USERNAME', defaultValue: '', description: 'Username for deployment')
    }

    stages {
        stage('GetProject') {
            steps {
                git branch: 'main', url: 'https://github.com/mnauton/margauxspetitions.git'
            }
        }
        stage('Build') {
            steps {
                sh "mvn clean"
                sh "mvn dependency:copy-dependencies"
                sh "mvn compile"
            }
        }
        stage('Package') {
            steps {
                sh "mvn package"
            }
        }
        stage('Archive') {
            steps {
                archiveArtifacts allowEmptyArchive: true,
                    artifacts: '**/margauxspetitions.war'
            }
        }
        stage('Deploy') {
            steps {
                script {
                    // Prompt for user approval before deployment
                    input message: 'Do you want to proceed with deployment?', ok: 'Deploy'
                    echo 'User approved deployment. Proceeding...'

                    // Deployment steps
                    sh 'docker build -f Dockerfile -t myapp .'
                    sh 'docker rm -f myapp || true'
                    sh 'docker run --name myapp -p 9090:8080 --detach myapp:latest'
                }
            }
        }
    }
}
