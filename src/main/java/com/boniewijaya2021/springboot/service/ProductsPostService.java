package com.boniewijaya2021.springboot.service;

import com.boniewijaya2021.springboot.entity.TblProducts;
import com.boniewijaya2021.springboot.pojo.ProductsPojo;
import com.boniewijaya2021.springboot.pojo.ProductsPostPojo;
import com.boniewijaya2021.springboot.repository.ProductsRepository;
import com.boniewijaya2021.springboot.repository.ProductsRepositoryClass;
import com.boniewijaya2021.springboot.utility.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class ProductsPostService {

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private ProductsRepositoryClass productsRepositoryClass;

    public ResponseEntity<MessageModel> addBarangClass(ProductsPostPojo productsPostPojo) {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();

        try {
            TblProducts tblProducts = new TblProducts();
            tblProducts.setNamaBarang(productsPostPojo.getNamaBarang());
            tblProducts.setTipeBarang(productsPostPojo.getTipeBarang());
            tblProducts.setAsalBarang(productsPostPojo.getAsalBarang());
            tblProducts.setTanggalProduksi(productsPostPojo.getTanggalProduksi());
            tblProducts.setBiayaProduksi(productsPostPojo.getBiayaProduksi());
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


}
