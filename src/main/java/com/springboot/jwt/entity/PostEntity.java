package com.springboot.jwt.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(name = "post_title")
    private String postTitle;

    @Lob
    @Column(name = "post_body")
    private String postBody;

    @Column(name = "post_tag")
    private String postTag;

    @Column(name = "created_dt")
    private LocalDateTime createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "loggedInUserId", referencedColumnName = "loggedInUserId")
    private UserEntity userEntity;
}
