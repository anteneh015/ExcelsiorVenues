package model;

import java.util.List;

public interface SpaceDAO {
    List<Space> getAllSpaces();
    Space getSpaceDetail(String selectSpace);
}
