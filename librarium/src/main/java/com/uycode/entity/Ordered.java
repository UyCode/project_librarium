package com.uycode.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ordered")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Ordered {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

   /* @OneToOne
    @JoinColumn(name = "uid")*/
    private  Integer uid;

    /*@OneToOne
    @JoinColumn(name = "bid")*/

    private Integer bid;



}
