package ru.calcResoursec.test.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "my_checks")
public class Check {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long sum;
    private String date;

    @OneToMany(
            mappedBy = "check",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Purchase> purchases = new ArrayList<>();

    public Check() {
    }

    public Check(User user, String date) {
        this.user = user;
        this.date = date;
    }

    public Check(User user, String date, Long sum) {
        this.sum = sum;
        this.date = date;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Iterable<Purchase> getPurchases() {
        return purchases;
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
        purchase.setCheck(this);

        calculateSum();
    }

    public void updatePurchase(Purchase purchase, Integer purchaseIndex) {
         Purchase updatedPurchase = purchases.get(purchaseIndex);

         updatedPurchase.setName(purchase.getName());
         updatedPurchase.setQuantity(purchase.getQuantity());
         updatedPurchase.setPrice(purchase.getPrice());
         updatedPurchase.setCategory(purchase.getCategory());

         calculateSum();
    }

    public void removePurchase(int index) {
        purchases.remove(index);

        calculateSum();
    }

    public Purchase getPurchase(int index) {
        Purchase purchase = purchases.get(index);

        return purchase;
    }

    public int searchPurchaseIndex(String purchaseName) {
        if (!purchases.isEmpty()) {
            Iterator<Purchase> iterator =  purchases.iterator();

            int i = -1;
            while (iterator.hasNext()) {
                i++;

                if (purchaseName.equals(iterator.next().getName())) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int searchPurchaseId(String purchaseName) {
        if (!purchases.isEmpty()) {
            Iterator<Purchase> iterator =  purchases.iterator();

            while (iterator.hasNext()) {
                Purchase purchase = iterator.next();
                if (purchaseName.equals(purchase.getName())) {
                    return purchase.getId();
                }
            }
        }
        return -1;
    }

    public boolean isNotEmpty() {
        if (purchases.isEmpty()) {
            return false;
        }
        return true;
    }

    private void calculateSum() {
        long s = 0;
        sum = s;

        Iterator<Purchase> iterator = purchases.iterator();

        while (iterator.hasNext()) {
            Purchase purchase = iterator.next();
            sum += purchase.getPrice() * purchase.getQuantity();
        }
    }
}
