package com.zipbom.zipbom.RecentView.service;

import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Product.model.Product;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.RecentView.model.RecentView;
import com.zipbom.zipbom.RecentView.repository.RecentViewRepository;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecentViewService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecentViewRepository recentViewRepository;
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public CMRespDto<?> addRecentView(PrincipalDetails principalDetails, Long productId) {
        User user = userRepository.findByUserId(principalDetails.getUserId()).get();
        Product product = productRepository.findByProductId(productId).get();
        RecentView recentView = new RecentView(product, user);
        user.addRecentViews(recentView);
        recentViewRepository.save(recentView);
        return new CMRespDto<>(200, "add success", null);
    }
}
