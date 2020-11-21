/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author felixdadebo
 */
@Entity
@Table(name = "USERMODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usermodel.findAll", query = "SELECT u FROM Usermodel u")
    , @NamedQuery(name = "Usermodel.findByFollowers", query = "SELECT u FROM Usermodel u WHERE u.followers = :followers")
    , @NamedQuery(name = "Usermodel.findByName", query = "SELECT u FROM Usermodel u WHERE u.name = :name")
    , @NamedQuery(name = "Usermodel.findByActivity", query = "SELECT u FROM Usermodel u WHERE u.activity = :activity")
    , @NamedQuery(name = "Usermodel.findByNameAndActivity", query = "SELECT u FROM Usermodel u WHERE u.name = :name and u.activity = :activity")
    , @NamedQuery(name = "Usermodel.findByNameAdvanced", query = "SELECT u FROM Usermodel u WHERE  LOWER(u.name) LIKE  CONCAT('%', LOWER(:name), '%')")
  //  , @NamedQuery(name = "Usermodel.findByCgpaInBetween", query = "SELECT u FROM Usermodel u WHERE  u.cgpa >= :cgpa_low AND u.cgpa <= :cgpa_high ")


        
    })


public class Usermodel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FOLLOWERS")
    private Integer followers;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ACTIVITY")
    private Boolean activity;

    public Usermodel() {
    }

    public Usermodel(Integer followers) {
        this.followers = followers;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActivity() {
        return activity;
    }

    public void setActivity(Boolean activity) {
        this.activity = activity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (followers != null ? followers.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usermodel)) {
            return false;
        }
        Usermodel other = (Usermodel) object;
        if ((this.followers == null && other.followers != null) || (this.followers != null && !this.followers.equals(other.followers))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " model.Usermodel[ followers=" + followers + " ]";
    }

    public void setActivity(String active) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
