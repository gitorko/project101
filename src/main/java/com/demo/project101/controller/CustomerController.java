package com.demo.project101.controller;

import java.util.List;

import com.demo.project101.config.TenantContext;
import com.demo.project101.domain.Customer;
import com.demo.project101.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    final CustomerService customerService;

    @GetMapping
    public List<Customer> getAll(@RequestHeader("X-TenantID") String tenantId) {
        try {
            TenantContext.setCurrentTenant(tenantId);
            return customerService.findAll();
        } finally {
            TenantContext.clear();
        }
    }

    @PostMapping
    public Customer saveCustomer(@RequestHeader("X-TenantID") String tenantId, @RequestBody Customer customer) {
        try {
            TenantContext.setCurrentTenant(tenantId);
            return customerService.save(customer);
        } finally {
            TenantContext.clear();
        }
    }
}
