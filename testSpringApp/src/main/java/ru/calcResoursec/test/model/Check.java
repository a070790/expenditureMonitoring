package ru.calcResoursec.test.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "my_check")
public class Check {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "Person_Id")
    Person person;

    private String shopList;
    private Long sum;
    private String date;

    public Check(){

    }

     public Check(String shopList, Long sum, String date) {
        this.shopList = shopList;
        this.sum = sum;
        this.date = date;
    }

    public Check(Person person){
        this.person= person;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShopList() {
        return shopList;
    }

    public void setShopList(String shopList) {
        this.shopList = shopList;
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
}
