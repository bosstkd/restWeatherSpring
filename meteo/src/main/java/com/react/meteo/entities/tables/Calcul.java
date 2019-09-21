/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.react.meteo.entities.tables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "calcul", catalog = "meteo", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calcul.findAll", query = "SELECT c FROM Calcul c")
    , @NamedQuery(name = "Calcul.findById", query = "SELECT c FROM Calcul c WHERE c.id = :id")
    , @NamedQuery(name = "Calcul.findByEquation", query = "SELECT c FROM Calcul c WHERE c.equation = :equation")
    , @NamedQuery(name = "Calcul.findByResultat", query = "SELECT c FROM Calcul c WHERE c.resultat = :resultat")})
public class Calcul implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "equation", nullable = false, length = 255)
    private String equation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "resultat", nullable = false)
    private int resultat;

    public Calcul() {
    }

    public Calcul(Integer id) {
        this.id = id;
    }

    public Calcul(Integer id, String equation, int resultat) {
        this.id = id;
        this.equation = equation;
        this.resultat = resultat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public int getResultat() {
        return resultat;
    }

    public void setResultat(int resultat) {
        this.resultat = resultat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calcul)) {
            return false;
        }
        Calcul other = (Calcul) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.react.meteo.entities.tables.Calcul[ id=" + id + " ]";
    }
    
}
