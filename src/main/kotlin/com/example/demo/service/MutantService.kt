package com.example.demo.service

import com.datastax.oss.driver.api.core.cql.PreparedStatement
import com.example.demo.entity.Mutant
import com.example.demo.entity.MutantPrimaryKey
import com.example.demo.repository.MutantRepository
import org.springframework.data.cassandra.core.cql.CqlTemplate
import org.springframework.data.cassandra.core.cql.PreparedStatementBinder
import org.springframework.stereotype.Service

@Service
class MutantService (val cqlTemplate: CqlTemplate,
                     val mutantRepository: MutantRepository) {


    fun save(mutant: Mutant) : Boolean {
        //
        val cql = "insert into mutant_data (first_name, last_name) values (?, ?)"
        // create prepared statement
        val b = cqlTemplate.execute(cql, mutant.id?.firstName, mutant.id?.lastName)
        return b
    }

    fun queryById(id: MutantPrimaryKey) : List<Mutant> {
        //
        val cql = "select * from mutant_data where first_name = ? and last_name = ?"
        // binder
        val preparedStatementBinder = PreparedStatementBinder { ps : PreparedStatement ->
            ps.bind(id.firstName, id.lastName)
        }
        //
        return cqlTemplate.query(cql, preparedStatementBinder, MutantRowMapper())
    }

}