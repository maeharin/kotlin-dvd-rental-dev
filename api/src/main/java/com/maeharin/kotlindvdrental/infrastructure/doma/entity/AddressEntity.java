package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.time.LocalDateTime;

import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "address")
public class AddressEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "address_address_id_seq")
    public Integer addressId;

    /** */
    public String address;

    /** */
    public String address2;

    /** */
    public String district;

    /** */
    public Short cityId;

    /** */
    public String postalCode;

    /** */
    public String phone;

    /** */
    public LocalDateTime lastUpdate;
}