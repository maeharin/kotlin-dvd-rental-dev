package com.maeharin.kotlindvdrental.infrastructure.elasticsearch

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.maeharin.kotlindvdrental.ElasticSearchConfig
import com.maeharin.kotlindvdrental.domain.model.Actor
import com.maeharin.kotlindvdrental.domain.model.Category
import com.maeharin.kotlindvdrental.domain.model.Film
import com.maeharin.kotlindvdrental.domain.model.Language
import org.elasticsearch.action.bulk.BulkRequest
import org.elasticsearch.action.index.IndexRequest
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.common.xcontent.XContentType
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.springframework.stereotype.Repository

@Repository
class FilmRepositoryElasticSearchImpl(
    val objectMapper: ObjectMapper,
    val elasticSearchConfig: ElasticSearchConfig
){
    val INDEX_NAME = "dvd-rental"
    val TYPE_NAME = "films"

    fun bulkIndex(films: List<Film>) {
        val bulkRequest = BulkRequest().also { bulkRequest ->
            films.forEach { film ->
                // see: https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-index.html#java-docs-index-generate-beans
                val source = _toElasticSearchSrouce(film)

                val json = objectMapper.writeValueAsString(source)

                // see: https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-docs-index.html#java-docs-index-doc
                val indexRequest = IndexRequest(INDEX_NAME, TYPE_NAME, film.id.toString())
                                    .source(json, XContentType.JSON)

                bulkRequest.add(indexRequest)
            }
        }

        _getClient().bulk(bulkRequest)
    }

    fun search(): List<Film> {
        val searchRequest = SearchRequest(INDEX_NAME).types(TYPE_NAME).also { searchRequest ->
            val searchSourceBuilder = SearchSourceBuilder().also { builder ->
                builder.query(QueryBuilders.matchAllQuery())
            }

            searchRequest.source(searchSourceBuilder)
        }

        val searchResponse = _getClient().search(searchRequest)

        return searchResponse.hits.map { hit ->
            val json: String = hit.sourceAsString
            val filmSource: FilmElasticSearchSource = objectMapper.readValue(json)
            _toDomainModel(filmSource)
        }
    }

    private fun _getClient(): RestHighLevelClient {
        val host1 = elasticSearchConfig.httpHost1
        val host2 = elasticSearchConfig.httpHost2

        val lowLevelClient = if (host2 != null) {
            RestClient.builder(host1, host2).build()
        } else {
            RestClient.builder(host1).build()
        }

        return RestHighLevelClient(lowLevelClient)
    }

    private fun _toElasticSearchSrouce(film: Film): FilmElasticSearchSource {
        return FilmElasticSearchSource(
                id = film.id!!,
                title = film.title,
                description = film.description,
                releaseYear = film.releaseYear,
                rentalDuration = film.rentalDuration,
                rentalRate = film.rentalRate,
                length = film.length,
                replacementCost = film.replacementCost,
                language = LanguageElasticSearchSource(
                        id = film.language.id,
                        name = film.language.name,
                        updatedAt = film.language.updatedAt
                ),
                actors = film.actors.map { actor ->
                    ActorElasticSearchSource(
                            id = actor.id,
                            firstName = actor.firstName,
                            lastName = actor.lastName,
                            updatedAt = actor.updatedAt
                    )
                },
                categories = film.categories.map { category ->
                    CategoryElasticSearchSource(
                            id = category.id,
                            name = category.name,
                            updatedAt = category.updatedAt
                    )
                }
        )
    }

    private fun _toDomainModel(esSource: FilmElasticSearchSource): Film {
        return Film(
                id = esSource.id,
                title = esSource.title,
                description = esSource.description,
                releaseYear = esSource.releaseYear,
                rentalDuration = esSource.rentalDuration,
                rentalRate = esSource.rentalRate,
                length = esSource.length,
                replacementCost = esSource.replacementCost,
                language = Language(
                        id = esSource.language.id,
                        name = esSource.language.name,
                        updatedAt = esSource.language.updatedAt
                ),
                actors = esSource.actors.map { a ->
                    Actor(
                            id = a.id,
                            firstName = a.firstName,
                            lastName = a.lastName,
                            updatedAt = a.updatedAt
                    )
                },
                categories = esSource.categories.map { c ->
                    Category(
                            id = c.id,
                            name = c.name,
                            updatedAt = c.updatedAt
                    )
                }
        )
    }
}