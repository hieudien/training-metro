package com.example.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tbl_old_post")
public class OldPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String oldPostId;
    private String oldPostCode;
}
