package com.boniewijaya2021.springboot.repository;

import com.boniewijaya2021.springboot.pojo.ProductsPojo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductsRepositoryClass {

    @PersistenceContext
    private EntityManager entityManager;


    //GET
    public List<ProductsPojo> getDataDinamic(String namaBarang, String tipeBarang
    ) {
        StringBuilder qb = new StringBuilder();

        if (namaBarang != null) {
            qb.append(" and nama_barang = :namaBarang \n");
        }
        if (tipeBarang != null) {
            qb.append("and tipe_barang = :tipeBarang \n");
        }

        String sisipan = qb.toString();


        String QueryText = "SELECT cast (id_produksi as varchar) id_produksi, nama_barang, tipe_barang, asal_barang, tanggal_produksi, biaya_produksi\n" +
                "FROM sample.tbl_products  WHERE 1=1 \n" + sisipan;

        Query query = entityManager.createNativeQuery(QueryText);
        if (namaBarang != null) {
            query.setParameter("namaBarang", namaBarang);
        }
        if (tipeBarang != null) {
            query.setParameter("tipeBarang", tipeBarang);
        }


        List hasil = query.getResultList();
        List<ProductsPojo> result = new ArrayList<>();

        Iterator itr = hasil.iterator();
        while (itr.hasNext()) {
            ProductsPojo browse = new ProductsPojo();
            Object[] obj = (Object[]) itr.next();
            String idProduksi = String.valueOf(obj[0]);
            Integer biayaProduksi = Integer.valueOf((Integer) obj[1]);
            namaBarang = String.valueOf(obj[2]);
            tipeBarang = String.valueOf(obj[3]);

            browse.setIdProduksi(idProduksi);
            browse.setBiayaProduksi(biayaProduksi);
            browse.setNamaBarang(namaBarang);
            browse.setTipeBarang(tipeBarang);
            result.add(browse);
        }
        return result;
    }

    public void createData(ProductsPojo product) {
        String QueryText = "INSERT INTO sample.tbl_products (nama_barang, tipe_barang, asal_barang, tanggal_produksi, biaya_produksi) " +
                "VALUES (:namaBarang, :tipeBarang, :asalBarang, :tanggalProduksi, :biayaProduksi)";

        Query query = entityManager.createNativeQuery(QueryText);
        query.setParameter("namaBarang", product.getNamaBarang());
        query.setParameter("tipeBarang", product.getTipeBarang());
        query.setParameter("asalBarang", product.getAsalBarang());
        query.setParameter("tanggalProduksi", product.getTanggalProduksi());
        query.setParameter("biayaProduksi", product.getBiayaProduksi());

        query.executeUpdate();
    }

    public void updateData(ProductsPojo product) {
        String QueryText = "UPDATE sample.tbl_products " +
                "SET nama_barang = :namaBarang, tipe_barang = :tipeBarang, asal_barang = :asalBarang, " +
                "tanggal_produksi = :tanggalProduksi, biaya_produksi = :biayaProduksi " +
                "WHERE id_produksi = :idProduksi";

        Query query = entityManager.createNativeQuery(QueryText);
        query.setParameter("namaBarang", product.getNamaBarang());
        query.setParameter("tipeBarang", product.getTipeBarang());
        query.setParameter("asalBarang", product.getAsalBarang());
        query.setParameter("tanggalProduksi", product.getTanggalProduksi());
        query.setParameter("biayaProduksi", product.getBiayaProduksi());
        query.setParameter("idProduksi", product.getIdProduksi());

        query.executeUpdate();
    }

    public void deleteData(String idProduksi) {
        String QueryText = "DELETE FROM sample.tbl_products WHERE id_produksi = :idProduksi";

        Query query = entityManager.createNativeQuery(QueryText);
        query.setParameter("idProduksi", idProduksi);

        query.executeUpdate();
    }


}
