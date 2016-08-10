package com.example.models

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * Item Model
 */
@Entity
@ToString
class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    long id
    String name
    String description
    int price
    int quantity


    public Item() {}

    /**
     * Quick constructor that sets the name and description to be the same
     * @param name
     * @param price
     * @param quantity
     */
    public Item(String name, int price, int quantity) {
        this.name =name;
        this.description = name;
        this.price = price;
        this.quantity = quantity;
    }
}
