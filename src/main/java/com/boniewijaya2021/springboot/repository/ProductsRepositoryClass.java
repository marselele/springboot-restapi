package com.boniewijaya2021.springboot.repository;

import com.boniewijaya2021.springboot.pojo.ProductsPojo;
import com.boniewijaya2021.springboot.pojo.ProductsPostPojo;
import com.boniewijaya2021.springboot.utility.AppUtil;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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


        String QueryText = "SELECT cast (id_produksi as varchar) id_produksi, nama_barang, tipe_barang, asal_barang, tanggal_produksi, cast (biaya_produksi as varchar)biaya_produksi\n" +
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
            String namaBarangs = String.valueOf(obj[1]);
            String tipeBarangs = String.valueOf(obj[2]);
            String asalBarang = String.valueOf(obj[3]);
            LocalDateTime tanggalProduksi = ((Timestamp) obj[4]).toLocalDateTime();
            Integer biayaProduksi = Integer.valueOf((String) obj[5]);


            browse.setIdProduksi(idProduksi);
            browse.setNamaBarang(namaBarangs);
            browse.setTipeBarang(tipeBarangs);
            browse.setAsalBarang(asalBarang);
            browse.setTanggalProduksi(tanggalProduksi);
            browse.setBiayaProduksi(biayaProduksi);
            result.add(browse);
        }
        return result;
    }


    @Transactional
    public void insertDataBarang(ProductsPojo productsPojo) {
        UUID generatedUUID = UUID.randomUUID();
        String uuid = generatedUUID.toString();
        String namaBarang = productsPojo.getNamaBarang();
        String tipeBarang = productsPojo.getTipeBarang();
        String asalBarang = productsPojo.getAsalBarang();
        LocalDateTime tanggalProduksi = productsPojo.getTanggalProduksi();
        Integer biayaProduksi = productsPojo.getBiayaProduksi();

        String queryText = "INSERT INTO sample.tbl_products " +
                "(id_produksi, nama_barang, tipe_barang, asal_barang, tanggal_produksi, biaya_produksi)\n" +
                "VALUES " +
                "(CAST(:idProduksi AS UUID),:namaBarang,:tipeBarang,:asalBarang,:tanggalProduksi,:biayaProduksi)";

        entityManager.createNativeQuery(queryText)
                .setParameter("idProduksi", uuid)
                .setParameter("namaBarang", namaBarang)
                .setParameter("tipeBarang", tipeBarang)
                .setParameter("asalBarang", asalBarang)
                .setParameter("tanggalProduksi", tanggalProduksi)
                .setParameter("biayaProduksi", biayaProduksi).executeUpdate();
    }

    @Transactional
    public void updateDataBarang(ProductsPojo productsPojo) {
        StringBuilder queryString = new StringBuilder("UPDATE sample.tbl_produksi SET");

        // Iterate over the attributes of the provided POJO
        for (Field field : ProductsPojo.class.getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                if (field.getName().equals("idProduksi")) {
                    continue;
                } else {
                    value = field.get(productsPojo);
                }
            } catch (IllegalAccessException e) {
                continue;
            }

            if (value != null) {
                String snakeCaseField = AppUtil.camelCaseToUnderscore(field.getName());
                queryString.append(" ").append(snakeCaseField).append(" = :").append(field.getName()).append(",");
            }
        }

        queryString.setLength(queryString.length() - 1);

        queryString.append(" WHERE id_produksi = CAST(:idProduksi AS UUID)");

        Query query = entityManager.createNativeQuery(queryString.toString());

        for (Field field : ProductsPojo.class.getDeclaredFields()) {
            field.setAccessible(true);
            Object value;
            try {
                if (field.getName().equals("idProduksi")) {
                    continue;
                } else {
                    value = field.get(productsPojo);
                }
            } catch (IllegalAccessException e) {
                continue;
            }

            if (value != null) {
                query.setParameter(field.getName(), value);
            }
        }

        query.setParameter("idProduksi", productsPojo.getIdProduksi());
        query.executeUpdate();
    }


    public void deleteDataBarang(UUID idProduksi) {
        String queryText = "DELETE FROM sample.tbl_products " +
                "WHERE " +
                "id_produksi = CAST(:idProduksi AS UUID)";
        entityManager.createNativeQuery(queryText)
                .setParameter("idProduksi", idProduksi)
                .executeUpdate();
    }
}



