REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=jobs4u.candidateApp\target\jobs4u.candidateApp-0.1.0.jar;jobs4u.candidateApp\target\dependency\*;

REM call the java VM, e.g,
java -cp %BASE_CP% jobs4u.candidateApp.Lapr4.Main
