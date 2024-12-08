package domain.models.script

import java.util.*

/**
 * Contient les informations du terminal, qui sont passées au contexte de script
 */
data class DeviceInfo(
    val serialNumber: String,
    val androidVersion: String, // ex: 6.0
    val sdkVersion: Int, // ex: 23
    val buildDate: Date?, // ex: 1698641355
    val buildId: String, // ex: "TP1A.220624.014"
    val buildVersion: String, // ex: "G998BXXS9EWJO". Pas toujours dispo. Visible dans "A propos du telephone" -> "Version de la bande de base"
    val model: String, // ex: SM-A536B
    val productName: String, // ex: a53xnaeea
    val brand: String, // ex: "samsung" | "google" | "HONOR" | "HUAWEI"
    val locale: String, // ex: "en-US" | "fr-FR"
    val timezone: String, // ex: Europe/Paris
    val securityPatch: String, // ex: "2018-11-01"
    val lcdDensity: Int, // ex: 480
)
