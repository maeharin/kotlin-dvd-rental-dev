package com.maeharin.kotlindvdrental.infrastructure.elasticsearch

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.maeharin.kotlindvdrental.ElasticSearchConfig
import com.maeharin.kotlindvdrental.domain.model.Film
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
                val source = FilmElasticSearchSource(film)

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
            Film(filmSource)
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
}