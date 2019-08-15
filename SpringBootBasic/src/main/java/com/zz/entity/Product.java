package com.zz.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Product {
    String pid, pname, category,shelfLife;
    Date produceDate;
    int stock;
    BigDecimal price;

    public Product(){}

    public Product(String pid, String pname, String category, String shelfLife, Date produceDate, int stock,BigDecimal price){
        this.pid = pid;
        this.pname = pname;
        this.category = category;
        this.shelfLife = shelfLife;
        this.produceDate = produceDate;
        this.stock = stock;
        this.price = price;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Date getProduceDate() {
        return produceDate;
    }

    public void setProduceDate(Date produceDate) {
        this.produceDate = produceDate;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
