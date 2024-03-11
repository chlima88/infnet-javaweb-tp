package br.edu.infnet.tpapp.dtos;

import br.edu.infnet.tpapp.domain.model.Purchase;

import java.util.List;

public record PurchaseDTO (int id, int customerId, List<Integer> productsId) {
    public Integer getId() {
        return this.id;
    }

    public boolean compareTo(PurchaseDTO other) throws Exception {
        if(other.getId().equals(this.getId())) throw new Exception("PurchaseId already in use");
        return false;
    }
}
