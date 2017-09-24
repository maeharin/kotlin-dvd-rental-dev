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
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
@Table(name = "staff")
public class StaffEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer staffId;

    /** */
    public String firstName;

    /** */
    public String lastName;

    /** */
    public Short addressId;

    /** */
    public String email;

    /** */
    public Short storeId;

    /** */
    public Boolean active;

    /** */
    public String username;

    /** */
    public String password;

    /** */
    public LocalDateTime lastUpdate;

    /** */
    public byte[] picture;
}