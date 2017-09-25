package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "inventory")
public class InventoryEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer inventoryId;

    /** */
    public Short filmId;

    /** */
    public Short storeId;

    /** */
    public LocalDateTime lastUpdate;
}