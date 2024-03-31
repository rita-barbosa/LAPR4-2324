REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=jobs4u.customerApp\target\jobs4u.customerApp-0.1.0.jar;jobs4u.customerApp\target\dependency\*;

REM call the java VM, e.g,
java -cp %BASE_CP% jobs4u.customerApp.Lapr4.Main
