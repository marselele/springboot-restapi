package com.boniewijaya2021.springboot.controller;

import com.boniewijaya2021.springboot.entity.TblProducts;
import com.boniewijaya2021.springboot.entity.TblSales;
import com.boniewijaya2021.springboot.pojo.ProductsPojo;
import com.boniewijaya2021.springboot.pojo.UsersPostPojo;
import com.boniewijaya2021.springboot.service.ProductsService;
import com.boniewijaya2021.springboot.service.SalesPostService;
import com.boniewijaya2021.springboot.service.SalesService;
import com.boniewijaya2021.springboot.utility.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/sample")
public class SampleGet {

    @Autowired
    private SalesService salesService;

    @Autowired
    private SalesPostService salesPostService;


    //    public ResponseEntity getDataBarang(@RequestParam UUID idProduksi) {
//        ResponseEntity responseEntity = productsService.getDataBarang(idProduksi);
//        return responseEntity;
//    }
//    @GetMapping("/get/dataBarang/{idProduksi}")
//    public ResponseEntity getDataBarang(@PathVariable UUID idProduksi) {
//        ResponseEntity responseEntity = productsService.getDataBarang(idProduksi);
//        return responseEntity;
//    }


    @GetMapping("/get/dataPenjualan")
    public ResponseEntity getDataPenjualan(@RequestParam UUID idSales) {
        ResponseEntity responseEntity = salesService.getDataPenjualan(idSales);
        return responseEntity;

    }

    @GetMapping("/get/dataPenjualanClass")
    public ResponseEntity getDataPenjualanDinamic(String namaSales, String namaBarang) {
        ResponseEntity responseEntity = salesService.getPenjualanClassrepo(namaSales, namaBarang);
        return responseEntity;
    }

    @PostMapping("/post/sales")
    private ResponseEntity<MessageModel> addSales(@RequestBody TblSales tblSales) {
        ResponseEntity responseEntity = salesPostService.addDataPenjualan(tblSales);
        return responseEntity;
    }

    @PostMapping("/post/users")
    private ResponseEntity<MessageModel> addUsers(@RequestBody UsersPostPojo usersPostPojo) {
        ResponseEntity responseEntity = salesPostService.addDataUsers(usersPostPojo);
        return responseEntity;
    }


}
