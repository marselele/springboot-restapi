package com.boniewijaya2021.springboot.service;

import com.boniewijaya2021.springboot.entity.TblProducts;
import com.boniewijaya2021.springboot.pojo.ProductsPojo;
import com.boniewijaya2021.springboot.repository.ProductsRepository;
import com.boniewijaya2021.springboot.repository.ProductsRepositoryClass;
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

    @Autowired
    private ProductsRepositoryClass productsRepositoryClass;

    //  Repo Class
    public ResponseEntity getBarangClass(String namaBarang, String tipeBarang) {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();
        try {
            List<ProductsPojo> data = productsRepositoryClass.getDataDinamic(namaBarang, tipeBarang);
            if (data.isEmpty()) {
                msg.setStatus(true);
                msg.setMessage("data tidak ditemukan");
                msg.setData(null);
                return ResponseEntity.ok().body(msg);
            } else {
                msg.setStatus(true);
                msg.setMessage("Success");
                result.put("data", data);
                msg.setData(result);
                return ResponseEntity.ok().body(msg);
            }

        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.ok().body(msg);

        }
    }

    public ResponseEntity createBarangClass(ProductsPojo product) {
        MessageModel msg = new MessageModel();
        try {

            productsRepositoryClass.createData(product);
            msg.setStatus(true);
            msg.setMessage("Data created successfully");
            msg.setData(null);

            return ResponseEntity.ok().body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());

            return ResponseEntity.ok().body(msg);
        }
    }

    public ResponseEntity updateBarangClass(ProductsPojo product) {
        MessageModel msg = new MessageModel();
        try {

            productsRepositoryClass.updateData(product);

            msg.setStatus(true);
            msg.setMessage("Data updated successfully");
            msg.setData(null);

            return ResponseEntity.ok().body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());

            return ResponseEntity.ok().body(msg);
        }
    }

    public ResponseEntity deleteBarangClass(String idProduksi) {
        MessageModel msg = new MessageModel();
        try {
            productsRepositoryClass.deleteData(idProduksi);
            msg.setStatus(true);
            msg.setMessage("Data deleted successfully");
            msg.setData(null);
            return ResponseEntity.ok().body(msg);
        } catch (Exception e) {
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.ok().body(msg);
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


// Repo Interface

    public ResponseEntity getDataBarang() {
        List<TblProducts> tblProducts = (List<TblProducts>) productsRepository.findAll();
        if (!tblProducts.isEmpty()) {
            return ResponseEntity.ok(tblProducts);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No data found");
        }
    }

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








