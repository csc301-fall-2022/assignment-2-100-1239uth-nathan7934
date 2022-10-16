package com.example.backend.discount;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @SequenceGenerator(name = "discount_sequence", sequenceName = "discount_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "discount_sequence")
    private Long id;

    @Column(name = "code", unique = true, nullable = false)
    @Size(min = 6, max = 6)
    private String code;

    @Column(name = "discount_amount", nullable = false)
    private Integer discountAmount;

    public Discount() {
    }

    public Discount(String code, Integer discountAmount) {
        this.code = code;
        this.discountAmount = discountAmount;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discountAmount='" + discountAmount + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }
}
