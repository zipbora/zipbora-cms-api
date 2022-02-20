package com.zipbom.zipbom.RecentView.service;

import com.zipbom.zipbom.Auth.jwt.JwtServiceImpl;
import com.zipbom.zipbom.Auth.model.User;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Product.model.Product;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.RecentView.model.RecentView;
import com.zipbom.zipbom.RecentView.repository.RecentViewRepository;
import com.zipbom.zipbom.Util.response.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
public class RecentViewService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RecentViewRepository recentViewRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private JwtServiceImpl jwtService;

    @Transactional
    public CMRespDto<?> addRecentView(HttpServletRequest httpServletRequest, Long productId) {

        User user = userRepository.findByUserId(
                (String) jwtService.getInfo(httpServletRequest.getHeader("jwt-auth-token")).get("userId"))
                .orElseThrow(IllegalArgumentException::new);

        Product product = productRepository.findByProductId(productId).get();

        RecentView recentView = new RecentView(product, user);

        user.addRecentView(recentView);

        return new CMRespDto<>(200, "add success", null);
    }
}
