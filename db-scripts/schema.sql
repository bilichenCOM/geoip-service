CREATE DATABASE ip2location WITH ENCODING 'UTF8';
\c ip2location
CREATE TABLE ip2location_db5(
	ip_from bigint NOT NULL,
	ip_to bigint NOT NULL,
	country_code character(2) NOT NULL,
	country_name character varying(64) NOT NULL,
	region_name character varying(128) NOT NULL,
	city_name character varying(128) NOT NULL,
	latitude real NOT NULL,
	longitude real NOT NULL,
	CONSTRAINT ip2location_db5_pkey PRIMARY KEY (ip_from, ip_to)
);
