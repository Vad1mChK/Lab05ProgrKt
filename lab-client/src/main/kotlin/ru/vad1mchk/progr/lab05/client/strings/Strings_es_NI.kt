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
        arrayOf("propertyNameCoordinateX", "Coord. X"),
        arrayOf("propertyNameCoordinateY", "Coord. Y"),
        arrayOf("propertyNameCreationDate", "Fecha de creación"),
        arrayOf("propertyNameHealth", "Salud"),
        arrayOf("propertyNameHeartCount", "Número de corazones"),
        arrayOf("propertyNameLoyal", "Leal"),
        arrayOf("propertyNameMeleeWeapon", "Armas cuerpo a cuerpo"),
        arrayOf("propertyNameChapterName", "Nombre del capítulo"),
        arrayOf("propertyNameChapterParentLegion", "Legión parental"),
        arrayOf("propertyNameChapterMarinesCount", "Número de Marines"),
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
        arrayOf("mainApplicationSettingsLanguage", "Idioma de la aplicación:"),
        arrayOf("mainApplicationSettingsUser", "Usuario actual:"),
        arrayOf("mainApplicationSettingsUserInfo", "Mostrar información"),
        arrayOf("mainApplicationSettingsDemonstration", "Demostración:"),
        arrayOf("mainApplicationSettingsFormats", """
            Formato de fecha: {0, date, full}
            Formato de números: {1, number}
            Formato del mensaje: hoy, {2, date, full}, el usuario {3} creó {4, number, integer} {5, choice}.
        """.trimIndent()),
        arrayOf("mainApplicationSettingsFormatSpaceMarineSingular", "paracaidista espacial"),
        arrayOf("mainApplicationSettingsFormatSpaceMarinePlural", "paracaidistas espaciales"),
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
        arrayOf("disciplineName", "Programación"),
        arrayOf("authorOne", "Vadim Konstantinovich Chaikin"),
        arrayOf("authorTwo", "Daniil Andreevich Gorinov"),
        arrayOf("mainApplicationAddIfMinCommand", "Añadir si menos"),
        arrayOf("mainApplicationRemoveGreaterCommand", "Eliminar grandes"),
        arrayOf("mainApplicationHistoryCommand", "Historia de acción"),
        arrayOf("mainApplicationPrintFieldDescendingHealthCommand", "Salud en orden decreciente"),
        arrayOf("mainApplicationExecuteScriptCommand", "Ejecutar secuencia de comandos"),
        arrayOf("userInformationText", """
            Color del usuario: {0}.
            El usuario {1} posee {2, number, integer} {3, choice}.
        """.trimIndent()),
        arrayOf("userInformationSpaceMarinesOne", "astronauta"),
        arrayOf("userInformationSpaceMarinesFew", "astronautas"),
        arrayOf("userInformationSpaceMarinesMany", "astronautas"),
        arrayOf("userInformationServer", "servidor"),
        arrayOf("userInformationTextServer", """
            Color del usuario: {0}.
            El servidor posee {2, number, integer} {3, choice}.
           """.trimIndent()),
        arrayOf("collectionInformationLabel", "Información"),
        arrayOf("collectionInformationText", """
            Tipo de colección: lista enlazada ({0}).
            Tipo de elemento: paracaidistas espaciales ({1}).
            {2, number, integer} / {3, number, integer} elementos de la colección pertenecen a usted, {4}.
            Fecha de inicialización: {5, date, full}.
        """.trimIndent()),
        arrayOf("spaceMarineInformationWithNullChapter", """
            Creador: {0}
            Coordenadas: x: {1, number, integer} y: {2, number}
            Fecha de creación: {3}
            Salud: {4, number}
            Número de corazones: {5, number, integer}
            Leal: {6, choice}
            Armas: {7, choice}
            Capítulo:?
        """.trimIndent()),
        arrayOf("spaceMarineInformationWithNonNullChapter", """
            Creador: {0}
            Coordenadas: x: {1, number, integer} y: {2, number}
            Fecha de creación: {3}
            Salud: {4, number}
            Número de corazones: {5, number, integer}
            Leal: {6, choice}
            Armas: {7, choice}
            Nombre del capítulo: {8}
            Legión parental: {9}
            Número de paracaidistas: {10, number, integer}
        """.trimIndent()),
        arrayOf("spaceMarineModifierLabelCreate", "Creación"),
        arrayOf("spaceMarineModifierLabelUpdate", "Actualización #{0}"),
        arrayOf("spaceMarineModifierSubmit", "Enviar")
    )
}