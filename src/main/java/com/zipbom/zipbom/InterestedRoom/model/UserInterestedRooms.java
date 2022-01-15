package com.zipbom.zipbom.InterestedRoom.model;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class UserInterestedRooms {

    @OneToMany(mappedBy = "user")
    private final List<InterestedRoom> interestedRooms = new ArrayList<>();

}
