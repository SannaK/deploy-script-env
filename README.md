# Comment configurer son environnement pour faire des scripts "Deploy"

## Telecharger un IDE
Vous pouvez telecharger un IDE tel que IntelliJ, Fleet ou Visual Studio Code


## Telecharger le projet / Cloner le git
Vous pouvez telecharger ou cloner le project


## Ouvrir le projet avec l'IDE
 - Ouvrir le projet
 - Une fois le projet ouvert, essayer de compiler le de main.kt (src/main/kotlin/main.kt)
 - Une fois la compilation réussi, vous pouvez créer vos script dans "src/main/resources", vous aurez l'autocompletion de disponible


## Base d'un script 
Vous pouvez mettre autant d'étape que vous le souhaitez grace au mot clé "step". Vous ne pouvez avoir qu'un script. Les nom de steps doivent être uniques. 

```
import domain.models.script.*

script {
    step("Nom de mon etape") {
        wait((1000L .. 3000L).random())
    }
}
```
Pour voir la liste des méthodes, faites un ```this.``` dans un step
<img width="694" alt="image" src="https://github.com/user-attachments/assets/727a887c-8148-4131-92d5-2d4ea23b4322">


Une autre alternative est d'aller voir le fichier situé dans [src/main/kotlin/domain/handlers/ScriptContext.kt](src/main/kotlin/domain/handlers/ScriptContext.kt)


## Connexion à un provider 
Pour cela il faut bien entendu avoir un serveur d'accéssible. Le lien vers ce serveur
<img width="370" alt="image" src="https://github.com/user-attachments/assets/b6653110-512b-4859-b8a6-182916da08df">
<img width="503" alt="image" src="https://github.com/user-attachments/assets/0acbefc1-ac4f-4f9e-b72b-95d8dd3cd111">
*Vous pouvez vous assurer que le serveur est bien accessible grace au bouton de test.*


Au niveau du script, il suffit d'ajouter le nom du provider: ```script(provider="Nom du provider")```
Vous pouvez ensuite utiliser la méthode ```provider("Nom de la colonne")```pour lire une données.


Les noms de provider existants sont visible sur le serveur
<img width="1247" alt="image" src="https://github.com/user-attachments/assets/fee210ce-40ae-468f-9138-0b8859e5b6a8">

Par exemple, vous un script qui ecrit le "nocommande" dans le premier champs de saisi disponible sur le telephone
```
script(provider = "TestProvider") {
    step("Lecture du provider") {
        val numCommande = provider("nocommande")
        text(numCommande) {
            it.className == "android.widget.EditText"
        }
    }
}
```


## Liste des méthodes annexes
Vous pouvez, en plus de step, ajoutez les méthodes suivantes : 
  - onStart -> Executé à chaque démarrage de script
  - onSuccess -> Executé en cas de script qui c'est terminé en succès
  - onFailure -> Executé en cas de script en erreur
  - onCancel -> Executé quand le script est annulé (manuellement)
  - onFinish -> Executé quand le script est terminé, peut importe l'état


## Tester le script
Pour cela, il suffit de l'ouvrir dans l'application Deploy. 
<img width="868" alt="image" src="https://github.com/user-attachments/assets/a2d7a054-fc3e-4f63-8414-48affc04ef59">

Deux cas possibles : 
  - Le chargement c'est bien effectué : parfait, vous pouvez tester
  - Il y a une erreur, vous pouvez l'afficher pour savoir laquelle et à quelle ligne
    <img width="652" alt="image" src="https://github.com/user-attachments/assets/05059660-2d76-46ca-9e80-6aa50d874c14">

En cas de modification du script, pensez à le recharger !
<img width="233" alt="image" src="https://github.com/user-attachments/assets/5f83a679-81b8-4439-b26d-109d1d85dbcd">


## Utilisation des modules
Vous pouvez centraliser du code commun à tous vos scripts dans des modules. Il suffit de faire des fichiers .kts qui contiennent les fonctions que vous souhaitez. 
Si les fonctions veulent utiliser les méthodes de scripts, il faut les faires sous ce format : 
```
suspend fun ScriptContext.NomDeMaFonction() {
  // Contenu de la fonction, qui peut appeler des methodes de scripts
}

// Un exemple de fonction qui n'utilise pas les méthodes de scripts
fun getSum(nb1: Int, nb2: Int): Int {
    return nb1 + nb2
}
```

Tous les modules doivent être regroupé **dans un même dossier !**. Le chemin de ce dossier doit ensuite être configuré dans le client deploy : 
<img width="370" alt="image" src="https://github.com/user-attachments/assets/b6653110-512b-4859-b8a6-182916da08df">
<img width="637" alt="image" src="https://github.com/user-attachments/assets/3d9d8891-3517-4ac7-a448-b712edd644b9">


## Aide pour le dev
Dans l'écran principal, au niveau de la liste des terminaux connectés : 
<img width="717" alt="image" src="https://github.com/user-attachments/assets/18139f37-d59d-4422-9c9d-6e02ab8a38d2">

Vous pouvez cliquez sur l'icone <img width="45" alt="image" src="https://github.com/user-attachments/assets/42db4f62-4d7e-4754-808c-6e80faa30dc5"> pour passer en mode dev. 
<img width="1501" alt="image" src="https://github.com/user-attachments/assets/d10e9dcf-557a-47de-a299-941f15dbb22f">
Sur cette ecran, vous pouvez voir la liste des elements graphiques detectables et à quoi ils correspondent sur l'écran. La liste des propriétés qui caracteriste l'élément sont visible dans la partie inférrieures de l'écran. Tous ces éléments sont accessibles dans les scripts. 



