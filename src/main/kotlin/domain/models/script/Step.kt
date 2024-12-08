package domain.models.script

import domain.handlers.ScriptContext

/**
 * Représente une étape dans un script
 */


data class
Step (
    val title: String,
    val action: suspend ScriptContext.() -> Unit = {}
)