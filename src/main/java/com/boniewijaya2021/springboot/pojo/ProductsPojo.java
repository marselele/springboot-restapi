package com.boniewijaya2021.springboot.pojo;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ProductsPojo {
    private String idProduksi;
    private String namaBarang;
    private String tipeBarang;
    private String asalBarang;
    private String tanggalProduksi;
    private Integer biayaProduksi;
}
