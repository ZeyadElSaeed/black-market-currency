package com.zeyad.blackmarketcurrency.repositories;

import com.zeyad.blackmarketcurrency.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    public List<Client> findAllByEmail(String email);
    public Client deleteByEmail(String email);
}
