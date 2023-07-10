package com.twa.flights.api.reservation.model

import com.twa.flights.api.reservation.enums.DocumentType
import java.util.*
import javax.persistence.*
import java.time.LocalDate

@Entity
data class Document(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null,

    @Column(name = "number", nullable = false, length = 20)
    val number: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: DocumentType? = null,

    @Column(name = "expiration", nullable = false)
    val expiration: LocalDate? = null
)
