package ru.vad1mchk.progr.lab05.client.strings

object Strings_es_NI : Strings() {
    override val content: Array<Array<String>> = arrayOf(
        arrayOf("applicationName", "Gerente de marines espaciales"),
        arrayOf("loginFormWelcomeLabel", "¡Bienvenidos!"),
        arrayOf("loginFormUsernameLabel", "Introduzca su nombre de usuario:"),
        arrayOf("loginFormPasswordLabel", "Introducir la contraseña:"),
        arrayOf("loginFormLoginButton", "Iniciar sesión"),
        arrayOf("loginFormRegisterButton", "Registro"),
        arrayOf("loginFormLanguageLabel", "Elige lengua:"),
        arrayOf("mainApplicationMapLabel", "Mapa"),
        arrayOf("mainApplicationTableLabel", "Tabla"),
        arrayOf("mainApplicationSettingsLabel", "Ajustes"),
        arrayOf("mainApplicationAboutLabel", "Acerca del programa"),
        arrayOf("mainApplicationCreateCommand", "Crear"),
        arrayOf("mainApplicationUpdateCommand", "Renovar"),
        arrayOf("mainApplicationDeleteCommand", "Eliminar"),
        arrayOf("mainApplicationClearCommand", "Limpiar"),
        arrayOf("mainApplicationFilterCommand", "Filtrar"),
        arrayOf("mainApplicationInfoCommand", "Información general"),
        arrayOf("propertyNameName", "Nombre"),
        arrayOf("propertyNameCoordinates", "Coordenadas"),
        arrayOf("propertyNameCoordinateX", "Coordenada Y"),
        arrayOf("propertyNameCoordinateY", "Coordenada Y"),
        arrayOf("propertyNameCreationDate", "Fecha de creación"),
        arrayOf("propertyNameHealth", "Salud"),
        arrayOf("propertyNameHeartCount", "Número de corazones"),
        arrayOf("propertyNameLoyal", "Leal"),
        arrayOf("propertyNameMeleeWeapon", "Armas cuerpo a cuerpo"),
        arrayOf("propertyNameChapterName", "Nombre del capítulo"),
        arrayOf("propertyNameChapterParentLegion", "Legión parental"),
        arrayOf("propertyNameChapterMarinesCount", "Número de Marines espaciales"),
        arrayOf("propertyNameCreator", "Creador"),
        arrayOf("propertyValueTrue", "Sí"),
        arrayOf("propertyValueFalse", "No"),
        arrayOf("propertyValueNull", "?"),
        arrayOf("propertyValueMeleeWeaponPowerSword", "Espada de poder"),
        arrayOf("propertyValueMeleeWeaponChainSword", "Espada de cadena"),
        arrayOf("propertyValueMeleeWeaponChainAxe", "Hacha de cadena"),
        arrayOf("infoLabel", "Información"),
        arrayOf("infoText", """
            Tipo de colección:
            Lista enlazada ({0})
            
            Tipo de elementos:
            Paracaidistas espaciales ({1})
            
            {2,number,integer} de {3,number,integer} pertenecen a usted, {4}.
            
            Fecha de inicialización: {5, date, full}
        """.trimIndent()),
        arrayOf("mainApplicationSettingsLanguage", "Idioma de la aplicación"),
        arrayOf("mainApplicationSettingsDemonstration", "Demostración"),
        arrayOf("mainApplicationSettingsFormats", """
            Formato de fecha: {0, date, full}
            Formato de números: {1, number}
            Formato del mensaje: hoy, {2, date, full}, el usuario {3} creó {4, number, integer} {5, choice}.
        """.trimIndent()),
        arrayOf("mainApplicationAboutText", """
            Administrador de paracaidistas espaciales, versión {0}
            La aplicación se creó en el marco de {1, number, integer}- ésimo trabajo de laboratorio en la disciplina «{2}».
            La implementación se realiza en el lenguaje de programación {3} {4}
            Versión JDK: {5}
            Versión De JavaFX: {6}
            
            Autores de la aplicación:
            * {7}
            * {8}
        """.trimIndent()),
        arrayOf("authorOne", "Vadim Konstantinovich Chaikin"),
        arrayOf("authorTwo", "Daniil Andreevich Gorinov"),
        arrayOf("mainApplicationAddIfMinCommand", "Añadir si menos"),
        arrayOf("mainApplicationRemoveGreaterCommand", "Eliminar grandes"),
        arrayOf("mainApplicationHistoryCommand", "Historia de acción"),
        arrayOf("mainApplicationPrintFieldDescendingHealthCommand", "Salud en orden decreciente"),
    )
}