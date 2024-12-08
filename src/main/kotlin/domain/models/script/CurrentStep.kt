package domain.models.script

/**
 * Représentation de l'étape en cours d'un script
 */
sealed class CurrentStep {
    data object Initializing: CurrentStep()

    /**
     * Etape du callback "onStart" au lancement du script
     */
    data object OnStart: CurrentStep()

    /**
     * Etape du callback "onError" quand le script à rencontré une erreur
     *
     * @param originStep Etape qui est à l'origine du onError (ça peut être le onStart comme une étape du script).
     */
    data class OnError(val originStep: CurrentStep): CurrentStep()

    /**
     * Etape du callback "onCancel" à l'annulation du script
     *
     * @param originStep Etape où on était au moment de l'annulation
     */
    data class OnCancel(val originStep: CurrentStep): CurrentStep()

    /**
     * Etape du callback "onFinish" à la fin du script (qu'il soit en erreur ou en succès)
     *
     * @param originStep Etape où on était au où la fin de script à été déclenché. Null si aucune étape en particulier.
     */
    data class OnFinish(val originStep: CurrentStep?): CurrentStep()


    /**
     * Etape du callback "onSuccess" quand le script c'est terminé en succès.
     *
     * @param originStep Etape où on était au où la fin de script à été déclenché. Null si aucune étape en particulier.
     */
    data class OnSuccess(val originStep: CurrentStep?) : CurrentStep()


    /**
     * Étape du script
     */
    data class ScriptStep(
        val title: String,
        val currentCount: Int,
        val totalCount: Int,
    ) : CurrentStep()
}