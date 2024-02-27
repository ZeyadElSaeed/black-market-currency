package com.zeyad.blackmarketcurrency.repositories;

import com.zeyad.blackmarketcurrency.models.Website;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteRepository extends JpaRepository<Website, Integer> {
    public Website deleteByUrl(String url);
}
