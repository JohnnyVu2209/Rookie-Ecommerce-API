package com.musical.instrument.ecommerce.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "account",uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email") }
    ,indexes = {
        @Index(name = "acc_email_pass",columnList = "email, password"),
        @Index(columnList = "username")
    })
public class Account {

    @Id
    @Column(name = "id_account")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 120)
    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "sex")
    private Boolean sex;

    @Size(max = 15)
    @Column(name = "phone")
    private String phone;

    @Email
    @Size(max = 50)
    @Column(name = "email")
    private String email;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "update_date")
    private Date updateDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
    @PrimaryKeyJoinColumn
    private Cart cart;

    @ManyToMany
    @JoinTable(
            name = "account_role",
            joinColumns = @JoinColumn(name = "id_account"),
            inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<Role> roles = new HashSet<>();

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
