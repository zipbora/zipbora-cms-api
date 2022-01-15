package com.zipbom.zipbom.RecentView.model;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class ProductRecentViews {
    @OneToMany(mappedBy = "product")
    private final List<RecentView> recentViews = new ArrayList<>();
}
