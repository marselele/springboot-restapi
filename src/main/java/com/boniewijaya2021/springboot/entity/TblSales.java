package com.boniewijaya2021.springboot.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_penjualan", schema = "sample")
public class TblSales implements Serializable {
//    @Id
//    @Column(name = "id_penjualan", nullable = false)
//    private Integer idPenjualan;

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "id_penjualan", nullable = false)
    private UUID idPenjualan;

    @Column(name = "nama_barang", nullable = false)
    private String namaBarang;

    @Column(name ="harga", nullable = false)
    private Double harga;

    @Column(name = "sales_name", nullable = false)
    private String salesName;



}
