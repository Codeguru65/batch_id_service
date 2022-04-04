package com.fbc.batchidservice.repository;

import com.fbc.batchidservice.entity.Batch;
import com.fbc.batchidservice.entity.IdCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IdCheckRepository extends JpaRepository<IdCheck, Long> {
    IdCheck findByBatch(Batch batch);

    List<IdCheck> findAllByBatch(Batch batch);


}
