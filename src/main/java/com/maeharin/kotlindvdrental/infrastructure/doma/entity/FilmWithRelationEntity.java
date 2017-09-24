package com.maeharin.kotlindvdrental.infrastructure.doma.entity;

import org.seasar.doma.*;

import java.time.LocalDateTime;

/**
 * 映画（Actor, Category, Languageなどの関連テーブルとjoinした結果をマッピングするDomaエンティティ）
 */
@Entity
public class FilmWithRelationEntity extends FilmEntity {
    /** language **/
    public String languageName;
    public LocalDateTime languageLastUpdate;

    /** categories */
    public Integer categoryId;
    public String categoryName;
    public LocalDateTime categoryLastUpdate;

    /** actors */
    public Integer actorId;
    public String actorFirstName;
    public String actorLastName;
    public LocalDateTime actorLastUpdate;
}