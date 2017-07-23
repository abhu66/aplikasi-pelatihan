/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.khoerul.abu.pelatihan.aplikasipelatihan.dao;

import com.khoerul.abu.pelatihan.aplikasipelatihan.entity.Peserta;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Abu Khoerul I A
 */
public interface PesertaDao extends PagingAndSortingRepository<Peserta, String> {
    
}
