package com.twa.flights.api.reservation.model

import com.twa.flights.api.reservation.enums.Gender
import com.twa.flights.api.reservation.enums.PassengerType
import javax.persistence.*
import java.time.LocalDate

@Entity
data class Passenger(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    val gender: Gender? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: PassengerType? = null,

    @Column(name = "birth", nullable = false)
    val birth: LocalDate? = null,

    @Column(name = "nationality", nullable = false, length = 3)
    val nationality: String? = null,

    @Column(name = "first_name", nullable = false, length = 20)
    val firstName: String? = null,

    @Column(name = "last_name", nullable = false, length = 20)
    val lastName: String? = null,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val document: Document? = null)
