package br.edu.infnet.tpapp.services;

import br.edu.infnet.tpapp.domain.model.Purchase;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PurchaseService {

    private final Map<Integer, Purchase> purchasesDb;

    public PurchaseService() {
        this.purchasesDb = new HashMap<>();
    };

    public void add(Purchase purchase) {
        this.purchasesDb.put(purchase.getId(), purchase);
    };

    public Purchase get(int purchaseId) {
        return this.purchasesDb.get(purchaseId);
    };

    public void remove(int purchaseId) {
        this.purchasesDb.remove(purchaseId);
    };

    public Collection<Purchase> list() {
        return purchasesDb.values();
    };
}
