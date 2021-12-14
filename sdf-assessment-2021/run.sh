#!/usr/bin/env bash
mvn clean package
java -cp ./target/http-server-1.0-SNAPSHOT.jar mywebserver.Main --port 3000  --docRoot ./target:/opt/tmp/www
