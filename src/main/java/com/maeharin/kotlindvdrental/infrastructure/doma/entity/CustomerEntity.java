package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "customer")
public class CustomerEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "customer_customer_id_seq")
    public Integer customerId;

    /**
     * ログインID
     */
    public String loginId;

    /**
     * ハッシュ化されたパスワード
     */
    public String passwordDigest;

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