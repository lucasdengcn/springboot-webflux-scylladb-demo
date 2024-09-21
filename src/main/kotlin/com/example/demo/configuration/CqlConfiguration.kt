package com.example.demo.configuration

import com.datastax.oss.driver.api.core.CqlSession
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.core.cql.CqlTemplate

@Configuration
class CqlConfiguration {

    @Bean
    fun cqlTemplate(session : CqlSession, casandraProperties : CassandraProperties) : CqlTemplate {
        val cqlTemplate = CqlTemplate(session)
        return cqlTemplate;
    }

}