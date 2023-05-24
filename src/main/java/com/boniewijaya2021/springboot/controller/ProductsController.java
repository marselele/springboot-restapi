package com.boniewijaya2021.springboot.controller;

import com.boniewijaya2021.springboot.entity.TblProducts;
import com.boniewijaya2021.springboot.pojo.ProductsPojo;
import com.boniewijaya2021.springboot.pojo.ProductsPostPojo;
import com.boniewijaya2021.springboot.pojo.UsersPostPojo;
import com.boniewijaya2021.springboot.service.ProductsPostService;
import com.boniewijaya2021.springboot.service.ProductsService;
import com.boniewijaya2021.springboot.utility.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductsPostService productsPostService;

    // repo interface

//    @GetMapping("/get/dataBarang")
//    public ResponseEntity getDataBarang() {
//        ResponseEntity responseEntity = productsService.getDataBarang();
//        return responseEntity;
//    }
//
//    @PostMapping("/post/dataBarang")
//    private ResponseEntity<MessageModel> addDataBarang(@RequestBody TblProducts tblProducts) {
//        ResponseEntity responseEntity = productsService.addDataBarang(tblProducts);
//        return responseEntity;
//    }
//
//    @DeleteMapping("/delete/dataBarang/{idProduksi}")
//    public ResponseEntity removeDataBarang(@PathVariable("idProduksi") UUID idProduksi) {
//        ResponseEntity responseEntity = productsService.removeDataBarang(idProduksi);
//        return responseEntity;
//    }

    // repo class

    @GetMapping("/get/dataBarangClass")
    public ResponseEntity getBarangClass(@RequestParam(value = "namaBarang", required = false) String namaBarang,
                                         @RequestParam(value = "tipeBarang", required = false) String tipeBarang) {
        return productsService.getBarangClass(namaBarang, tipeBarang);
    }

    @PostMapping("/post/dataBarangClass")
    private ResponseEntity<MessageModel> addBarangClass(@RequestBody ProductsPojo productsPojo) {
        ResponseEntity responseEntity = productsPostService.addBarangClass(productsPojo);
        return responseEntity;
    }

    @PutMapping("/put/dataBarangClass")
    public ResponseEntity<MessageModel> updateBarang(@RequestBody ProductsPojo productsPojo) {
        ResponseEntity responseEntity = productsPostService.updateBarangClass(productsPojo);
        return responseEntity;
    }


    @DeleteMapping("/delete/dataBarangClass/{idProduksi}")
    public ResponseEntity removeBarangClass(@PathVariable("idProduksi") UUID idProduksi) {
        ResponseEntity responseEntity = productsPostService.deleteBarangClass(idProduksi);
        return responseEntity;
    }


}
