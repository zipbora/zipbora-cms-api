package com.zipbom.zipbom.RecentView.model;

import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Product.model.Product;

import javax.persistence.*;

@Entity
@Table(name = "recent_view")
public class RecentView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    protected RecentView() {
    }

    public RecentView(Product product, User user) {
        this.product = product;
        this.user = user;
    }
}
