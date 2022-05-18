#!groovy
pipeline {

    agent any

    environment {
        // 应用名称
        APP_NAME = 'tsingeye-admin'
        // 应用部署路径
        APP_DEPLOY_BASE_DIR = '/var/jenkins_home/workspace/tsingeye-viid/'
        // target地址
        SOURCE_PATH = '/var/jenkins_home/workspace/tsingeye-viid/tsingeye-admin/target'
    }

    stages {

        stage('构建') {
            steps {

                sh 'if [ ! -d "' + "${env.HOME}" + '/resources" ];then\n' +
                        '  echo "配置文件不存在无需修改"\n' +
                        'else\n' +
                        '  cp  -rf  ' + "${env.HOME}" + '/resources/*.yaml ' + "${env.APP_NAME}" + '/src/main/resources\n' +
                        '  echo "配置文件替换"\n' +
                        'fi'

                sh 'mvn clean package -Dmaven.test.skip=true'
            }
        }

        stage('部署') {
            steps {
                sh 'cp -f ' + ' bin/deploy.sh ' + "${env.APP_DEPLOY_BASE_DIR}" + "${env.APP_NAME}"
                archiveArtifacts "${env.APP_NAME}" + '/target/*.jar'
                sh 'chmod +x ' + "${env.APP_DEPLOY_BASE_DIR}" + "${env.APP_NAME}" + '/deploy.sh'
                sh 'bash ' + "${env.APP_DEPLOY_BASE_DIR}" + "${env.APP_NAME}" + '/deploy.sh'
            }
        }
    }
}
