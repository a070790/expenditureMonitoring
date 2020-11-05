package ru.calcResoursec.test.model;

import javax.persistence.*;
import java.util.ArrayList;
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

    private Integer checkNum;
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

    public Check(Long sum, Integer checkNum, String date, User user) {
        this.sum = sum;
        this.checkNum = checkNum;
        this.date = date;
        this.user = user;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
        purchase.setCheck(this);
    }

    public void removePurchase(Purchase purchase) {
        purchases.remove(purchase);
        purchase.setCheck(null);
    }
}
