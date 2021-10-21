package jdbc;

import model.Venue;
import model.VenueDAO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JDBCVenueDAO implements VenueDAO {
    private JdbcTemplate jdbcTemplate;

    public JDBCVenueDAO(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Venue> getAllVenues() {
        String sqlGetAllVenue = "SELECT * FROM Venue";
        List<Venue> venues = new ArrayList<Venue>();


        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllVenue);

        while (results.next()) {
            venues.add(mapRowToVenue(results));
        }
        return venues;
    }

    private Venue mapRowToVenue(SqlRowSet row) {
        Venue venue = new Venue();

        venue.setId(row.getLong("id"));
        venue.setName(row.getString("name"));
        venue.setId(row.getLong("city_id"));
        venue.setName(row.getString("description"));
        return venue;
    }
}
