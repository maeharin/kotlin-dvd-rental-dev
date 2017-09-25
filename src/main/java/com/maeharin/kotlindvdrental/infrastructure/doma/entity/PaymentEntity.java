package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "payment")
public class PaymentEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "payment_payment_id_seq")
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