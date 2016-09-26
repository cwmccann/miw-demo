#!/usr/bin/env groovy

node {
    stage 'Checkout and Build'

    sh "java -version"
    checkout scm

    sh "./gradlew clean"
    sh "./gradlew assemble"
    sh "./gradlew test"

    stage 'Publishing Snapshot'
    sh "./gradlew publish"
}

input 'Create Release?'

node {
    stage 'Create Release'
    sh "./gradlew release -Prelease.useAutomaticVersion=true"
}