package com.zipbom.zipbom.InterestedRoom.model;

import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Product.model.Product;

import javax.persistence.*;

import lombok.Getter;

@Entity
@Getter
@Table(name = "interested_room")
public class InterestedRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public InterestedRoom(Product product, User user) {
        this.product = product;
        this.user = user;
    }

    public InterestedRoom() {

    }
}
