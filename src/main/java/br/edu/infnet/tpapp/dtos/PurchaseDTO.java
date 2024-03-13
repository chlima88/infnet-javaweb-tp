package br.edu.infnet.tpapp.dtos;

import java.util.List;

public record PurchaseDTO (int id, int customerId, List<Integer> productsId) {
    public Integer getId() {
        return this.id;
    }


}
