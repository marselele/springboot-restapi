package com.boniewijaya2021.springboot.pojo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
public class ProductsPojo {
    private String idProduksi;
    private String namaBarang;
    private String tipeBarang;
    private String asalBarang;
    private Timestamp tanggalProduksi;
    private Integer biayaProduksi;
}
