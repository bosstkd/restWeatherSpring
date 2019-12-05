/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.react.meteo.controller.rest;

import com.react.meteo.entities.tables.Calcul;
import com.react.meteo.repository.calculRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Amine
 */
@CrossOrigin
@RestController
public class calculRestPostController {
    
    
    
    @Autowired
    private calculRepository cr;
    

    @PostMapping(path = "/calcul/insert")
    public String calcul(@RequestBody Calcul calcu){
        cr.save(calcu);
        return "Insertion effectuée";
      }
    
    
    @PostMapping(path = "/calcul/delete")
    public String calculDelete(@RequestBody Calcul calcu){
        cr.delete(calcu);
        return "Suppression effectuée";
      }
    
    
    
    @GetMapping(path = "/list/calcul")
    public List<Calcul> listCalcul(){
        return cr.findAll();
    }
    
    
}
