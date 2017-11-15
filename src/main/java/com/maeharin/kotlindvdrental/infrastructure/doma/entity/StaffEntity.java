package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.time.LocalDateTime;

import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "staff")
public class StaffEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "staff_staff_id_seq")
    public Integer staffId;

    /**
     * ログインID
     */
    public String loginId;

    /**
     * ハッシュ化されたパスワード
     */
    public String passwordDigest;

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
    public LocalDateTime lastUpdate;

    /** */
    public byte[] picture;
}