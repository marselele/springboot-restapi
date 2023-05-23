package com.boniewijaya2021.springboot.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class ProductsPostPojo {
    private String namaBarang;
    private String tipeBarang;
    private String asalBarang;
    private Date tanggalProduksi;
    private Integer biayaProduksi;
}
