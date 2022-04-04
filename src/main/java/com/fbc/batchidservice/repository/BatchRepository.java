package com.fbc.batchidservice.repository;

import com.fbc.batchidservice.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BatchRepository extends JpaRepository<Batch,Long> {
}
