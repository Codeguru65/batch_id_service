package com.fbc.batchidservice.service;


import com.fbc.batchidservice.entity.Batch;
import com.fbc.batchidservice.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarchService {

    @Autowired
    private BatchRepository batchRepository;

    public Batch saveBatch(Batch batch){
        return batchRepository.save(batch);
    }
}
