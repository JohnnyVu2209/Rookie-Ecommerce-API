package com.musical.instrument.ecommerce.repositpory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musical.instrument.ecommerce.Entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer>{

}
