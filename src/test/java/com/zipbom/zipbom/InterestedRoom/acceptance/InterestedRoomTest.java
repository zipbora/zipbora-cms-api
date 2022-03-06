package com.zipbom.zipbom.InterestedRoom.acceptance;

import static com.zipbom.zipbom.Auth.jwt.UserAuthority.*;

import org.junit.jupiter.api.Test;

public class InterestedRoomTest {
	@Test
	void test() {
		System.out.println(ROLE_USER.compareTo(ROLE_ANONYMOUS_USER));
		System.out.println(ROLE_REAL_ESTATE_AGENT.compareTo(ROLE_USER));
	}
}
