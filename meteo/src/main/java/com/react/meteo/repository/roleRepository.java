/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.react.meteo.repository;

import com.react.meteo.entities.tables.Role;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 *
 * @author Amine
 */
public interface roleRepository extends JpaRepository<Role, String>{
    
}
