package com.example.demo.repository

import com.example.demo.entity.Mutant
import com.example.demo.entity.MutantPrimaryKey
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MutantRepositoryTests {

    @Autowired
    private lateinit var mutantRepository: MutantRepository

    @Test
    fun `should return empty list when no mutants exist`() {
        val mutants = mutantRepository.findAll()
        val mutableList = mutants.collectList().block()
        assertNotNull(mutableList)
        mutableList?.isEmpty()?.let { assertTrue(it) }
    }

    @Test
    fun `should save and retrieve a mutant`() {
        // Arrange
        val mutant = Mutant("Doe", "John")
        val mutantMono = mutantRepository.save(Mutant("Doe", "John"))
        val savedMutant = mutantMono.block()
        assertNotNull(savedMutant)
        // Act
        val retrievedMutant = savedMutant?.id?.let { mutantRepository.findById(it).block()}

        // Assert
        assertNotNull(retrievedMutant)
        assertEquals(mutant.id, retrievedMutant?.id)
    }

    @Test
    fun `should update a mutant`() {
        // Arrange
        val mutantId = MutantPrimaryKey("Doe", "John")
        val mutantMono = mutantRepository.findById(mutantId)
        val savedMutant = mutantMono.block()
        assertNotNull(savedMutant)
        //
        savedMutant?.address = "China"
        val updatedMutantMono = savedMutant?.let { mutantRepository.save(it) }

        // Act
        val retrievedMutantMono = updatedMutantMono?.block()?.id?.let { mutantRepository.findById(it) }
        val retrievedMutant = retrievedMutantMono?.block()
        // Assert
        assertNotNull(retrievedMutant)
        assertEquals(mutantId, retrievedMutant?.id)
    }

    @Test
    fun `should delete a mutant`() {
        // Arrange
        val mutantId = MutantPrimaryKey("Doe", "John")
        val existingMutant = mutantRepository.findById(mutantId).block()

        existingMutant?.let { mutantRepository.delete(it).block() }

        // Act
        val deletedMutant = existingMutant?.let { mutantRepository.findById(it.id!!).block() }

        // Assert
        assertNull(deletedMutant)
    }

}