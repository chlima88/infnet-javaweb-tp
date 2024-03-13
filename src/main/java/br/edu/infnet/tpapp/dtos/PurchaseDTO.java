package br.edu.infnet.tpapp.dtos;

import br.edu.infnet.tpapp.domain.model.Purchase;

import java.util.List;

public record PurchaseDTO (int id, int customerId, List<Integer> productsId) {
    public Integer getId() {
        return this.id;
    }


}
