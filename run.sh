#!/bin/bash

./db-scripts/prepare-db.sh
mvn clean install
java -jar ./geoip-server/target/geoip-server-0.0.1-SNAPSHOT.jar

exit 0
