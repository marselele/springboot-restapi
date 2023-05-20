package com.boniewijaya2021.springboot.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class PenjualanPojo {
    private String idPenjualan;
    private String namaBarang;
    private Double harga;
    private String salesName;
}
