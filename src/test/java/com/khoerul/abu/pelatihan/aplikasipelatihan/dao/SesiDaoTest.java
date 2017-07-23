/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoerul.abu.pelatihan.aplikasipelatihan.dao;

import com.khoerul.abu.pelatihan.aplikasipelatihan.entity.Materi;
import com.khoerul.abu.pelatihan.aplikasipelatihan.entity.Peserta;
import com.khoerul.abu.pelatihan.aplikasipelatihan.entity.Sesi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.DataSource;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Abu Khoerul I A
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {"/data/peserta.sql","/data/materi.sql","/data/sesi.sql"}
)
public class SesiDaoTest {

    @Autowired
    private SesiDao sd;
 
    @Test
    public void testCariByMateri(){
        Materi m  = new Materi();
        m.setId("aa6");
        
        
        PageRequest page = new PageRequest(0, 5);
        
        Page<Sesi> hasilQuery = sd.findByMateri(m,page);
        Assert.assertEquals(2L, hasilQuery.getTotalElements());
        
        
        Assert.assertFalse(hasilQuery.getContent().isEmpty());
        Sesi s = hasilQuery.getContent().get(0);
        Assert.assertNotNull(s);
        Assert.assertEquals("Java Fundamental",s.getMateri().getNama());
    }
    
    @Test
    public void testCariBerdasarkanTanggalMulaiDanKodeMateri() throws ParseException{
        PageRequest page = new PageRequest(0, 5);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date sejak = formatter.parse("2015-01-01");
        Date sampai = formatter.parse("2015-01-03");
        
        Page<Sesi> hasilQuery = sd.cariBerdasarkanTanggalMulaiDanKodeMateri(
                sejak, sampai, "JF-002", page);
        
        Assert.assertEquals(1L, hasilQuery.getTotalElements());
        Assert.assertFalse(hasilQuery.getContent().isEmpty());
        
        
        Sesi s = hasilQuery.getContent().get(0);
        Assert .assertEquals("Java Web", s.getMateri().getNama());
        
    }
   

    

    
}
