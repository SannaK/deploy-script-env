package domain.models.uielement

import jdk.jshell.spi.ExecutionControl.NotImplementedException

/**
 * Représente un element graphique android (récupéré souvent via adb)
 */

data class UiElement(
    val index: Int,
    val text: String,
    val id: String,
    val className: String,
    val packageName: String,
    val contentDescription: String = "",
    val checkable: Boolean = false,
    val checked: Boolean = false,
    val clickable: Boolean = false,
    val enabled: Boolean = true,
    val focusable: Boolean = false,
    val focused: Boolean = false,
    val scrollable: Boolean = false,
    val longClickable: Boolean = false,
    val password: Boolean = false,
    val selected: Boolean = false,
    val bounds: Bounds = Bounds(0,0,0,0),
    var parent: UiElement? = null,
    val children: List<UiElement> = emptyList()
) {

    override fun toString() = "[" +
            "index: $index; " +
            "text: $text; " +
            "id: $id; " +
            "className: $className; " +
            "contentDescription: $contentDescription" +
    "]"

    /**
     * Renvoi l'élement précédent de la liste (le frere, ou le dernier enfant du parent)
     */
    fun previousElement(): UiElement? {
        throw NotImplementedException("HIDDEN")
    }

    /**
     * Retourne le frere précédent de l'élement, ou le parent si le frere n'existe pas
     */
    fun previousSibling(): UiElement? {
        throw NotImplementedException("HIDDEN")
    }

    /**
     * Renvoi le prochain element dans la liste (le frere ou le frere du parent)
     */
    fun nextElement(): UiElement? {
        throw NotImplementedException("HIDDEN")
    }


    /**
     * Retourne le frere suivant de l'élement, ou le frere du parent si il n'y à pas de frere direct
     */
    fun nextSibling(): UiElement? {
        throw NotImplementedException("HIDDEN")
    }

}