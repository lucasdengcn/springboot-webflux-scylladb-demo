package com.example.demo.service

import com.datastax.oss.driver.api.core.cql.Row
import com.example.demo.entity.Mutant
import org.springframework.data.cassandra.core.cql.RowMapper

class MutantRowMapper : RowMapper<Mutant> {

    override fun mapRow(rs: Row, rowNum: Int): Mutant? {
        return Mutant(rs.getString("first_name"), rs.getString("last_name"))
    }

}