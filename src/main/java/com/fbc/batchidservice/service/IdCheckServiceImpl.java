package com.fbc.batchidservice.service;

import com.fbc.batchidservice.entity.Batch;
import com.fbc.batchidservice.entity.IdCheck;
import com.fbc.batchidservice.repository.IdCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class IdCheckServiceImpl implements IdCheckService{

    @Autowired
    private IdCheckRepository idCheckRepository;

    @Override
    public List<IdCheck> findAllChecks() {
        return idCheckRepository.findAll();
    }

    @Override
    public List<IdCheck> findAllByBatch(Batch batch) {
        return idCheckRepository.findAllByBatch(batch);
    }


}
