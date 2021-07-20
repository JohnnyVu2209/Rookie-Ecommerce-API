package com.musical.instrument.ecommerce.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cart",indexes = {
        @Index(name = "cart_quantity", columnList = "quantity"),
        @Index(name = "cart_amount", columnList = "amount")
})
public class Cart {

    @Id
    @Column(name = "id_cart")
    private Long id;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "amount")
    private Double amount;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "id_cart")
    private Account account;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

    public Cart(Account account){
        this.setId(account.getId());
        this.setCreateDate(new Date());
        this.setQuantity(0);
        this.account = account;
    }
}
