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
@Table(name = "actor")
public class ActorEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer actorId;

    /** */
    public String firstName;

    /** */
    public String lastName;

    /** */
    public LocalDateTime lastUpdate;
}