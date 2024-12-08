package domain.models.script

import domain.handlers.ScriptContext

/**
 * Représentation d'un script une fois chargé
 */
class Script(
    @Suppress("PropertyName")
    val _providerName: String? = null
) {
    private var onStartCallback: suspend ScriptContext.() -> Unit = {}
    private var onSuccessCallback: suspend ScriptContext.(CurrentStep) -> Unit = {}
    private var onFailureCallback: suspend ScriptContext.(Throwable, CurrentStep) -> Unit = { _, _ -> }
    private var onCancelCallback: suspend ScriptContext.(CurrentStep) -> Unit = {}
    private var onFinishCallback: suspend ScriptContext.(CurrentStep) -> Unit = {}
    private val steps = mutableListOf<Step>()


    // Method to add a step to the script
    fun step(name: String, action: suspend ScriptContext.() -> Unit) {
        steps.add(Step(name, action))
    }

    fun onStart(callback: suspend ScriptContext.() -> Unit) {
        onStartCallback = callback
    }

    fun onSuccess(callback: suspend ScriptContext.(CurrentStep) -> Unit) {
        onSuccessCallback = callback
    }

    fun onFailure(callback: suspend ScriptContext.(Throwable, CurrentStep) -> Unit) {
        onFailureCallback = callback
    }

    fun onCancel(callback: suspend ScriptContext.(CurrentStep) -> Unit) {
        onCancelCallback = callback
    }

    fun onFinish(callback: suspend ScriptContext.(CurrentStep) -> Unit) {
        onFinishCallback = callback
    }


    // Methods for script execution
    @Suppress("FunctionName")
    fun _getSteps(): List<Step> = steps

    @Suppress("FunctionName")
    suspend fun _callOnStart(context: ScriptContext) = onStartCallback(context)

    @Suppress("FunctionName")
    suspend fun _callOnSuccess(context: ScriptContext, step: CurrentStep) = onSuccessCallback(context, step)

    @Suppress("FunctionName")
    suspend fun _callOnFailure(context: ScriptContext, cause: Throwable, step: CurrentStep) = onFailureCallback(context, cause, step)

    @Suppress("FunctionName")
    suspend fun _callOnCancel(context: ScriptContext, step: CurrentStep) = onCancelCallback(context, step)

    @Suppress("FunctionName")
    suspend fun _callOnFinish(context: ScriptContext, step: CurrentStep) = onFinishCallback(context, step)
}

/**
 * DSL function pour créer le script avec la syntaxe que l'ont souhaite
 */
fun script(provider: String? = null, init: Script.() -> Unit): Script {
    val script = Script(provider) // Create a new Script instance
    script.init() // Apply the init lambda to the Script instance
    return script // Return the initialized Script instance
}