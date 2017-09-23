package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import org.jetbrains.annotations.NotNull;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.Id;
import org.seasar.doma.Table;
import org.seasar.doma.jdbc.entity.NamingType;

@Entity(naming = NamingType.SNAKE_UPPER_CASE)
@Table(name = "film")
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = org.seasar.doma.GenerationType.IDENTITY)
    public Integer filmId;

    @NotNull
    public String title;
}
