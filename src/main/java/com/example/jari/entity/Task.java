package com.example.jari.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name="Task")
public class Task extends BaseEntity{
    @Column(name="id")
    @Id
    private int id;

    @Column(name="order")
    private int order;

    @Column(name = "priority")
    private int priority;

    @Column(name = "type")
    private int type;

    @Column(name = "summary")
    private String summary;

    @Column(name = "descr")
    private String descr;

//    @ManyToOne
//    @JoinColumn(name = "listId")
//    private ListEntity list;
//
//    @ManyToOne
//    @JoinColumn(name = "reporterId")
//    private UserEntity reporter;
}
