package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.time.LocalDateTime;

import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "category")
public class CategoryEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "category_category_id_seq")
    public Integer categoryId;

    /** */
    public String name;

    /** */
    public LocalDateTime lastUpdate;
}