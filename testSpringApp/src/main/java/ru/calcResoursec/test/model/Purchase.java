package ru.calcResoursec.test.model;

import javax.persistence.*;

@Entity
@Table(name = "my_purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
<<<<<<< HEAD
    private String name;
    private String category;
    private Long price;
    private Integer quantity;
    private Long sum;

    @ManyToOne
    @JoinColumn(name = "check_id")
    private Check check;
=======
    @ManyToOne
    @JoinColumn(name = "Check_Id")
    Check check;

    private String name;
    private String category;
    private int quantity;
    private float price;
>>>>>>> 085dfb237fa2e800d5bdf056f0b15e981fef6519

    public Purchase() {
    }

    public Purchase(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public void setPrice(Long price) {
        this.price = price;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }
}
