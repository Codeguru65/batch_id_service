package com.fbc.batchidservice.service;


import com.fbc.batchidservice.entity.Batch;
import com.fbc.batchidservice.entity.IdCheck;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public interface BatchIdCheckService {
    List<IdCheck> batchIdCheck(MultipartFile file, String token) throws IOException, UnirestException;
    void deleteBatch(Long id);
    List<Batch> deleteBatches();
    Optional<Batch> findBatchById(Long id);
    List<Batch> findAllBatches();
    void downloadCSV();


}
