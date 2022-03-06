package com.zipbom.zipbom.InterestedRoom.repository;

import java.util.List;

import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.InterestedRoom.model.InterestedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestedRoomRepository extends JpaRepository<InterestedRoom, Long> {
	List<InterestedRoom> findAllByUser(User user);
}
