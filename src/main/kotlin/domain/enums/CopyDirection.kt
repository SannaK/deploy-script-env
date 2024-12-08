package domain.enums

/**
 * Indique la direction d'une copie de fichier (pour le AppError associé)
 */
enum class CopyDirection {
    ComptuerToDevice,
    DeviceToComputer,
    DeviceToDevice,
    ComputerToComputer
}