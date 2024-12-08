package domain.handlers

import domain.enums.AndroidKey
import domain.enums.DeviceOrientation
import domain.enums.Direction
import domain.models.*
import domain.models.script.DeviceInfo
import domain.models.uielement.UiElement


/**
 * Fournit le context d'un script, c'est à dire toutes les méthodes disponible dans un "step"
 */
interface ScriptContext {

    val deviceInfo: DeviceInfo

    /**
     * Permet de récupérer une valeur du provider
     */
    suspend fun provider(key: String): String


    /**
     * Permet d'executer une commande adb.
     *
     * Le mot clé "adb" en début de commande est optionnel.
     */
    suspend fun adb(command: String): ShellResult


    /**
     * Liste des propriètés du terminal
     */
    suspend fun getDeviceProperties(): Map<String, String>


    /**
     * Pousse / copie des fichiers ou des dossiers du poste de travail vers le terminal.
     *
     * @param origin Chemin du dossier ou du fichier que vous voulez copier
     * @param dest Chemin d'un dossier ou d'un fichier sur le telephone pour la copie (ex: sdcard/documents).
     * Si [origin] est un nom de fichier et [dest] un nom de dossier, le fichier sera copier avec le même nom.
     */
    suspend fun push(origin: String, dest: String)

    /**
     * Récupére / copie des fichiers ou des dossiers du telephone vers le poste de travail.
     *
     * @param origin Chemin d'un dossier ou d'un fichier sur le telephone pour la copie (ex: sdcard/documents).
     * @param dest Chemin du dossier ou du fichier vers où vous voulez copier.
     * Si [origin] est un nom de fichier et [dest] un nom de dossier, le fichier sera copier avec le même nom.
     */
    suspend fun pull(origin: String, dest: String)

    /**
     * Permet de savoir si un fichier ou un dossier existe sur le terminal.
     */
    suspend fun fileOrDirExists(path: String): Boolean

    /**
     * Permet de supprimer un fichier ou un dossier sur le terminal.
     *
     * Ne renvoi pas d'erreur si le fichier n'existe pas. Utiliser [fileOrDirExists] si c'est important.
     */
    suspend fun deleteFileOrDir(path: String)


    /**
     * Tap (clic) à des coordonnées de l'écran
     */
    suspend fun tap(x: Int, y: Int)

    /**
     * Fait un long tap à des coordonnées de l'écran
     */
    suspend fun longTap(x: Int, y: Int, duration: Int = 2000)


    /**
     * Permet d'écrire du texte dans un [UiElement]
     */
    suspend fun text(text: String, element: UiElement)

    /**
     * Permet d'écrire du texte dans le premier [UiElement] qui respecte le [predicate].
     *
     *
     * @throws domain.exceptions.ScriptError.PredicateNotFound Si aucun résultat pour le [predicate]
     * @return Le [UiElement] qui à été trouvé par le [predicate]
     */
    suspend fun text(text: String, predicate: (UiElement) -> Boolean): UiElement


    /**
     * Clique sur un [UiElement]
     */
    suspend fun click(elem: UiElement)

    /**
     * Clique sur un [UiElement] seulement si la [condition] est respectée
     */
    suspend fun click(
        element: UiElement,
        condition: (UiElement) -> Boolean = { true },
    )

    /**
     * Clique sur le premier [UiElement] qui respecte le [predicate].
     *
     * Si une [condition] est spécifié, le clique n'aura lieu que si elle est vrai.
     * Si aucune [condition] n'est spécifié, le clique aura lieu.
     *
     * @throws domain.exceptions.ScriptError.PredicateNotFound Si aucun résultat pour le [predicate]
     * @return Le [UiElement] qui à été trouvé par le [predicate]
     */
    suspend fun click(
        condition: (UiElement) -> Boolean = { true }, // Permet d'ANNULER l'action si la condition n'est pas rempli
        predicate: (UiElement) -> Boolean, // Permet de CHOISIR un element
    ): UiElement

    /**
     * Fait un clic long sur un [UiElement]
     */
    suspend fun longClick(elem: UiElement)

    /**
     * Fait un clic long sur un [UiElement] seulement si la [condition] est respectée
     */
    suspend fun longClick(
        element: UiElement,
        condition: (UiElement) -> Boolean = { true },
    )

    /**
     * Fait un clic long sur le premier [UiElement] qui respecte le [predicate].
     *
     * Si une [condition] est spécifié, le clique n'aura lieu que si elle est vrai.
     * Si aucune [condition] n'est spécifié, le clique aura lieu.
     *
     * @throws domain.exceptions.ScriptError.PredicateNotFound Si aucun résultat pour le [predicate]
     * @return Le [UiElement] qui à été trouvé par le [predicate]
     */
    suspend fun longClick(
        condition: (UiElement) -> Boolean = { true }, // Permet d'ANNULER l'action si la condition n'est pas rempli
        predicate: (UiElement) -> Boolean, // Permet de CHOISIR un element
    ): UiElement


    /**
     * Fait en sorte qu'un [UiElement] soit coché. Si il l'est déjà, rien ne se passera.
     *
     * @throws domain.exceptions.ScriptError.ItemNotCheckable Si le [element] n'est pas cheakable
     */
    suspend fun check(element: UiElement)

    /**
     * Fait en sorte que le premier [UiElement] qui respecte le [predicate] soit coché.
     * Si il l'est déjà, rien ne se passera.
     *
     * @throws domain.exceptions.ScriptError.PredicateNotFound Si aucun résultat pour le [predicate]
     * @throws domain.exceptions.ScriptError.ItemNotCheckable Si le résultat du [predicate] n'est pas cheakable
     * @return Le [UiElement] qui à été trouvé par le [predicate]
     */
    suspend fun check(predicate: (UiElement) -> Boolean): UiElement

    /**
     * Fait en sorte qu'un [UiElement] soit décoché. Si il l'est déjà, rien ne se passera.
     *
     * @throws domain.exceptions.ScriptError.ItemNotCheckable Si le [element] n'est pas cheakable
     */
    suspend fun uncheck(element: UiElement)

    /**
     * Fait en sorte que le premier [UiElement] qui respecte le [predicate] soit décoché.
     * Si il l'est déjà, rien ne se passera.
     *
     * @throws domain.exceptions.ScriptError.PredicateNotFound Si aucun résultat pour le [predicate]
     * @throws domain.exceptions.ScriptError.ItemNotCheckable Si le résultat du [predicate] n'est pas cheakable
     * @return Le [UiElement] qui à été trouvé par le [predicate]
     */
    suspend fun uncheck(predicate: (UiElement) -> Boolean): UiElement



    /**
     * Simule une touche android
     */
    suspend fun key(androidKey: AndroidKey)


    /**
     * Attend qu'un [UiElement] qui respecte le [predicate] apparaisse à l'écran.
     *
     * Si le timeout n'est pas défini ou si il est défini à 0, l'attente est infinie
     *
     * @throws domain.exceptions.ScriptError.WaitForPredicateTimeout Si le [predicate] n'est pas trouvé dans le temps imparti
     * @return Le [UiElement] qui à été trouvé par le [predicate]
     */
    suspend fun waitFor(timeoutMs: Long = 0, predicate: (UiElement) -> Boolean): UiElement


    /**
     * Désactive ou réactive la rotation auto de l'écran
     */
    suspend fun setAutoRotate(enable: Boolean)

    /**
     * Permet de choisir l'orientation de l'écran
     */
    suspend fun orientation(deviceOrientation: DeviceOrientation)


    /**
     * Permet de scroll d'un [UiElement].
     *
     * @throws domain.exceptions.ScriptError.ItemNotScrollable Si le [scrollableUiElement] n'est pas scrollable
     */
    suspend fun scroll(
        scrollableUiElement: UiElement,
        direction: Direction = Direction.DOWN,
        speed: Int = 5,
    )

    /**
     * Scroll le premier [UiElement] scrollable de l'ui
     *
     * @throws domain.exceptions.ScriptError.ItemNotScrollable Si aucun item ne peut être scrollé
     * @return Le [UiElement] qui à été trouvé
     */
    suspend fun scrollOnFirst(
        direction: Direction = Direction.DOWN,
        speed: Int = 5
    ): UiElement

    /**
     * Scroll aux coordonnées ([startX];[startY]) de l'écran
     */
    suspend fun scroll(
        direction: Direction = Direction.DOWN,
        speed: Int = 5,
        startX: Int = 540, // Milieu d'un écran 1080px
        startY: Int = 960, // Milieu d'un écran de 1920px
    )


    /**
     * Scroll un [UiElement] jusqu'à trouver un element qui correspond au [predicate].
     *
     * @throws domain.exceptions.ScriptError.ItemNotScrollable Si le [scrollableUiElement] ne peut être scrollé
     * @throws domain.exceptions.ScriptError.EndOfScroll Si on est arrivé en bas du scroll sans avoir trouvé le [predicate]
     * @return Le [UiElement] qui à été trouvé par le [predicate]
     */
    suspend fun scrollUntil(
        scrollableUiElement: UiElement,
        direction: Direction = Direction.DOWN,
        speed: Int = 5,
        predicate: (UiElement) -> Boolean,
    ): UiElement

    /**
     * Scroll le premier [UiElement] scrollable jusqu'à trouver un element qui correspond au [predicate].
     *
     * @throws domain.exceptions.ScriptError.ItemNotScrollable Si aucun item ne peut être scrollé
     * @throws domain.exceptions.ScriptError.EndOfScroll Si on est arrivé en bas du scroll sans avoir trouvé le [predicate]
     * @return Le [UiElement] qui à été trouvé par le [predicate]
     */
    suspend fun scrollUntilOnFirst(
        direction: Direction = Direction.DOWN,
        speed: Int = 5,
        predicate: (UiElement) -> Boolean,
    ): UiElement

    /**
     * Scroll aux coordonnées ([startX];[startY]) jusqu'à trouver un element qui correspond au [predicate].
     *
     * @throws domain.exceptions.ScriptError.EndOfScroll Si on est arrivé en bas du scroll sans avoir trouvé le [predicate]
     * @return Le [UiElement] qui à été trouvé par le [predicate]
     */
    suspend fun scrollUntil(
        direction: Direction = Direction.DOWN,
        speed: Int = 5,
        startX: Int = 540, // Milieu d'un écran 1080px
        startY: Int = 960, // Milieu d'un écran de 1920px
        predicate: (UiElement) -> Boolean,
    ): UiElement


    /**
     * Force l'arret d'une application / d'un package.
     *
     * Déclenche par défault une erreur quand l'application n'existe pas.
     *
     * @param packageName Nom du package / de l'application. Ex: com.example.myApp
     * @param throwErrorIfNotExists Doit-on stopper le script si le package n'existe pas (par défault: oui)
     */
    suspend fun forceStop(packageName: String, throwErrorIfNotExists: Boolean = true)


    /**
     * Permet le déclenchement d'un component.
     *
     * @param packageName Nom du package qui contient le composant
     * @param activity Chemin du composant à lancer
     * @param forceStop Doit-on forcer la fermeture du package avant de lancer le nouveau composant ?
     *
     * ```
     * // Lancement d'une application'
     * launchComponent("com.example.app", ".MainActivity")
     * ```
     */
    suspend fun launchComponent(
        packageName: String,
        activity: String,
        forceStop: Boolean = false,
        extras: Map<String, String> = emptyMap()
    )

    /**
     * Permet le déclenchement d'un component.
     *
     *
     * Lancement des options d'affichage dans les paramètres
     * ```
     * launchComponent("com.example.app/.MainActivity")
     * ```
     * @param component Chemin du composant tel qu'il est décrit dans le manifest de l'application
     */
    suspend fun launchComponent(component: String, extras: Map<String, String> = emptyMap())

    /**
     * Permet de lancer une activity.
     *
     * Seul les extra en string sont supporté.
     *
     * Exemple:
     * ```
     * launch("android.settings.DISPLAY_SETTINGS")
     */
    suspend fun launch(
        action: String? = null,
        category: String? = null,
        component: String? = null,
        extras: Map<String, String> = emptyMap()
    )

    /**
     * Permet d'atteindre un broadcast receiver.
     *
     * Il ne suppose que les extra de type String
     */
    suspend fun broadcast(
        action: String? = null,
        category: String? = null,
        component: String? = null,
        extras: Map<String, String> = emptyMap()
    )


    /**
     * TODO : A faire et bien définir
     */
    suspend fun swipe(elem: UiElement, direction: Direction)

    /**
     * TODO : A faire et bien définir
     */
    suspend fun drag()




    /**
     * Active ou désactive le google play protect sur le terminal
     */
    suspend fun setGooglePlayProtect(enable: Boolean)


    /**
     * Défini le temps que met l'écran à s'éteindre sans activité de l'utilisateur
     */
    suspend fun setScreenTimeout(durationMs: Long)

    /**
     * Défini le temps d'innactivité nécéssaire pour que le mot de passe soit de nouveau requis,
     * une fois que l'écran est éteinds (à condition que le vérrouillage instantanée soit désactivé)
     */
    suspend fun setLockAfterScreenOff(durationMs: Long)

    /**
     * Renvoi le type de sécurité utilisé par le terminal
     *
     * ex: NONE, PIN, PASSWORD, PATTERN (pas forcement en majuscule !)
     */
    suspend fun getUnlockMethod(): String

    /**
     * Permet de savoir si le terminal à une sécurité (code pin, mot de passe, patern, ...)
     */
    suspend fun hasSecurityCode(): Boolean

    /**
     * Défini un code pin
     *
     * @param pincode Le code pin à définir
     * @param actualCredential L'ancien code / password actuellement sur le terminal
     */
    suspend fun setPinCode(pincode: String, actualCredential: String? = null)

    /**
     * Défini un code pin
     *
     * @param password Le mot de passe à définir
     * @param actualCredential L'ancien code / password actuellement sur le terminal
     */
    suspend fun setPassword(password: String, actualCredential: String? = null)


    /**
     * Verifie que le credentials est bien celui actuellement défini sur le terminal
     */
    suspend fun verifyCredentials(credential: String): Boolean

    /**
     * Supprime l'authentification sur le terminal
     *
     * @param actualCredential L'ancien code / password actuellement sur le terminal
     */
    suspend fun clearCredentials(actualCredential: String)




    /**
     * Permet d'attendre X temps en millisecondes
     */
    suspend fun wait(durationMs: Long)

    /**
     * Répete une action tant que le [predicate] est respectée.
     *
     * Ne déclenche pas d'erreur si le prédicate n'est jamais trouvé.
     *
     *```
     * // Exemple
     * repeatWhile(predicate = { it.text.equals("continuer", true) }) {
     *     click(it)
     * }
     * // Equivalent en Kotlin pure
     * while(true) {
     *     val elem = findFirstOrNull { it.text.equals("continuer", true) } ?: break
     *     click(elem)
     * }
     *```
     */
    suspend fun repeatWhile(predicate: (UiElement) -> Boolean, action: suspend (UiElement) -> Unit)

    /**
     * Précise qu'un code est optionnel et ne doit pas déclencher d'erreurs
     */
    suspend fun noError(callback: suspend ScriptContext.() -> Unit)

    /**
     * Permet de stopper le script en indiquant un succès
     */
    suspend fun exitSuccess()

    /**
     * Permet de stopper le script en indiquant qu'une erreur à eu lieu
     *
     * @param reason La raison de l'erreur
     */
    suspend fun exitFailure(reason: String = "")



    /**
     * Execute une suite de callback jusqu'a ce qu'un fonctionne.
     *
     * Attention : Si une erreur autre que AppError.Script à eu lieu, la méthode arretera d'essayer et
     * ne renvera pas son exception.
     *
     * En effet on n'est censé rencontrer que des erreurs de scripts dans le script,
     * si quelque d'autre de plus grave ce passe il est important de la laisser passer et d'arreter cette méthode.
     *
     * @throws domain.exceptions.ScriptError.NoneCallbackWorked Si aucun appel n'à fonctionné.
     *
     */
    suspend fun firstSuccessOf(vararg tries: suspend ScriptContext.() -> Unit)


    /**
     * Renvoi la liste des [UiElement] à l'écran
     */
    suspend fun getElements(): List<UiElement>

    /**
     * Permet de savoir si un [UiElement] existe
     */
    suspend fun exists(element: UiElement): Boolean

    /**
     * Permet de savoir si un [UiElement] qui respecte le [predicate] existe
     */
    suspend fun exists(predicate: (UiElement) -> Boolean): Boolean

    /**
     * Permet de récupérer le premier [UiElement] qui respecte le [predicate]
     *
     * @throws domain.exceptions.ScriptError.PredicateNotFound Si aucun résultat pour le [predicate]
     */
    suspend fun findFirst(predicate: (UiElement) -> Boolean) : UiElement

    /**
     * Permet de récupérer le premier [UiElement] qui respecte le [predicate].
     *
     * La fonction renvoi null si aucun résultat.
     */
    suspend fun findFirstOrNull(predicate: (UiElement) -> Boolean) : UiElement?

    /**
     * Renvoi tous les [UiElement] qui respecte le [predicate].
     *
     * La notion de niveau est perdu, les éléments sont renvoyé au même niveau (flatten).
     */
    suspend fun findAll(predicate: (UiElement) -> Boolean) : List<UiElement>


    /**
     * Permet de récupérer le premier [UiElement] qui respecte le [predicate] **à partir de l'element [start]**
     *
     * @throws domain.exceptions.ScriptError.PredicateNotFound Si aucun résultat pour le [predicate]
     */
    suspend fun findFirstAfter(start: UiElement, predicate: (UiElement) -> Boolean) : UiElement

    /**
     * Permet de récupérer le premier [UiElement] qui respecte le [predicate] **à partir du premier predicate [start]**
     *
     * @throws domain.exceptions.ScriptError.PredicateNotFound Si aucun résultat pour le [predicate]
     *
     * ```
     *  // Renvoi le premier element que l'on peut coché après le texte "écran allumé"
     *  findFirstAfter(
     *      start = { it.text.contains("écran allumé", true) }
     *  ) {
     *      it.checkable
     *  }
     * ```
     */
    suspend fun findFirstAfter(
        start: (UiElement) -> Boolean,
        predicate: (UiElement) -> Boolean
    ) : UiElement


    /**
     * Permet de récupérer une variable globale définie précédemment
     */
    suspend fun <T> getGlobalVariable(name: String): T

    /**
     * Permet de définir une variable globale, utilisable dans d'autres étapes
     */
    suspend fun <T> setGlobalVariable(name: String, value: T)


    /**
     * Renvoi si le wifi est activé ou pas
     */
    suspend fun isWifiActivated(): Boolean

    /**
     * Active le Wifi
     */
    suspend fun turnOnWifi()

    /**
     * Désactive le Wifi
     */
    suspend fun turnOffWifi()

    /**
     * Obtient le SSID actuel du terminal
     */
    suspend fun getCurrentSsid(): String?

    /**
     * Permet de ce connecter à un wifi enregistré sur le terminal
     */
    @Deprecated("Ne fonctionne pas avec toutes les versions android")
    suspend fun connectToWifi(ssid: String)

    /**
     * Obtenient la liste des wifi configurés
     */
    suspend fun getConfiguredWifi(): List<ConfiguredWifi>

    /**
     * Attend que le wifi soit celui que l'on souhaite.
     *
     * @param ssid SSID du wifi que l'on souhaite
     * @param timeoutMs Temps que l'on souhaite attendre (0 = infini)
     * @param caseSensitive Est-ce qu'il faut être case sensitive ?
     */
    suspend fun waitForWifi(ssid: String, timeoutMs: Long = 0L, caseSensitive: Boolean = false)



    /**
     * Permet d'oublier un wifi enregistré sur le terminal
     */
    suspend fun forgetWifi(ssid: String)

    /**
     * Obtenir la liste des packages présent sur le terminal
     */
    suspend fun getPackagesList(): List<String>

    /**
     * Permet de savoir si un package existe
     */
    suspend fun hasPackage(packageName: String): Boolean

    /**
     * Permet d'attendre qu'un package soit installé sur le terminal si ce n'est pas déjà le cas.
     */
    suspend fun waitPackage(packageName: String, timeoutMs: Long = 0L)

    /**
     * Redémarre le terminal
     */
    suspend fun rebootDevice()

    /**
     * Eteinds le terminal
     */
    suspend fun shutdownDevice()

    /**
     * Désactive le déboggage USB
     */
    suspend fun disableAdb()

    /**
     * Désactive l'affichage du mode developpeur (attention, ne désactive pas adb)
     */
    suspend fun disableDeveloperMode()

}

