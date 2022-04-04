package com.fbc.batchidservice.service;

import com.fbc.batchidservice.entity.*;
import com.fbc.batchidservice.repository.BatchRepository;
import com.fbc.batchidservice.repository.IdCheckRepository;

import java.io.*;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvListWriter;

import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class BatchIdCheckSericeImpl implements BatchIdCheckService {


    @Autowired
    private IdCheckRepository idCheckRepository;

    @Autowired
    private BatchRepository batchRepository;


    @Autowired
    private IdCheckService idCheckService;

    @Override
    public List<IdCheck> batchIdCheck(MultipartFile file, String token) throws IOException, UnirestException {



        String TOKEN_REQUEST_URL = "https://xarani.net/service/token/";
        String ID_BASE_URL = "https://xarani.net/service/person/?id_number=";
        RestTemplate restTemplate = new RestTemplate();
//        CredentialDAO credentialDAO = CredentialDAO.builder()
//                .username("admin")
//                .password("Xarani@2021")
//                .build();
//        Token token =  restTemplate.postForObject(TOKEN_REQUEST_URL, credentialDAO, Token.class);





        HttpHeaders header = new HttpHeaders();
        header.setBearerAuth(token);
        header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity("parameters", header);

        String line;
        String splitBy=", ";
        String fileName = file.getOriginalFilename();
        Batch batch = Batch.builder()
                .batchName(fileName)
                .build();

        System.out.println("bath name: "+batch.getBatchName());

        Batch savedBatch = batchRepository.save(batch);

        try{
            System.out.print("position");
            BufferedReader bufferedReader =  new BufferedReader( new InputStreamReader(file.getInputStream()));


            while ((line = bufferedReader.readLine()) != null){

                System.out.println("--> "+line);
                String[] person = line.split(splitBy);
                ResponseEntity responseEntity = restTemplate.exchange(ID_BASE_URL+person[2], HttpMethod.GET, httpEntity, RgDAO.class);
                RgDAO rgDAO = (RgDAO) responseEntity.getBody();



                System.out.print("body: "+responseEntity.getBody());
                IdCheck idCheck = IdCheck.builder()
                        .firstName(rgDAO.getFirstName())
                        .surname(rgDAO.getSurname())
                        .dateOfBirth(rgDAO.getDateOfBirth())
                        .birthPlace(rgDAO.getBirthPlace())
                        .personNo(rgDAO.getPersonNo())
                        .dateOfDeath(rgDAO.getDateOfDeath())
                        .sex(rgDAO.getSex())
                        .status(rgDAO.getStatus())
//                        .match(match)
                        .batch(batch)
                        .build();

                idCheckRepository.save(idCheck);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }




        return idCheckRepository.findAllByBatch(savedBatch);


    }

    @Override
    public void deleteBatch(Long id) {
        batchRepository.deleteById(id);
    }

    @Override
    public List<Batch> deleteBatches() {
        batchRepository.deleteAll();
        return findAllBatches();
    }

    @Override
    public Optional<Batch> findBatchById(Long id) {
        return batchRepository.findById(id);
    }

    @Override
    public List<Batch> findAllBatches() {
        return batchRepository.findAll();
    }

    @Override
    public void downloadCSV() {
       File file = new File("reports/report.csv");

       try{

           List<Batch> batches = batchRepository.findAll();
           System.out.println("finding the id Checks: "+batchRepository.findAll());
//           PrintWriter out  =  new PrintWriter(file);
//           List<IdCheck> IdChecks =  idCheckService.findAllChecks();
//           for (IdCheck i: IdChecks){
//               out.println(i.getFirstName()
//                       +", "+i.getSurname()
//                       +", "+i.getBirthPlace()
//                       +", "+i.getPersonNo()
//                       +", "+i.getSex()
//                       +", "+i.getStatus()
//                       +", "+i.getSource()
//               );
//           }

           PrintWriter out =  new PrintWriter(file);
           batches.forEach(batch -> out.println(batch.getBatchName()));
       }
       catch (Exception e){
           e.printStackTrace();
       }
    }


}
