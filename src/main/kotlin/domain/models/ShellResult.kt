package domain.models

/**
 * Résultat d'une fonction shell
 * @param resultCode Code de retour de la commande
 * @param output le texte qui à été renvoyé par la commande
 */

data class ShellResult (
    val resultCode: Int,
    val output: String,
)