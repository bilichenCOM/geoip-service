# geoip-service
## run
This application requires you to have Java 8, Maven, PostgreSQL installed on your machine.</br>
To import data from ip2location web-site you need first of all to have PostgreSQL server running</br>
First of all go to project-root and then to db-scripts/ and provide your download token in ```download_token``` file.</br>
Then you need to run:</br>
```sudo chmod +x *.sh```</br>
```./prepare-db.sh```</br>
Last script should start downloading ip2location archived db, unpacking it and importing to PostgreSQL database.</br>
After importing you need to add new role to PostgreSQL:</br>
```sudo -u postgres psql```</br>
```create role geoip with superuser login encrypted password 'geoip';```
To run application you have to go to root of project and execute following:</br>
```sudo chmod +x run.sh```</br>
```./run.sh```</br>
