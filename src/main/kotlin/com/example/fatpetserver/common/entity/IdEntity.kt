package com.example.fatpetserver.common.entity

import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class IdEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = DEFAULT_ID,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other as BaseEntity

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    companion object {
        const val DEFAULT_ID = 0L
    }
}
