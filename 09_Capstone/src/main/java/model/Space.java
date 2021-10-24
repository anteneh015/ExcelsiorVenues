package model;

public class Space {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(Long venue_id) {
        this.venue_id = venue_id;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getOpen_from() {
        return open_from;
    }

    public void setOpen_from(String open_from) {
        this.open_from = open_from;
    }

    public String getOpen_to() {
        return open_to;
    }

    public void setOpen_to(String open_to) {
        this.open_to = open_to;
    }

    public double getDaily_rate() {
        return daily_rate;
    }

    public void setDaily_rate(double daily_rate) {
        this.daily_rate = daily_rate;
    }

    public int getMax_occupancy() {
        return max_occupancy;
    }

    public void setMax_occupancy(int max_occupancy) {
        this.max_occupancy = max_occupancy;
    }

    private String name;
    private Long venue_id;
    private String venue_name;
    private String open_from;
    private String open_to;
    private double daily_rate;
    private int max_occupancy;
}
