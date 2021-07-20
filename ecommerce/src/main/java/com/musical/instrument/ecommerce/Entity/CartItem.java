package com.musical.instrument.ecommerce.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart_item", indexes = {
        @Index(name = "item_pr_qtt", columnList = "id_product ,quantity"),
        @Index(name = "item_prc", columnList = "price"),
        @Index(name = "cart_item", columnList = "id_cart")
})
public class CartItem {

    @Id
    @Column(name = "id_item")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="id_product")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cart")
    private Cart cart;

    public CartItem( Product product, int quantity, Cart cart){
        this.quantity = quantity;
        this.product = product;
        this.amount = product.getPrice()*quantity;
        this.cart = cart;
    }
}
