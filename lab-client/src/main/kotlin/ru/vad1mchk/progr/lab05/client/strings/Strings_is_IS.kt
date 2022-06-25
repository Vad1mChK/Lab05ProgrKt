package ru.vad1mchk.progr.lab05.client.strings

object Strings_is_IS : Strings() {
    override val content: Array<Array<String>> = arrayOf(
        arrayOf("applicationName", "Geimfarastjóri"),
        arrayOf("loginFormWelcomeLabel", "Velkominn!"),
        arrayOf("loginFormUsernameLabel", "Sláðu inn notandanafn:"),
        arrayOf("loginFormPasswordLabel", "Sláðu inn lykilorð:"),
        arrayOf("loginFormLoginButton", "Skrá inn"),
        arrayOf("loginFormRegisterButton", "Skráðu þig"),
        arrayOf("loginFormLanguageLabel", "Veldu tungumál:"),
        arrayOf("mainApplicationMapLabel", "Kort"),
        arrayOf("mainApplicationTableLabel", "Tafla"),
        arrayOf("mainApplicationSettingsLabel", "Stillingar"),
        arrayOf("mainApplicationAboutLabel", "Um vefinn"),
        arrayOf("mainApplicationCreateCommand", "Að búa til"),
        arrayOf("mainApplicationUpdateCommand", "Uppfæra"),
        arrayOf("mainApplicationDeleteCommand", "Fjarlægja"),
        arrayOf("mainApplicationClearCommand", "Hreinsa"),
        arrayOf("mainApplicationFilterCommand", "Sía"),
        arrayOf("mainApplicationInfoCommand", "Almennar upplýsingar"),
        arrayOf("propertyNameName", "Nafn"),
        arrayOf("propertyNameCoordinates", "Saðsetning"),
        arrayOf("propertyNameCoordinateX", "Saðs. X"),
        arrayOf("propertyNameCoordinateY", "Saðs. Y"),
        arrayOf("propertyNameCreationDate", "Dagsetning stofnunar"),
        arrayOf("propertyNameHealth", "Heilsa"),
        arrayOf("propertyNameHeartCount", "Hjartafjöldi"),
        arrayOf("propertyNameLoyal", "Tryggur"),
        arrayOf("propertyNameMeleeWeapon", "Melee vopn"),
        arrayOf("propertyNameChapterName", "Kafli nafn"),
        arrayOf("propertyNameChapterParentLegion", "Parent legion"),
        arrayOf("propertyNameChapterMarinesCount", "Fjöldi landgönguliða"),
        arrayOf("propertyNameCreator", "Höfundur"),
        arrayOf("propertyValueTrue", "Já"),
        arrayOf("propertyValueFalse", "Nei"),
        arrayOf("propertyValueNull", "?"),
        arrayOf("propertyValueMeleeWeaponPowerSword", "Máttur Sverð"),
        arrayOf("propertyValueMeleeWeaponChainSword", "Tsjernenko"),
        arrayOf("propertyValueMeleeWeaponChainAxe", "Keðja Axe"),
        arrayOf("infoLabel", "Upplýsingar"),
        arrayOf("infoText", """
            Tegund safns:
            Tengd listi ({0})
            
            Tegund staka:
            Landgönguliðar ({1})
            
            {2,number,integer} frá {3,number,integer} þín, {4}.
            
            Frumstillingardagur: {5, date, full}
        """.trimIndent()),
        arrayOf("mainApplicationSettingsLanguage", "Umsókn tungumál:"),
        arrayOf("mainApplicationSettingsUser", "Núverandi notandi:"),
        arrayOf("mainApplicationSettingsUserInfo", "Sýna upplýsingar"),
        arrayOf("mainApplicationSettingsDemonstration", "Sýning:"),
        arrayOf("mainApplicationSettingsFormats", """
            Dagsetning snið: {0, date, full}
            Númer snið: {1, number}
            Skilaboð snið: í dag, {2, date, full}, notandi {3} búið {4, number, integer} {5}.
        """.trimIndent()),
        arrayOf("mainApplicationSettingsFormatSpaceMarineSingular", "geimskip"),
        arrayOf("mainApplicationSettingsFormatSpaceMarinePlural", "geimfarar"),
        arrayOf("mainApplicationAboutText", """
            Geimferðastjóri, útgáfa {0}
            Umsóknin var búin til sem hluti af {1, number, integer}-th rannsóknarstofu vinna á aga «{2}».
            Framkvæmd er gerð á forritunarmálinu {3} {4}
            JDK útgáfa: {5}
            JavaFX útgáfa: {6}
            
            Höfundar umsóknarinnar:
            * {7}
            * {8}
        """.trimIndent()),
        arrayOf("disciplineName", "Forritun"),
        arrayOf("authorOne", "Vadim Konstantinovich Chaikin"),
        arrayOf("authorTwo", "Daniil Andreevich Gorinov"),
        arrayOf("mainApplicationAddIfMinCommand", "Bæta við ef minna"),
        arrayOf("mainApplicationRemoveGreaterCommand", "Eyða stórum"),
        arrayOf("mainApplicationHistoryCommand", "Saga aðgerða"),
        arrayOf("mainApplicationPrintFieldDescendingHealthCommand", "Heilsa í lækkandi röð"),
        arrayOf("mainApplicationExecuteScriptCommand", "Framkvæma handrit"),
        arrayOf("userInformationText", """
            Notandi litur: {0}.
            Notandi {1} á {2, number, integer} {3, choice}.
        """.trimIndent()),
        arrayOf("userInformationSpaceMarinesOne", "geimskip"),
        arrayOf("userInformationSpaceMarinesFew", "landgönguliðar"),
        arrayOf("userInformationSpaceMarinesMany", "landgönguliðar"),
        arrayOf("userInformationServer", "þjónn"),
        arrayOf("userInformationTextServer", """
            Notandi litur: {0}.
            Félagið á {2, number, integer} {3, choice}.
        """.trimIndent()),
        arrayOf("collectionInformationLabel", "Upplýsingar"),
        arrayOf("collectionInformationText", """
            Safntegund: tengd listi ({0}).
            Þáttur tegund: Rúm Marines ({1}).
            {2, number, integer} / {3, number, integer} safn atriði tilheyra þér, {4}.
            Frumstillingardagur: {5, date, full}.
        """.trimIndent()),
        arrayOf("spaceMarineInformationWithNullChapter", """
            Höfundur: {0}
            Hnit: x: {1, number, integer} y: {2, number}
            Dagsetning stofnunar: {3}
            Heilsa: {4, number}
            Hjartafjöldi: {5, number, integer}
            Trygg: {6, choice}
            Vopn: {7, choice}
            Kafli: ?
        """.trimIndent()),
        arrayOf("spaceMarineInformationWithNonNullChapter", """
            Höfundur: {0}
            Hnit: x: {1, number, integer} y: {2, number}
            Dagsetning stofnunar: {3}
            Heilsa: {4, number}
            Hjartafjöldi: {5, number, integer}
            Trygg: {6, choice}
            Vopn: {7, choice}
            Kafli Nafn: {8}
            Foreldrafélag: {9}
            Fjöldi paratroopers: {10, number, integer}
        """.trimIndent()),
        arrayOf("spaceMarineModifierLabelCreate", "Sköpun"),
        arrayOf("spaceMarineModifierLabelUpdate", "Uppfæra #{0}"),
        arrayOf("spaceMarineModifierSubmit", "Senda")
    )
}