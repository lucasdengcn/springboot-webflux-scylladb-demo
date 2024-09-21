package com.example.demo.service

import com.example.demo.entity.Mutant
import com.example.demo.entity.MutantPrimaryKey
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MutantServiceTests (@Autowired val mutantService: MutantService) {

    @Test
    fun `should save a mutant and return true`() {
        val mutant = Mutant("Deng", "Lucas")
        val result = mutantService.save(mutant)
        assertTrue(result)
    }

    @Test
    fun `query by existing id should return mutant object`() {
        val mutantId = MutantPrimaryKey("Deng", "Lucas")
        val mutantList = mutantService.queryById(mutantId)
        //
        assertFalse(mutantList.isEmpty())
        mutantList.forEach { println(it) }

    }

    @Test
    fun `query by non existing id should return empty list`() {
        val mutantId = MutantPrimaryKey("None", "Lucas")
        val mutantList = mutantService.queryById(mutantId)
        assertTrue(mutantList.isEmpty())
    }

}