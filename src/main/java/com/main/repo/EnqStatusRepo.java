	package com.main.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.EnqStatusEntity;

@Repository
public interface EnqStatusRepo extends JpaRepository<EnqStatusEntity, Integer> {

}
