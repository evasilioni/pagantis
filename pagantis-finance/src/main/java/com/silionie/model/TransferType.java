package com.silionie.model;

import javax.persistence.*;

@Entity
@Table(name = "TRANSFER_TYPES")
public class TransferType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public TransferType() {
    }

    public TransferType(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
