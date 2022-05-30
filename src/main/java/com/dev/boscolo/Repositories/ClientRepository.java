package com.dev.boscolo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.boscolo.Entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
