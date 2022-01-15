package com.zipbom.zipbom.RecentView.model;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserRecentViews {

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private final List<RecentView> recentViews = new ArrayList<>();

    public void addRecentView(RecentView recentView) {
        if (recentViews.size() >= 3) {
            recentViews.remove(0);
        }
        recentViews.add(recentView);
    }

    public List<RecentView> getRecentViews() {
        return Collections.unmodifiableList(recentViews);
    }
}
