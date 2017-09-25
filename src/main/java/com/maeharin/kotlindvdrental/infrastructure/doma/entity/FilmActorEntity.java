package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.time.LocalDateTime;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "film_actor")
public class FilmActorEntity {

    /** */
    @Id
    public Integer actorId;

    /** */
    @Id
    public Integer filmId;

    /** */
    public LocalDateTime lastUpdate;
}