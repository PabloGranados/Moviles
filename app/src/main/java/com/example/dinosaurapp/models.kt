package com.example.dinosaurapp

/**
 * Modelo de datos para representar una Era Prehistórica
 */
data class Era(
    val id: String,
    val name: String,
    val description: String,
    val timeRange: String,
    val iconResource: Int,
    val primaryColor: Int,
    val secondaryColor: Int,
    val periods: List<String> = emptyList()
)

/**
 * Modelo de datos para representar un Período geológico
 */
data class Period(
    val id: String,
    val name: String,
    val description: String,
    val timeRange: String,
    val eraId: String,
    val iconResource: Int,
    val creatures: List<String> = emptyList()
)

/**
 * Modelo de datos para representar una criatura prehistórica
 */
data class Creature(
    val id: String,
    val name: String,
    val scientificName: String,
    val description: String,
    val periodId: String,
    val characteristics: List<String>,
    val habitat: String,
    val diet: String,
    val size: String,
    val discovery: String,
    val imageResource: Int,
    val interestPoints: List<InterestPoint> = emptyList()
)

/**
 * Modelo de datos para puntos de interés interactivos
 */
data class InterestPoint(
    val id: String,
    val title: String,
    val description: String,
    val type: InterestPointType,
    val xPosition: Float, // Posición X en porcentaje (0.0 - 1.0)
    val yPosition: Float, // Posición Y en porcentaje (0.0 - 1.0)
    val additionalInfo: String = ""
)

/**
 * Tipos de puntos de interés
 */
enum class InterestPointType {
    FOSSIL_SITE,
    RESEARCH_STATION,
    OBSERVATION_POINT,
    TIME_PORTAL,
    HABITAT_INFO,
    DISCOVERY_SITE
}