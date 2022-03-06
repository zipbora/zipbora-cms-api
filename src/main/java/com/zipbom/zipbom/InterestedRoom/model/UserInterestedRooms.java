package com.zipbom.zipbom.InterestedRoom.model;

import javax.persistence.CascadeType;
import javax.persistence.EntityNotFoundException;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import com.zipbom.zipbom.Auth.model.User;

public class UserInterestedRooms {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<InterestedRoom> interestedRooms = new ArrayList<>();

    public void addInterestedRoom(InterestedRoom interestedRoom) {
        interestedRooms.add(interestedRoom);
    }

    public List<InterestedRoom> getInterestedRooms() {
        return interestedRooms;
    }

    public void deleteInterestedRooms(Long productId, User user) {
        interestedRooms.remove(        user.getUserInterestedRooms()
            .getInterestedRooms()
            .stream()
            .filter(interestedRoom -> interestedRoom.getId() == productId)
            .findFirst()
            .orElseThrow(EntityNotFoundException::new));
    }
}
