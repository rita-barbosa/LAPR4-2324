#!/usr/bin/env bash

# set the class path,
# assumes the build was executed with maven copy-dependencies
export BASE_CP=jobs4u.candidateApp/target/jobs4u.candidateApp-0.1.0.jar:jobs4u.candidateApp/target/dependency/*;

# call the java VM, e.g,
java -cp $BASE_CP jobs4u.candidateApp.Lapr4.Main