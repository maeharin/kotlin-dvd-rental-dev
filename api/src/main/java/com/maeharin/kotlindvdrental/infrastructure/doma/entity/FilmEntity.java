package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.jetbrains.annotations.Nullable;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

/**
 */
@Entity(naming = NamingType.SNAKE_LOWER_CASE)
@Table(name = "film")
public class FilmEntity {

    /** */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "film_film_id_seq")
    public Integer filmId;

    /** */
    public String title;

    /** */
    @Nullable
    public String description;

    /** */
    @Nullable
    public Integer releaseYear;

    /** */
    public Integer languageId;

    /** */
    public Short rentalDuration;

    /** */
    public BigDecimal rentalRate;

    /** */
    public Short length;

    /** */
    public BigDecimal replacementCost;

    /** */
    public LocalDateTime lastUpdate;
}