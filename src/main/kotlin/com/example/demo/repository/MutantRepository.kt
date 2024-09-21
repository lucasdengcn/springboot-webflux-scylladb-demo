package com.example.demo.repository

import com.example.demo.entity.Mutant
import com.example.demo.entity.MutantPrimaryKey
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface MutantRepository : ReactiveCrudRepository<Mutant, MutantPrimaryKey> {
}