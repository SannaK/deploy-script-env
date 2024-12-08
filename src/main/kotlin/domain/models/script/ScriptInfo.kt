package domain.models.script

import domain.utils.StringPath

/**
 * Représentation des informations d'un scripts qui à été chargé
 */
@Suppress("MemberVisibilityCanBePrivate")
sealed class ScriptInfo {

    /**
     * Soit le script est valide
     */
    data class Valid(
        /** Chemin vers le script **/
        val scriptPath: StringPath?,

        /** Objet script qui à été chargé et evalué **/
        val script: Script,

        /** Contenu raw du script **/
        val scriptContent : String,
    ) : ScriptInfo()

    /**
     * Soit il est invalide
     */
    data class Invalid(
        /** Chemin vers le script (si spécifié) **/
        val scriptPath: StringPath?,

        /** Message d'erreur qui explique pourquoi le script n'est pas valide **/
        val errorMessage: String,

        /** Il est possible que le script soit en erreur mais qu'on est quand même chargé la liste des étapes **/
        val steps: List<Step> = emptyList(),
    ) : ScriptInfo()
}
