package br.edu.infnet.tpapp.domain.model;

public class CustomerRank {

    private String name;
    private float discount;

    public CustomerRank (String name, float discount) {
        this.name = name;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }
    public float getDiscount() {
        return discount;
    }

}
