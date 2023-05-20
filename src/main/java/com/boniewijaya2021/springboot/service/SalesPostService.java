package com.boniewijaya2021.springboot.service;

import com.boniewijaya2021.springboot.entity.TblSales;
import com.boniewijaya2021.springboot.entity.TblUsers;
import com.boniewijaya2021.springboot.pojo.UsersPostPojo;
import com.boniewijaya2021.springboot.repository.SalesRepository;
import com.boniewijaya2021.springboot.repository.UserRepository;
import com.boniewijaya2021.springboot.utility.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SalesPostService {

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<MessageModel> addDataPenjualan(TblSales tblSales)
    {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();

        try{
            salesRepository.save(tblSales);
            msg.setStatus(true);
            msg.setMessage("Success");
            result.put("data", tblSales);
            msg.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(msg);

        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }

    public ResponseEntity<MessageModel> addDataUsers(UsersPostPojo usersPostPojo)
    {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();


        try{
            TblUsers tblUsers = new TblUsers();
            tblUsers.setSalesName(usersPostPojo.getSalesName());
            tblUsers.setEmail(usersPostPojo.getEmail());
            userRepository.save(tblUsers);
            msg.setStatus(true);
            msg.setMessage("Success");
            result.put("data", tblUsers);
            msg.setData(result);
            return ResponseEntity.status(HttpStatus.OK).body(msg);

        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus(false);
            msg.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
        }
    }
}
