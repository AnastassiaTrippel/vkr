package com.example.muivvkr.controller;

import com.example.muivvkr.entity.OrganizationEntity;
import com.example.muivvkr.service.OrganizationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/organization")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class OrganizationController {

    private final OrganizationService organizationService;

    @GetMapping
    public String getOrganizations(Model model) throws JsonProcessingException {
        List<OrganizationEntity> organizations = organizationService.getOrganizations();
        model.addAttribute("organizations", organizations);
        return "organization";
    }

    @PostMapping
    public String addOrganization(@RequestParam String title, @RequestParam String address, @RequestParam String url,
                                  @RequestParam String dispatch, Map<String, Object> model) {
        organizationService.add(title, address, url, dispatch);
        List<OrganizationEntity> organizations = organizationService.getOrganizations();
        model.put("organizations", organizations);
        return "organization";
    }
}