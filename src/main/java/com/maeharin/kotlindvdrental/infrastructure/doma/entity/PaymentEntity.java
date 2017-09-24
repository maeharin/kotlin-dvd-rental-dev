package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.math.BigDecimal;
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
@Table(name = "payment")
public class PaymentEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer paymentId;

    /** */
    public Short customerId;

    /** */
    public Short staffId;

    /** */
    public Integer rentalId;

    /** */
    public BigDecimal amount;

    /** */
    public LocalDateTime paymentDate;
}