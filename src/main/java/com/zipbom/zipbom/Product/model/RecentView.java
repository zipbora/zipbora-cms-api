package com.zipbom.zipbom.Product.model;

import com.zipbom.zipbom.Auth.model.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class RecentView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private Product product;

    private User user;

}
