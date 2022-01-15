package com.zipbom.zipbom.RecentView.controller;

import com.zipbom.zipbom.Auth.model.PrincipalDetails;
import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.RecentView.dto.AddRecentViewRequestDto;
import com.zipbom.zipbom.RecentView.service.RecentViewService;
import com.zipbom.zipbom.Util.dto.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecentViewController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RecentViewService recentViewService;

    @PostMapping("/user/add/recentView")
    public CMRespDto<?> addRecentView(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody AddRecentViewRequestDto addRecentViewRequestDto) {
        System.out.println("============================");
        System.out.println(addRecentViewRequestDto.getProductId().getClass());
        System.out.println("============================");

        return recentViewService.addRecentView(principalDetails, addRecentViewRequestDto.getProductId());
    }
}

