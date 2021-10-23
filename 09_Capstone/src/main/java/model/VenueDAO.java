package model;

import java.util.List;

public interface VenueDAO {
    List<Venue> getAllVenues();
    List<Venue> getVenueByCityId(long id);
    List<Venue> getVenueWithCityName();
}
