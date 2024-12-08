package domain.models.uielement

/**
 * Rectangle qui représente les limites d'un UiElement
 */

data class Bounds(val left: Int, val top: Int, val right: Int, val bottom: Int) {
    val width get() = right - left
    val height get() = bottom - top
}