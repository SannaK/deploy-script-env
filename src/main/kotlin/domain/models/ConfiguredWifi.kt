package domain.models

data class ConfiguredWifi (
    val id: Int,
    val ssid: String,
    val encryption: String
)