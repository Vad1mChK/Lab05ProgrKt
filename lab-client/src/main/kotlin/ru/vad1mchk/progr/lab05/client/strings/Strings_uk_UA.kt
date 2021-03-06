package ru.vad1mchk.progr.lab05.client.strings

object Strings_uk_UA : Strings() {
    override val content: Array<Array<String>> = arrayOf(
        arrayOf("applicationName", "Менеджер космічних десантників"),
        arrayOf("loginFormWelcomeLabel", "Ласкаво просимо!"),
        arrayOf("loginFormUsernameLabel", "Введіть ім'я користувача:"),
        arrayOf("loginFormPasswordLabel", "Введіть пароль:"),
        arrayOf("loginFormLoginButton", "Увійти"),
        arrayOf("loginFormRegisterButton", "Реєстрація"),
        arrayOf("loginFormLanguageLabel", "Оберіть мову:"),
        arrayOf("mainApplicationMapLabel", "Карта"),
        arrayOf("mainApplicationTableLabel", "Таблиця"),
        arrayOf("mainApplicationSettingsLabel", "Настройка"),
        arrayOf("mainApplicationAboutLabel", "Про програму"),
        arrayOf("mainApplicationCreateCommand", "Утворити"),
        arrayOf("mainApplicationUpdateCommand", "Оновити"),
        arrayOf("mainApplicationDeleteCommand", "Видалити"),
        arrayOf("mainApplicationClearCommand", "Очистивши"),
        arrayOf("mainApplicationFilterCommand", "Фільтрувати"),
        arrayOf("mainApplicationInfoCommand", "Загальна інформація"),
        arrayOf("propertyNameName", "Ім'я"),
        arrayOf("propertyNameCoordinates", "Координата"),
        arrayOf("propertyNameCoordinateX", "Коорд. X"),
        arrayOf("propertyNameCoordinateY", "Коорд. Y"),
        arrayOf("propertyNameCreationDate", "Дата створення"),
        arrayOf("propertyNameHealth", "Здоров'я"),
        arrayOf("propertyNameHeartCount", "Число сердець"),
        arrayOf("propertyNameLoyal", "Лояльний"),
        arrayOf("propertyNameMeleeWeapon", "Зброя ближнього бою"),
        arrayOf("propertyNameChapterName", "Ім'я глави"),
        arrayOf("propertyNameChapterParentLegion", "Батьківський легіон"),
        arrayOf("propertyNameChapterMarinesCount", "Число десантників"),
        arrayOf("propertyNameCreator", "Творець"),
        arrayOf("propertyValueTrue", "Так"),
        arrayOf("propertyValueFalse", "Ні"),
        arrayOf("propertyValueNull", "?"),
        arrayOf("propertyValueMeleeWeaponPowerSword", "Силовий меч"),
        arrayOf("propertyValueMeleeWeaponChainSword", "Ланцюговий меч"),
        arrayOf("propertyValueMeleeWeaponChainAxe", "Ланцюгова сокира"),
        arrayOf("infoLabel", "Інформація"),
        arrayOf("infoText", """
            Тип колекції:
            Зв'язний список ({0})
            
            Тип елементів:
            Космічні десантники ({1})
            
            {2,number,integer} з {3,number,integer} належать вам, {4}.
            
            Дата ініціалізації: {5, date, full}
        """.trimIndent()),
        arrayOf("mainApplicationSettingsLanguage", "Мова програми:"),
        arrayOf("mainApplicationSettingsUser", "Поточний користувач:"),
        arrayOf("mainApplicationSettingsUserInfo", "Показати інформацію"),
        arrayOf("mainApplicationSettingsDemonstration", "Демонстрація:"),
        arrayOf("mainApplicationSettingsFormats", """
            Формат дати: {0, date, full}
            Формат чисел: {1, number}
            Формат повідомлення: Сьогодні, {2, date, full}, користувач {3} створив {4, number, integer} {5}.
        """.trimIndent()),
        arrayOf("mainApplicationSettingsFormatSpaceMarineSingular", "космічного десантника"),
        arrayOf("mainApplicationSettingsFormatSpaceMarinePlural", "космічних десантників"),
        arrayOf("mainApplicationAboutText", """
            Менеджер Космічних десантників, версія {0}
            Додаток створено в рамках {1, number, integer}-ї лабораторної роботи з дисципліни «{2}».
            Реалізація виконана на мові програмування {3} {4}
            Версія JDK: {5}
            Версія JavaFX: {6}
            
            Автори програми:
            * {7}
            * {8}
        """.trimIndent()),
        arrayOf("disciplineName", "Програмування"),
        arrayOf("authorOne", "Вадим Костянтинович Чайкін"),
        arrayOf("authorTwo", "Данило Андрійович Горінов"),
        arrayOf("mainApplicationAddIfMinCommand", "Додати, якщо менше"),
        arrayOf("mainApplicationRemoveGreaterCommand", "Видалити великих"),
        arrayOf("mainApplicationHistoryCommand", "Історія дій"),
        arrayOf("mainApplicationPrintFieldDescendingHealthCommand", "Здоров'я в порядку убування"),
        arrayOf("mainApplicationExecuteScriptCommand", "Виконати скрипт"),
        arrayOf("userInformationLabel", "Інформація про Користувача"),
        arrayOf("userInformationText", """
            Колір користувача: {0}.
            Користувачеві {1} належить {2, number, integer} {3, choice}.
        """.trimIndent()),
        arrayOf("userInformationSpaceMarinesOne", "космодесантник"),
        arrayOf("userInformationSpaceMarinesFew", "космодесантника"),
        arrayOf("userInformationSpaceMarinesMany", "космодесантників"),
        arrayOf("userInformationServer", "сервер"),
        arrayOf("userInformationTextServer", """
            Колір користувача: {0}.
            Серверу належить {2, number, integer} {3, choice}.
        """.trimIndent()),
        arrayOf("collectionInformationLabel", "Інформація"),
        arrayOf("collectionInformationText", """
            Тип колекції: зв'язний список ({0}).
            Тип елементів: космічні десантники ({1}).
            {2, number, integer} / {3, number, integer} елементів колекції належать вам, {4}.
            Дата ініціалізації: {5, date, full}.
        """.trimIndent()),
        arrayOf("spaceMarineInformationWithNullChapter", """
            Творець: {0}
            Координати: x: {1, number, integer} y: {2, number}
            Дата створення: {3}
            Здоров'я: {4, number}
            Число сердець: {5, number, integer}
            Лояльний: {6, choice}
            Зброя: {7, choice}
            Глава:?
        """.trimIndent()),
        arrayOf("spaceMarineInformationWithNonNullChapter", """
            Творець: {0}
            Координати: x: {1, number, integer} y: {2, number}
            Дата створення: {3}
            Здоров'я: {4, number}
            Число сердець: {5, number, integer}
            Лояльний: {6, choice}
            Зброя: {7, choice}
            Назва глави: {8}
            Батьківський легіон: {9}
            Число Десантників: {10, number, integer}
        """.trimIndent()),
        arrayOf("spaceMarineModifierLabelCreate", "Створення"),
        arrayOf("spaceMarineModifierLabelUpdate", "Оновлення #{0}"),
        arrayOf("spaceMarineModifierSubmit", "Відправивши")
    )
}