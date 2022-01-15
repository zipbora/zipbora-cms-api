package com.zipbom.zipbom.InterestedRoom.model;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class ProductInterestedRooms {
    @OneToMany(mappedBy = "product")
    private final List<InterestedRoom> interestedRooms = new ArrayList<>();
}
