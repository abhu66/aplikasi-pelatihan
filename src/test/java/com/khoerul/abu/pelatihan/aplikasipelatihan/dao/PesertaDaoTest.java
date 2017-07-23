/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoerul.abu.pelatihan.aplikasipelatihan.dao;

import com.khoerul.abu.pelatihan.aplikasipelatihan.entity.Peserta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        scripts = "/data/peserta.sql"
)
public class PesertaDaoTest {

    @Autowired
    private PesertaDao pd;

    @Autowired
    private DataSource ds;

    @Test
    public void testInsert() throws SQLException {
        Peserta p = new Peserta();
        p.setNama("Peserta 001");
        p.setEmail("peserta001@gmail.com");
        p.setTanggalLahir(new Date());

        pd.save(p);

        String sql = "select count(*) as jumlah "
                + "from peserta"
                + " where email = 'peserta001@gmail.com'";
        try (Connection s = ds.getConnection()) {
            ResultSet rs = s.createStatement().executeQuery(sql);
            Assert.assertTrue(rs.next());

            Long jumlahRow = rs.getLong("jumlah");
            Assert.assertEquals(1L, jumlahRow.longValue());
        }
    }

    @Test
    public void testHitung() {
        Long jumlah = pd.count();
        Assert.assertEquals(3L, jumlah.longValue());
    }

    @Test
    public void testCaribById() {
        Peserta p = pd.findOne("aa1");
        Assert.assertNotNull(p);
        Assert.assertEquals("Peserta Test 001", p.getNama());
        Assert.assertEquals("peserta.test.001@gmail.com", p.getEmail());

        Peserta px = pd.findOne("xx");
        Assert.assertNull(px);

    }

    @After
    public void hapusData() throws SQLException {
        String sql = "DELETE from peserta where email = 'peserta001@gmail.com'";
        try (Connection s = ds.getConnection()) {
            s.createStatement().executeUpdate(sql);
        }
    }
}
