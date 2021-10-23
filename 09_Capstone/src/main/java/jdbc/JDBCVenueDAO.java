package jdbc;

import model.Category;
import model.City;
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

    @Override
    public List<Venue> getVenueByCityId(long id) {
        return null;
    }

    private Venue mapRowToVenue(SqlRowSet row) {
        Venue venue = new Venue();
        City city = new City();
        Category category = new Category();

        venue.setId(row.getLong("venue.id"));
        venue.setName(row.getString("venue.name"));
        venue.setCity_id(row.getLong("city_id"));
        venue.setDescription(row.getString("description"));
        city.setCityName(row.getString("city.name"));
        city.setStateAbbreviation(row.getString("state_abbreviation"));
        category.setCategoryId(row.getLong("category_id"));
        category.setCategoryName(row.getString("category.name"));

        return venue;
    }

//    @Override
//    public List<Venue> getVenueByCityId(long id){
//
//        List<Venue> venueByCityId = new ArrayList<Venue>();
//        String sqlGetVenueByCityId = "Select venue_id, name, city_id, description " +
//                "FROM venue WHERE venue_id = ? AND city_id = ?";
//        SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlGetVenueByCityId, id);
//
//        while(rows.next()) {
//
//            venueByCityId.add(mapRowToVenue(rows));
//        }
//        return venueByCityId;
//    }




    @Override
    public List<Venue> getVenueWithCityName() {

        List<Venue> getVenueWithCityName = new ArrayList<Venue>();
        String sqlGetVenueWithCityName = "SELECT * FROM venue RIGHT JOIN city ON city_id = city.id RIGHT JOIN category_venue ON venue.id = venue_id RIGHT JOIN category ON category.id = category_id";
        SqlRowSet results = jdbcTemplate.queryForRowSet((sqlGetVenueWithCityName));

        while (results.next()) {
            getVenueWithCityName.add(mapRowToVenue(results));

        }
        return getVenueWithCityName;
    }

}
