package com.react.meteo.controller.rest;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import com.react.meteo.representals.RepresentalClass;
import com.react.meteo.representals.RestRepresentalClass;
import com.react.meteo.fct.Codification;
import com.react.meteo.fct.dt;
import com.react.meteo.getter.weatherBean;
import com.react.meteo.getter.weatherGet;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin
@RestController
@Validated
public class weatherRestController {

	
	
	
	@GetMapping("/weather/{ville}")
	public List<RepresentalClass> getByZoneA(@PathVariable String ville) {
		
		weatherGet weather = new weatherGet();
		
		RepresentalClass rc = new RepresentalClass();
		List<RepresentalClass> lrc = new ArrayList<RepresentalClass>();
		
		List<weatherBean> lwb = new ArrayList<weatherBean>();
        
        try {
        	
			lwb = weather.weatherOf(ville);
			
			for(weatherBean wf:lwb){
				
				rc.setId(new Codification().cd_prs(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wf.getFrom())));
				rc.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wf.getFrom()));
				rc.setTemperature(wf.getTemperature()+"");
				rc.setCielClaire(wf.getCielClaire()+"");
				rc.setHumidite(wf.getHumidite()+"");
				rc.setPression(wf.getPression()+"");
				rc.setVitesseAir(wf.getVitesseAir()+"");
				lrc.add(rc);
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
		
		return lrc;
	}
	
	
	
	@GetMapping("/weather/{ville}/{dts}")
	public List<RepresentalClass> getByZoneAndDateA(@PathVariable String ville,@PathVariable String dts) {
		
		weatherGet weather = new weatherGet();
		
		RepresentalClass rc = new RepresentalClass();
		List<RepresentalClass> lrc = new ArrayList<RepresentalClass>();
		
		List<weatherBean> lwb = new ArrayList<weatherBean>();
        
        try {
			lwb = weather.weatherOfUntil(ville, new dt().strToDate(dts, "ddMMyyyy"));
			
			for(weatherBean wf:lwb){
				
				rc.setId(new Codification().cd_prs(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wf.getFrom())));
				rc.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wf.getFrom()));
				rc.setTemperature(wf.getTemperature()+"");
				rc.setCielClaire(wf.getCielClaire()+"");
				rc.setHumidite(wf.getHumidite()+"");
				rc.setPression(wf.getPression()+"");
				rc.setVitesseAir(wf.getVitesseAir()+"");
				lrc.add(rc);
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
		
		return lrc;
	}
	
	
	
	
	@GetMapping("/weather/code/{ville}")
	public RestRepresentalClass getByZoneB(@PathVariable String ville) {
		weatherGet weather = new weatherGet();
		
		RepresentalClass rc = new RepresentalClass();
		RestRepresentalClass rrc = new RestRepresentalClass();
		List<RepresentalClass> lrc = new ArrayList<RepresentalClass>();
		
		List<weatherBean> lwb = new ArrayList<weatherBean>();
        
        try {
        	
			lwb = weather.weatherOf(ville);
			
			for(weatherBean wf:lwb){
				rc.setId(new Codification().cd_prs(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wf.getFrom())));
				rc.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wf.getFrom()));
				rc.setTemperature(wf.getTemperature()+"");
				rc.setCielClaire(wf.getCielClaire()+"");
				rc.setHumidite(wf.getHumidite()+"");
				rc.setPression(wf.getPression()+"");
				rc.setVitesseAir(wf.getVitesseAir()+"");
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
	
	
	
	@GetMapping("/weather/code/{ville}/{dts}")
	public RestRepresentalClass getByZoneAndDateB(@PathVariable String ville, @PathVariable String dts) {
		weatherGet weather = new weatherGet();
		
		RepresentalClass rc = new RepresentalClass();
		RestRepresentalClass rrc = new RestRepresentalClass();
		List<RepresentalClass> lrc = new ArrayList<RepresentalClass>();
		
		List<weatherBean> lwb = new ArrayList<weatherBean>();
        
        try {
        	
			//lwb = weather.weatherOf(ville);
			lwb = weather.weatherOfUntil(ville, new dt().strToDate(dts, "ddMMyyyy"));
			for(weatherBean wf:lwb){
				rc.setId(new Codification().cd_prs(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wf.getFrom())));
				rc.setDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wf.getFrom()));
				rc.setTemperature(wf.getTemperature()+"");
				rc.setCielClaire(wf.getCielClaire()+"");
				rc.setHumidite(wf.getHumidite()+"");
				rc.setPression(wf.getPression()+"");
				rc.setVitesseAir(wf.getVitesseAir()+"");
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
	
	
	
	
	@GetMapping("/weather/code/{ville}/today")
	public RestRepresentalClass getByZoneAndDateC(@PathVariable String ville) {		
		Date dts = new Date();
       return getByZoneAndDateB(ville, new SimpleDateFormat("ddMMyyyy").format(dts));
	}
	
        
 
      
	
}
