package com.zipbom.zipbom.InterestedRoom.model;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class UserInterestedRooms {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<InterestedRoom> interestedRooms = new ArrayList<>();

    public void addInterestedRoom(InterestedRoom interestedRoom) {
        interestedRooms.add(interestedRoom);
    }
}
