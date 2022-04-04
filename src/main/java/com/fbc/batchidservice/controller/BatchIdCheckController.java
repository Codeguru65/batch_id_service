package com.fbc.batchidservice.controller;


import com.fbc.batchidservice.entity.Batch;
import com.fbc.batchidservice.entity.IdCheck;
import com.fbc.batchidservice.service.BatchIdCheckService;
import com.fbc.batchidservice.service.IdCheckService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BatchIdCheckController {

    @Qualifier("batchIdCheckSericeImpl")
    @Autowired
    private BatchIdCheckService batchIdCheckService;

    @Autowired
    private IdCheckService idCheckService;

    @PostMapping("/file-upload")
    public List<IdCheck> uploadFile(@RequestHeader(value="Accept") String acceptHeader,
                                    @RequestParam("file")MultipartFile file,
                                    @RequestHeader(value="Authorization") String authorizationHeader) throws IOException, UnirestException {

        System.out.print("authorizationHeader: "+authorizationHeader);
        return batchIdCheckService.batchIdCheck(file, authorizationHeader);
    }

    @GetMapping("/batch/{id}")
    public ResponseEntity findBatchById(@PathVariable("id") Long id){
       Optional<Batch> batch = batchIdCheckService.findBatchById(id);

       if(batch.isPresent()){
           return new ResponseEntity<>(batch.get(), HttpStatus.OK);
       }
       else {
           return new ResponseEntity("batch with id: "+id+" not found", HttpStatus.NOT_FOUND);
       }
    }

//    @PostMapping("/batch/create")
//    public Batch addBatch(@RequestBody Batch batch){
//
//    }

    @GetMapping("/download")
    public void download(){
        batchIdCheckService.downloadCSV();
    }




    @GetMapping("/batch-results/{id}")
    public List<IdCheck> findChecksByBatchId(@PathVariable("id") Long id){
       Optional optional =  batchIdCheckService.findBatchById(id);

       Batch batch;
       List<IdCheck> idChecks = new ArrayList<>();
       if(optional.isPresent()){
           batch = batchIdCheckService.findBatchById(id).get();
           idChecks = idCheckService.findAllByBatch(batch);
           return idChecks;
       }

       else{
           return idChecks;
       }


    }

    @GetMapping("/batch")
    public List<Batch> findAllBatches(){
        return batchIdCheckService.findAllBatches();
    }








}
