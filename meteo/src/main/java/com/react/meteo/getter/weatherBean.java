package com.react.meteo.getter;


import java.util.Date;

public class weatherBean {

private Date from;
private Date to;
	
private String directionAir;
private float degreeAir;
private float vitesseAir;
private float temperature;
private float pression;
private int humidite;
private int cielClaire;

public String getDirectionAir() {
	return directionAir;
}
public void setDirectionAir(String directionAir) {
	this.directionAir = directionAir;
}
public float getDegreeAir() {
	return degreeAir;
}
public void setDegreeAir(float degreeAir) {
	this.degreeAir = degreeAir;
}
public float getVitesseAir() {
	return vitesseAir;
}
public void setVitesseAir(float vitesseAir) {
	this.vitesseAir = vitesseAir;
}
public float getTemperature() {
	return temperature;
}
public void setTemperature(float temperature) {
	this.temperature = temperature;
}
public float getPression() {
	return pression;
}
public void setPression(float pression) {
	this.pression = pression;
}
public int getHumidite() {
	return humidite;
}
public void setHumidite(int humidite) {
	this.humidite = humidite;
}
public int getCielClaire() {
	return cielClaire;
}
public void setCielClaire(int cielClaire) {
	this.cielClaire = cielClaire;
}
public Date getFrom() {
	return from;
}
public void setForm(Date from) {
	this.from = from;
}
public Date getTo() {
	return to;
}
public void setTo(Date to) {
	this.to = to;
}


}
