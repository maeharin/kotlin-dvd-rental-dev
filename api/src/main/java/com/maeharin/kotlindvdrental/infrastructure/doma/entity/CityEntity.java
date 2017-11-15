package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.time.LocalDateTime;

import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "city")
public class CityEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "city_city_id_seq")
    public Integer cityId;

    /** */
    public String city;

    /** */
    public Short countryId;

    /** */
    public LocalDateTime lastUpdate;
}