package com.zipbom.zipbom.Auth.model;

import com.zipbom.zipbom.Auth.jwt.UserAuthority;
import com.zipbom.zipbom.InterestedRoom.model.InterestedRoom;
import com.zipbom.zipbom.InterestedRoom.model.UserInterestedRooms;
import com.zipbom.zipbom.Product.model.Products;
import com.zipbom.zipbom.RecentView.model.RecentView;
import com.zipbom.zipbom.RecentView.model.UserRecentViews;
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

    private String imageEncoding;

    @Enumerated(EnumType.STRING)
    private UserAuthority userAuthority;

    private LocalDateTime createDate;

    @Embedded
    private UserRecentViews recentViews;
    @Embedded
    private UserInterestedRooms userInterestedRooms;

    @Embedded
    private Products products;

    public void addRecentView(RecentView recentView) {
        recentViews.addRecentView(recentView);
    }

    public void addInterestedRoom(InterestedRoom interestedRoom){
        userInterestedRooms.addInterestedRoom(interestedRoom);
    }

    public UserRecentViews getRecentViews() {
        return recentViews;
    }

    @PrePersist // 디비에 INSERT 되기 직전에 실행
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
