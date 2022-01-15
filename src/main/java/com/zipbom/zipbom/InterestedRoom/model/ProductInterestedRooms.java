package com.zipbom.zipbom.InterestedRoom.model;

import com.zipbom.zipbom.InterestedRoom.model.InterestedRoom;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class ProductInterestedRooms {
    @OneToMany(mappedBy = "product")
    private List<InterestedRoom> interestedRooms = new ArrayList<>();
}
