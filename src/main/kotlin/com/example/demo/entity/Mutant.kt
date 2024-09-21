package com.example.demo.entity

import org.springframework.data.cassandra.core.cql.PrimaryKeyType
import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn
import org.springframework.data.cassandra.core.mapping.Table


@PrimaryKeyClass
class MutantPrimaryKey {

    @PrimaryKeyColumn("first_name", type = PrimaryKeyType.PARTITIONED)
    var firstName: String? = null

    @PrimaryKeyColumn("last_name", type = PrimaryKeyType.PARTITIONED)
    var lastName: String? = null

    constructor() {}

    constructor(firstName: String?, lastName: String?) {
        this.lastName = lastName
        this.firstName = firstName
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MutantPrimaryKey

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstName?.hashCode() ?: 0
        result = 31 * result + (lastName?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "MutantPrimaryKey(firstName=$firstName, lastName=$lastName)"
    }


}

@Table("mutant_data")
class Mutant {

    @PrimaryKey
    var id: MutantPrimaryKey? = null

    var address: String? = null

    @Column("picture_location")
    var pictureLocation: String? = null

    constructor() {}

    constructor(id: MutantPrimaryKey){
        this.id = id
    }

    constructor(id: MutantPrimaryKey, address: String?, pictureLocation: String?) {
        this.id = id
        this.address = address
        this.pictureLocation = pictureLocation
    }

    constructor(lastName: String?, firstName: String?) {
        this.id = MutantPrimaryKey(lastName, firstName)
    }

    constructor(lastName: String?, firstName: String?, address: String?){
        this.id = MutantPrimaryKey(lastName, firstName)
        this.address = address
    }

    constructor(lastName: String?, firstName: String?, address: String?, pictureLocation: String?) {
        this.id = MutantPrimaryKey(lastName, firstName)
        this.address = address
        this.pictureLocation = pictureLocation
    }

    override fun toString(): String {
        return "Mutant(id=$id, address=$address, pictureLocation=$pictureLocation)"
    }


}