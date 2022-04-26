package com.example.cablenetwork;

public class Payment {

    private String name;
    private String email;
    private Long ph;
    private Long amount;
    private String pmethod;
    private Long tranid;

    public Payment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPh() {
        return ph;
    }

    public void setPh(Long ph) {
        this.ph = ph;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getPmethod() {
        return pmethod;
    }

    public void setPmethod(String pmethod) {
        this.pmethod = pmethod;
    }

    public Long getTranid() {
        return tranid;
    }

    public void setTranid(Long tranid) {
        this.tranid = tranid;
    }
}
