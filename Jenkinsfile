node {
    stage 'check tools'
    sh "java -version"
    
    stage 'checkout source code'
    checkout scm

    stage 'clean'
    sh "./gradlew clean"

    stage 'backend tests'
    sh "./gradlew test"

    stage 'packaging'
    //sh "./gradlew release -Prelease.useAutomaticVersion=true"
    sh "./gradlew bootRepackage
    archiveArtifacts artifacts: 'build/libs/*.jar', excludes: null, fingerprint: true
}