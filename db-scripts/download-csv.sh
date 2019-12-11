#!/bin/bash

# name for archived ip database downloaded from ip2location site.
# this archive will be removed after unpacking
arch_filename=db_archived.zip

# folder in which files from archive will be placed
out_folder=./data-csv

# type of database to download
database_code=DB5LITE

download_token=$(cat ./download_token)

if [ -z $download_token ]; then
	echo "error! please provide your Download token in 'download_token' file"
	exit 1
fi

download_url="https://www.ip2location.com/download/?token=$download_token&file=$database_code"

echo "Downloading database files..."

wget "$download_url" -O "$arch_filename"

unzip "$arch_filename" -d "$out_folder"

rm "$arch_filename"

exit 0
