package com.react.meteo.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.react.meteo.entities.RepresentalClass;
import com.react.meteo.entities.RestRepresentalClass;
import com.react.meteo.fct.Codification;
import com.react.meteo.getter.weatherBean;
import com.react.meteo.getter.weatherGet;


@RestController
public class weatherRestController {

	
	@GetMapping("/weather/{ville}")
	public RestRepresentalClass getByZone(@PathVariable String ville) {
		weatherGet weather = new weatherGet();
		
		RepresentalClass rc = new RepresentalClass();
		RestRepresentalClass rrc = new RestRepresentalClass();
		List<RepresentalClass> lrc = new ArrayList<RepresentalClass>();
		
		List<weatherBean> lwb = new ArrayList<weatherBean>();
        
        try {
        	
			lwb = weather.weatherOf(ville);
			
			for(weatherBean wf:lwb){
				
				rc.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wf.getFrom()));
				rc.setTemperature(wf.getTemperature());
				rc.setCielClaire(wf.getCielClaire());
				rc.setHumidite(wf.getHumidite());
				rc.setPression(wf.getPression());
				rc.setVitesseAir(wf.getVitesseAir());
				lrc.add(rc);
				rrc.setMeteo(lrc);
				rrc.setCode(new Codification().cd_structure(ville));
				rc = new RepresentalClass();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rrc;
	}
	
	
}
