package jdbc;

import java.util.ArrayList;
import java.util.List;
import model.Space;
import model.SpaceDAO;
import model.Venue;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JDBCSpaceDAO implements SpaceDAO {

    private JdbcTemplate jdbcTemplate;

    public List<Space> getAllSpaces() {
        String sqlGetAllSpaces = "SELECT space.id, space.name, venue_id, venue.name AS venue_name, open_from, open_to, daily_rate, max_occupancy FROM venue JOIN space ON venue.id = venue_id;";
        List<Space> spaces = new ArrayList<Space>();


        SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllSpaces);

        while (results.next()) {
            spaces.add(mapRowToSpace(results));
        }
        return spaces;
    }

    private Space mapRowToSpace(SqlRowSet row) {
        Space space = new Space();


        space.setId(row.getLong("id"));
        space.setName(row.getString("name"));
        space.setVenue_id(row.getLong("venue_id"));
        space.setVenue_name(row.getString("venue_name"));
        space.setOpen_from(row.getString("open_from"));
        space.setOpen_to(row.getString("open_to"));
        space.setDaily_rate(row.getDouble("daily_rate"));
        space.setMax_occupancy(row.getInt("max_occupancy"));

        return space;
    }

    @Override
    public Space getSpaceDetail(String selectSpace) {
        List<Space> spaces = new ArrayList<Space>();
        // List<Space> venuesWithCategory = new ArrayList<Venue>();


        String sqlGetSpaces = "SELECT venue.id, venue.name, description, city.id AS city_id, city.name AS city_name, state_abbreviation FROM venue JOIN city ON city.id = city_id ORDER BY venue.id ASC";

        SqlRowSet results = jdbcTemplate.queryForRowSet((sqlGetSpaces));

        while (results.next()) {
            spaces.add(mapRowToSpace(results));
        }return getSpaceDetail(selectSpace);
    }
}

