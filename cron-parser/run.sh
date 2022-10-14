mvn clean install
if [ $? -eq 0 ]; then
    java -jar ./target/cron-parser-1.0-SNAPSHOT.jar "$1"
else
    echo BUILD FAILED. Fix it to run the command.
    exit -1
fi