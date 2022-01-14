package com.zipbom.zipbom.Auth.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SNSProvider {
    KAKAO("kakao"), APPLE("apple");
    private final String snsProvider;
}
