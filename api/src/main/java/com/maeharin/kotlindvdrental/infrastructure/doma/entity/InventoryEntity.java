package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.time.LocalDateTime;

import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "inventory")
public class InventoryEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "inventory_inventory_id_seq")
    public Integer inventoryId;

    /** */
    public Short filmId;

    /** */
    public Short storeId;

    /** */
    public LocalDateTime lastUpdate;
}