import domain.models.script.*


script {

    step("Ceci est mon etape 1") {
        wait((1000L .. 3000L).random())
    }
    step("Celle ci la deuxieme") {
        launchComponent("com.android.settings", ".Settings", true)
        wait((1000L .. 3000L).random())
    }
    step("On arrive à la troisemme") {
        key(AndroidKey.APP_SWITCH)
        wait((1000L .. 3000L).random())
    }
    step("Et celle de fin") {
        key(AndroidKey.HOME)
        wait((1000L .. 3000L).random())
    }

}