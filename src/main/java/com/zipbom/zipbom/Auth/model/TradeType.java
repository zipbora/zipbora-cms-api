package com.zipbom.zipbom.Auth.model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TradeType {
    lease("전세"),sell("매매"),monthlyRent("월세");
    private final String type;
}
