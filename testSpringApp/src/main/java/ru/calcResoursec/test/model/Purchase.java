package ru.calcResoursec.test.model;

import javax.persistence.*;

@Entity
@Table(name = "my_purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Check_Id")
    Check check;

    private String name;
    private String category;
    private int quantity;
    private float price;

    public Integer getId() {
        return id;
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
}
