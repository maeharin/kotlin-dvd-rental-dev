package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.time.LocalDateTime;

import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "store")
public class StoreEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "store_store_id_seq")
    public Integer storeId;

    /** */
    public Short managerStaffId;

    /** */
    public Short addressId;

    /** */
    public LocalDateTime lastUpdate;
}