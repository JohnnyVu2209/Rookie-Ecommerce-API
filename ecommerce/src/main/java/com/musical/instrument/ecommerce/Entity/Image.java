package com.musical.instrument.ecommerce.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Long imageId;

    @Column(name ="url")
    private String url;

    @Column(name ="create_date")
    private Date creatDate;

    @Column(name ="update_date")
    private Date updateDate;

    @Column(name ="isdeleted")
    private Boolean isDeleted;
}
