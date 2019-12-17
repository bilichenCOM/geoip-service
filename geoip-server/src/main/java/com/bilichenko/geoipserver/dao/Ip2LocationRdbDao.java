package com.bilichenko.geoipserver.dao;

import com.bilichenko.geoipserver.model.Ip2Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class Ip2LocationRdbDao implements Ip2LocationDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    
    @Override
    public Optional<Ip2Location> getByDecimalIp(Long decimalIp) {
        String sql =
                "SELECT ip_from, ip_to, country_code, country_name, region_name, city_name, latitude, longitude " +
                "FROM ip2location_db5 " +
                "WHERE ip_from <= :decimalIp AND ip_to >= :decimalIp;";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("decimalIp", decimalIp);
        try {
            Ip2Location entity = jdbcTemplate.queryForObject(sql, params, new Ip2LocationRowMapper());
            return Optional.ofNullable(entity);
        } catch (EmptyResultDataAccessException e) { // thrown when no result found for query
            return Optional.empty();
        }
    }

    private class Ip2LocationRowMapper implements RowMapper<Ip2Location> {
        @Override
        public Ip2Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ip2Location ip2Location = new Ip2Location();
            ip2Location.setIpFrom(rs.getLong("ip_from"));
            ip2Location.setIpTo(rs.getLong("ip_to"));
            ip2Location.setCountryCode(rs.getString("country_code"));
            ip2Location.setCountryName(rs.getString("country_name"));
            ip2Location.setRegionName(rs.getString("region_name"));
            ip2Location.setCityName(rs.getString("city_name"));
            ip2Location.setLatitude(rs.getDouble("latitude"));
            ip2Location.setLongitude(rs.getDouble("longitude"));
            return ip2Location;
        }
    }
}
