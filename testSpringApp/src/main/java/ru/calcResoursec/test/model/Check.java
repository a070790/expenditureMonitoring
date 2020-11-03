package ru.calcResoursec.test.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "my_checks")
public class Check {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Long sum;
    private String date;
//    private User user;

    public Check() {
    }

    public Check(Long sum, String date) {
        this.sum = sum;
        this.date = date;
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

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
