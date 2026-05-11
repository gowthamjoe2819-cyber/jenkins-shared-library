def call() {

    pipeline {

        agent any

        stages {

            stage('Build') {
                steps {
                    echo 'Building Application...'

                    sh '''
                    mkdir -p build
                    echo "Build completed"
                    '''
                }
            }

            stage('Test') {
                steps {
                    echo 'Running Tests...'

                    sh '''
                    echo "Tests passed"
                    '''
                }
            }

            stage('Docker Build') {
                steps {
                    echo 'Building Docker Image...'

                    sh '''
                    docker build -t sample-app .
                    '''
                }
            }

            stage('Deploy') {
                steps {
                    echo 'Deploying Application...'

                    sh '''
                    docker rm -f sample-container || true

                    docker run -d \
                    --name sample-container \
                    -p 80:80 \
                    sample-app
                    '''
                }
            }
        }
    }
}
