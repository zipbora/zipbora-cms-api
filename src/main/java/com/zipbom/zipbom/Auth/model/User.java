package com.zipbom.zipbom.Auth.model;

import com.zipbom.zipbom.Product.model.Products;
import com.zipbom.zipbom.Product.model.RecentViews;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    @Id
    @Column(name = "user_id")
    private String id;

    private CellularProvider cellularProvider;

    private String phoneNumber;

    private SNSProvider provider;

    @Column(unique = true)
    private String providerId;

    private boolean isPhoneVerified;

    private String nickname;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createDate;

//    @Embedded
//    private RecentViews recentViews;
//
//    @Embedded
//    private InterestedRoom interestedRoom;

    @Embedded
    private Products products;
    @PrePersist // 디비에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
