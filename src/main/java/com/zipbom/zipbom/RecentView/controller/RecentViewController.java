package com.zipbom.zipbom.RecentView.controller;

import com.zipbom.zipbom.Auth.repository.UserRepository;
import com.zipbom.zipbom.Product.repository.ProductRepository;
import com.zipbom.zipbom.RecentView.dto.AddRecentViewRequestDto;
import com.zipbom.zipbom.RecentView.service.RecentViewService;
import com.zipbom.zipbom.Util.response.CMRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/recentView")
public class RecentViewController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RecentViewService recentViewService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public CMRespDto<?> addRecentView(HttpServletRequest httpServletRequest, @RequestBody AddRecentViewRequestDto addRecentViewRequestDto) {
        return recentViewService.addRecentView(httpServletRequest, addRecentViewRequestDto.getProductId());
    }
}

