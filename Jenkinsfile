#!/usr/bin/env groovy

node {
    stage 'Checkout and Build'

    sh "java -version"
    checkout scm

    sh "./gradlew clean"
    sh "./gradlew assemble"
    sh "./gradlew test"

    stage 'Publishing Snapshot'
    //sh "./gradlew publish"
    archiveArtifacts artifacts: 'build/libs/*.jar', excludes: null, fingerprint: true
}

input 'Create Release?'

stage 'Create Release', concurrency: 1
node {
    sh "./gradlew clean release -Prelease.useAutomaticVersion=true"
    archiveArtifacts artifacts: 'build/libs/*.jar', excludes: null, fingerprint: true
}