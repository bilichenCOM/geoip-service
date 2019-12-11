#!/bin/bash

# name of file in which db schema is defined
schema_file=./schema.sql

# name of file where is script for importing from csv-file to database
import_script_file=./import-script.sql

# path to directory with csv files for import
data_dir=$(pwd)/data-csv

schema=$(cat "$schema_file")

echo "Creating db schema..."

cat "$schema_file" | sudo -u postgres psql

echo "Done!"

echo "Importing from files..."

import_script="COPY ip2location_db5 FROM '$data_dir/IP2LOCATION-LITE-DB5.CSV' WITH CSV QUOTE AS '\"'";

echo "$import_script" | sudo -u postgres psql ip2location

echo "Done!"

exit 0
