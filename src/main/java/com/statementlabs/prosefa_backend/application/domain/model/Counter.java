package com.statementlabs.prosefa_backend.application.domain.model;


import jakarta.persistence.*;

@Entity
@Table(name = "counters")
public class Counter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id; 
    private long seq;

    // ----- Getters and Setters -----

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public long getSeq() {
        return seq;
    }
    public void setSeq(long seq) {
        this.seq = seq;
    }

   
}
    