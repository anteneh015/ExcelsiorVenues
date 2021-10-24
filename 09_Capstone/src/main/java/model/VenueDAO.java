package model;

import java.util.List;

public interface VenueDAO {
    List<Venue> getAllVenues();
    Venue getVenueDetail(String selectVenue);
    //Venue getVenueByCityId(long id);
   // List<Venue> getVenueWithCityName();
}
