package ru.calcResoursec.test.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "my_checks")
public class Check {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Long sum;
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "check",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Purchase> purchases;

    public Check() {
    }

    public Check(Long sum, String date, User user) {
        this.sum = sum;
        this.date = date;
        this.user = user;
    }

    public Integer getId() {
        return id;
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
