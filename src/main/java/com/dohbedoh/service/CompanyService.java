package com.dohbedoh.service;

import com.dohbedoh.dao.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Allan on 12/10/2015.
 */
@Service(value = "companyService")
public class CompanyService {

    @Autowired
    private CompanyDAO companyDAO;
}
