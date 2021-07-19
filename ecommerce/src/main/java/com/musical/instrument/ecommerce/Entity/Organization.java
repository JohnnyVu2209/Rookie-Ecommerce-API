package com.musical.instrument.ecommerce.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "organization")
@NoArgsConstructor
@AllArgsConstructor
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organization")
    private Long organizationId;

    @Column(name = "name")
    private String Name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image")
    private Image image;

    @Column(name ="create_date")
    private Date creatDate;

    @Column(name ="update_date")
    private Date updateDate;

    @Column(name ="isdeleted")
    private Boolean isDeleted;
}
