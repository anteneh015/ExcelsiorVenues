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


    private Venue mapRowToVenue(SqlRowSet row) {
        Venue venue = new Venue();
        City city = new City();
        Category category = new Category();

        venue.setId(row.getLong("id"));
        venue.setName(row.getString("name"));
        venue.setCity_id(row.getLong("city_id"));
        venue.setDescription(row.getString("description"));

        return venue;
    }

    private Venue mapRowToVenueDetail(SqlRowSet row) {
        Venue venue = new Venue();
        City city = new City();
        Category category = new Category();

        venue.setId(row.getLong("id"));
        venue.setName(row.getString("name"));
        venue.setCity_id(row.getLong("city_id"));
        venue.setDescription(row.getString("description"));
        venue.setCity_name(row.getString("city_name"));
        venue.setState_abbreviation(row.getString("state_abbreviation"));
        venue.setCategoryName("");

        return venue;
    }


    @Override
    public Venue getVenueDetail(String selectVenue) {
        List<Venue> venues = new ArrayList<Venue>();
        List<Venue> venuesWithCategory = new ArrayList<Venue>();

        int venueChoice = Integer.parseInt(selectVenue)-1;
        String sqlGetVenues = "SELECT venue.id, venue.name, description, city.id AS city_id, city.name AS city_name, state_abbreviation FROM venue JOIN city ON city.id = city_id ORDER BY venue.id ASC";

        SqlRowSet results = jdbcTemplate.queryForRowSet((sqlGetVenues));

        while (results.next()) {
            venues.add(mapRowToVenueDetail(results));
        }

        // Todo: Extract to it's own method
        String sqlVenueCategory = "SELECT category.id as category_id, category.name, venue.id AS venue_id FROM category JOIN category_venue ON id = category_id JOIN venue ON venue.id = venue_id";
        SqlRowSet categoryResults = jdbcTemplate.queryForRowSet((sqlVenueCategory));
        String categoryString;

        Venue tempVenue;
        int venueId;
        while (categoryResults.next()) {
            venueId= Integer.parseInt(categoryResults.getString("venue_id")) - 1;
            tempVenue = venues.get(venueId);
            categoryString = venues.get(venueId).getCategoryName();
            categoryString += categoryResults.getString("name");

           venues.get(venueId).setCategoryName(categoryString + ", ");
        }

          return venues.get(venueChoice);
    }

//    VenueList = list of Venues
//  Choose only 1 venue based on what your first row of DB result  (venue_id)
//    Get CategoryName from that 1 venue
//    Set Category Name on that 1 Venue Object  based on what your first row of DB result (Category_name)
//    Put that on loop

}
