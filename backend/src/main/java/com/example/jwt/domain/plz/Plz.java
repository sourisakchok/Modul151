package com.example.jwt.domain.plz;

import com.example.jwt.core.generic.ExtendedAuditEntity;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "plz")
public class Plz {

    @Id
    @Column(name = "plz")
    private String plz;

    @Column(name = "ort")
    private String ort;

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public Plz(String plz, String ort) {
        this.plz = plz;
        this.ort = ort;
    }


    public Plz(){

    }

}
