package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.time.LocalDateTime;

import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "actor")
public class ActorEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "actor_actor_id_seq")
    public Integer actorId;

    /** */
    public String firstName;

    /** */
    public String lastName;

    /** */
    public LocalDateTime lastUpdate;
}