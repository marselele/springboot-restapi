package com.boniewijaya2021.springboot.service;

import com.boniewijaya2021.springboot.entity.TblProducts;
import com.boniewijaya2021.springboot.repository.ProductsRepository;
import com.boniewijaya2021.springboot.utility.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;


    //GET

    public ResponseEntity getDataBarang() {
        List<TblProducts> tblProducts = (List<TblProducts>) productsRepository.findAll();
        if (!tblProducts.isEmpty()) {
            return ResponseEntity.ok(tblProducts);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
        }
    }


    // GET DATA BARANG BY ID
//    public ResponseEntity<?> getDataBarang(UUID idProduksi) {
//        Optional<TblProducts> productsOptional = productsRepository.findById(idProduksi);
//        if (productsOptional.isPresent()) {
//            TblProducts tblProducts = productsOptional.get();
//            return ResponseEntity.ok(tblProducts);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not found");
//        }
//    }


    //CREATE UPDATE
    public ResponseEntity<MessageModel> addDataBarang(TblProducts tblProducts) {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();

        try {
            productsRepository.save(tblProducts);
            msg.setStatus(true);
            msg.setMessage("Success");
            result.put("data", tblProducts);
            msg.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(msg);

        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    // DELETE
    public ResponseEntity<MessageModel> removeDataBarang(UUID idProduksi) {
        MessageModel msg = new MessageModel();
        try {
            productsRepository.deleteById(idProduksi);
            msg.setStatus(true);
            msg.setMessage("Data successfully removed");
            return ResponseEntity.ok().body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage("Data not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);

        }
    }


}








