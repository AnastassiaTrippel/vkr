package com.example.muivvkr.service;

import com.example.muivvkr.entity.OrganizationEntity;
import com.example.muivvkr.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public void add(String title, String address, String items, String dispatch) {
        OrganizationEntity organization = new OrganizationEntity();
        organization.setTitle(title);
        organization.setAddress(address);
        organization.setUrl(items);
        organization.setDispatch(dispatch);
        organizationRepository.save(organization);
    }

    public List<OrganizationEntity> getOrganizations() {
        List<OrganizationEntity> organizations = new ArrayList<>();
        organizationRepository.findAll().forEach(organizations::add);
        return organizations;
    }
}
