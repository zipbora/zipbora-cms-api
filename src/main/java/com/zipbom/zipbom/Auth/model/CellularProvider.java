package com.zipbom.zipbom.Auth.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CellularProvider {

    SKT("skt"), KT("kt"), LG("lg");

    private final String cellularProvider;

}
