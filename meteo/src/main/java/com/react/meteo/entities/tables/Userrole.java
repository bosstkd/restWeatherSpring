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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amine
 */
@Entity
@Table(name = "userrole", catalog = "meteo", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userrole.findAll", query = "SELECT u FROM Userrole u")
    , @NamedQuery(name = "Userrole.findById", query = "SELECT u FROM Userrole u WHERE u.id = :id")})
public class Userrole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    @JoinColumn(name = "user_name", referencedColumnName = "user_name", nullable = false)
    @ManyToOne(optional = false)
    private Users userName;
    @JoinColumn(name = "role", referencedColumnName = "role", nullable = false)
    @ManyToOne(optional = false)
    private Role role;

    public Userrole() {
    }

    public Userrole(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUserName() {
        return userName;
    }

    public void setUserName(Users userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        if (!(object instanceof Userrole)) {
            return false;
        }
        Userrole other = (Userrole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.react.meteo.entities.tables.Userrole[ id=" + id + " ]";
    }
    
}
