package com.fbc.batchidservice.service;

import com.fbc.batchidservice.entity.Batch;
import com.fbc.batchidservice.entity.IdCheck;

import java.util.List;

public interface IdCheckService {
    List<IdCheck> findAllChecks();


    List<IdCheck> findAllByBatch(Batch batch);
}
