package com.react.meteo.getter;


import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;



public class weatherGet {
	 
		  
	public List<weatherBean> weatherOf(String zoneName) throws SAXException, MalformedURLException, IOException, ParserConfigurationException, TransformerException{
		    String url = "http://api.openweathermap.org/data/2.5/forecast?q="+zoneName.toLowerCase().replaceAll("-", " ")+"&units=imperial&type=accurate&mode=xml&APPID=ffdd58e2c0b917abe7137c942a1c1d67";
		  //  http://api.openweathermap.org/data/2.5/forecast?q=annaba&units=imperial&type=accurate&mode=xml&APPID=ffdd58e2c0b917abe7137c942a1c1d67
		   
		    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db = dbf.newDocumentBuilder();
		    Document doc = db.parse(new URL(url).openStream());
		    
		    DOMSource domSource = new DOMSource(doc);
		    StringWriter writer = new StringWriter();
		    StreamResult result = new StreamResult(writer);
		    TransformerFactory tf = TransformerFactory.newInstance();
		    Transformer transformer = tf.newTransformer();
		    transformer.transform(domSource, result);
		    String str = writer.toString();


		    int x = str.indexOf("<forecast>");
		    int y = str.indexOf("</forecast>")+11;
		    str = str.substring(x, y);
		    str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"+str;
		    
		    Vector vct = getFromTimeTo(str);

		    List<weatherBean> LB = ListWb(vct);
		   
		    return LB;
	}
	
	public weatherBean weatherOf(String zone, Date day, String heure){
		
		weatherBean toReturn = new weatherBean();
	    weatherGet weather = new weatherGet();
        List<weatherBean> bean = new ArrayList<weatherBean>();
         try {
        	 bean = weather.weatherOf(zone);
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
		
          Date dts = new Date();
			try {
				 String dt = new SimpleDateFormat("dd/MM/yyyy").format(day);
				 dts = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dt+" "+heure+":00:00");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

         
         for(weatherBean wtr : bean){
        		String std = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(dts);
				String std0 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(wtr.getFrom());
				if(std.equals(std0)){
					toReturn = wtr;
					break;
				}
         }
		
         try {
        	 return toReturn;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	  
     private Vector getFromTimeTo(String str){
    	 Vector vct = new Vector();
    	 int lastIndex = 0;
    	 String str2 = str;
    	 String str1 = str2;
    	 boolean ok = false;
    	 int x = 0;
    	 int y = 0;
    	 while(str2.contains("</time>") || ok){
    		 str1 = str2;
        	  x = str2.indexOf("<time from=");
     	      y = str2.indexOf("</time>")+7;
     	     lastIndex = y;
     	     str2 = str2.substring(lastIndex, str2.length());
     	     if(str2.equals("")) ok = true;
     	     vct.add(str1.substring(x, y));
    	 }

         return vct;
     }
	

     private  List<weatherBean> ListWb(Vector vct){
    	 List<weatherBean> wbList = new ArrayList<weatherBean>();

    	 for(int i = 0; i<vct.size(); i++){
    		 weatherBean wbean = new weatherBean();
    		 String str = vct.elementAt(i)+"";
    		 
    		 String str0 = str.substring(str.indexOf("from=")+6, str.indexOf("to")-2);
    		 str0 = str0.replace("T", " ");
    		 Date dt;
    		 
             try {dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str0);wbean.setForm(dt);} 
             catch (ParseException e) {e.printStackTrace();}
    		 
             str0 = str.substring(str.indexOf("to=")+4, str.indexOf("symbol")-3);
    		 str0 = str0.replace("T", " ");
    		 
             try {dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str0);wbean.setTo(dt);} 
             catch (ParseException e) {e.printStackTrace();}
       
            
             String temp = str.substring(str.indexOf("<temperature"), str.indexOf("<pressure"));
             temp = temp.substring(temp.indexOf("value")+7, temp.indexOf("/")-1);
             float tmp = Float.parseFloat(temp);
             tmp = (tmp-32)*0.5555f;

    		 wbean.setTemperature(tmp);
    		 
    		 String humid = str.substring(str.indexOf("<humidity"), str.indexOf("<clouds"));
    		 humid = humid.substring(humid.indexOf("value")+7, humid.indexOf("/")-1);
             int hmd = Integer.parseInt(humid);
    		 wbean.setHumidite(hmd);
    		 
    		 String press = str.substring(str.indexOf("<pressure"), str.indexOf("<humidity"));
    		 press = press.substring(press.indexOf("value")+7, press.indexOf("/")-1);
             float prs = Float.parseFloat(press);
    		 wbean.setPression(prs);
    		 
    		 String vitAir = str.substring(str.indexOf("<windSpeed"), str.indexOf("<temperature"));
    		 vitAir = vitAir.substring(vitAir.indexOf("mps")+5, vitAir.indexOf("name")-2);
             float vitesseAir = Float.parseFloat(vitAir);
    		 wbean.setVitesseAir(vitesseAir);
       
    		 
    		 String DegreeAir = str.substring(str.indexOf("<windDirection"), str.indexOf("<windSpeed"));
    		 DegreeAir = DegreeAir.substring(DegreeAir.indexOf("deg")+5, DegreeAir.indexOf("name")-2);
             float degAir = Float.parseFloat(DegreeAir);
    		 wbean.setDegreeAir(degAir);
    		 
    		 
    		 DegreeAir = str.substring(str.indexOf("<windDirection"), str.indexOf("<windSpeed"));
    		 DegreeAir = DegreeAir.substring(DegreeAir.indexOf("name")+6, DegreeAir.indexOf("/")-1);
    		 wbean.setDirectionAir(DegreeAir);
    		 
    		 
    		 String cielClaire = str.substring(str.indexOf("<clouds"), str.indexOf("</time>"));
    		 cielClaire = cielClaire.substring(cielClaire.indexOf("all")+5, cielClaire.indexOf("unit")-2);
    		 int cClaire = Integer.parseInt(cielClaire);
    		 wbean.setCielClaire(cClaire);
    		 wbList.add(wbean);
    	 }
    	 
    	 
    	 return wbList;
     }
	  
	}