package br.edu.infnet.tpapp.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "purchaseDto")
public record PurchaseDTO (@Id int id, int customerId, List<Integer> productsId) {
    public Integer getId() {
        return this.id;
    }


}
