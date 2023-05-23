package com.boniewijaya2021.springboot.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_products", schema = "exercise")
public class TblProducts implements Serializable {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "id_produksi", nullable = false)
    private UUID idProduksi;

    @Column(name = "nama_barang", nullable = false)
    private String namaBarang;

    @Column(name = "tipe_barang", nullable = false)
    private String tipeBarang;

    @Column(name = "asal_barang", nullable = false)
    private String asalBarang;

    @Column(name = "tanggal_produksi")
    private Timestamp tanggalProduksi;

    @Column(name = "biaya_produksi", nullable = false)
    private Integer biayaProduksi;

//    String QueryText = "SELECT cast (id_produksi as varchar) id_produksi, nama_barang, tipe_barang, asal_barang, tanggal_produksi, biaya_produksi\n" +
//                "FROM sample.tbl_products  WHERE 1=1 \n" + sisipan;


}


