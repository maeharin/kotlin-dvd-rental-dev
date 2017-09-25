package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.time.LocalDateTime;

import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "rental")
public class RentalEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "rental_rental_id_seq")
    public Integer rentalId;

    /** */
    public LocalDateTime rentalDate;

    /** */
    public Integer inventoryId;

    /** */
    public Short customerId;

    /** */
    public LocalDateTime returnDate;

    /** */
    public Short staffId;

    /** */
    public LocalDateTime lastUpdate;
}