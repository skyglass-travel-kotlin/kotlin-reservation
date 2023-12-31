package com.twa.flights.api.reservation.service

import com.twa.flights.api.reservation.connector.CatalogHttpConnector
import com.twa.flights.api.reservation.connector.ClustersHttpConnector
import com.twa.flights.api.reservation.dto.ReservationDTO
import com.twa.flights.api.reservation.mapper.toDTO
import com.twa.flights.api.reservation.mapper.toModel
import com.twa.flights.api.reservation.model.Reservation
import com.twa.flights.api.reservation.repository.ReservationRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReservationService(private val repository: ReservationRepository, private val catalogHttpConnector: CatalogHttpConnector, private val clustersHttpConnector: ClustersHttpConnector) {

    private val logger: Logger = LoggerFactory.getLogger(ReservationService::class.java)

    fun getReservationById(id: Long): ReservationDTO {
        logger.info("Obtain the reservation information")
        val toReturn: Optional<Reservation> = repository.findById(id)
        return toReturn.get().toDTO()
    }

    fun saveReservation(reservation: ReservationDTO): ReservationDTO {
        logger.info("Saving the reservation information")

        validateWithExternalServices(reservation)

        val toSave = reservation.toModel()
        val toReturn = repository.save(toSave)
        return toReturn.toDTO()
    }

    private fun validateWithExternalServices(reservation: ReservationDTO) {
        reservation.itineraryId?.let { reservation.searchId?.let { it1 -> clustersHttpConnector.getCluster(it1, it) } }

        reservation.passengers?.forEach {
            it.nationality?.let { it1 -> catalogHttpConnector.getCountry(it1) }
        }
    }
}