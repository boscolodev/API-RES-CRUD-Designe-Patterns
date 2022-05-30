package com.dev.boscolo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.boscolo.Entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

}
