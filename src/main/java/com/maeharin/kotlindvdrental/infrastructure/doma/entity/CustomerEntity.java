package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.time.LocalDate;
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
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
@Table(name = "customer")
public class CustomerEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer customerId;

    /** */
    public Short storeId;

    /** */
    public String firstName;

    /** */
    public String lastName;

    /** */
    public String email;

    /** */
    public Short addressId;

    /** */
    public Boolean activebool;

    /** */
    public LocalDate createDate;

    /** */
    public LocalDateTime lastUpdate;

    /** */
    public Integer active;
}