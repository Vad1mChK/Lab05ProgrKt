package ru.vad1mchk.progr.lab05.client.strings

object Strings_ru_RU : Strings() {
    override val content: Array<Array<String>> = arrayOf(
        arrayOf("applicationName", "Менеджер космических десантников"),
        arrayOf("loginFormWelcomeLabel", "Добро пожаловать!"),
        arrayOf("loginFormUsernameLabel", "Введите имя пользователя:"),
        arrayOf("loginFormPasswordLabel", "Введите пароль:"),
        arrayOf("loginFormLoginButton", "Войти"),
        arrayOf("loginFormRegisterButton", "Зарегистрироваться"),
        arrayOf("loginFormLanguageLabel", "Выберите язык:"),
        arrayOf("mainApplicationMapLabel", "Карта"),
        arrayOf("mainApplicationTableLabel", "Таблица"),
        arrayOf("mainApplicationSettingsLabel", "Настройки"),
        arrayOf("mainApplicationAboutLabel", "О программе"),
        arrayOf("mainApplicationCreateCommand", "Создать"),
        arrayOf("mainApplicationUpdateCommand", "Обновить"),
        arrayOf("mainApplicationDeleteCommand", "Удалить"),
        arrayOf("mainApplicationClearCommand", "Очистить"),
        arrayOf("mainApplicationFilterCommand", "Фильтровать"),
        arrayOf("mainApplicationInfoCommand", "Общая информация"),
        arrayOf("propertyNameName", "Имя"),
        arrayOf("propertyNameCoordinates", "Координаты"),
        arrayOf("propertyNameCoordinateX", "Коорд. X"),
        arrayOf("propertyNameCoordinateY", "Коорд. Y"),
        arrayOf("propertyNameCreationDate", "Дата создания"),
        arrayOf("propertyNameHealth", "Здоровье"),
        arrayOf("propertyNameHeartCount", "Число сердец"),
        arrayOf("propertyNameLoyal", "Лояльный"),
        arrayOf("propertyNameMeleeWeapon", "Оружие ближнего боя"),
        arrayOf("propertyNameChapterName", "Имя главы"),
        arrayOf("propertyNameChapterParentLegion", "Родительский легион"),
        arrayOf("propertyNameChapterMarinesCount", "Число космодесантников"),
        arrayOf("propertyNameCreator", "Создатель"),
        arrayOf("propertyValueTrue", "Да"),
        arrayOf("propertyValueFalse", "Нет"),
        arrayOf("propertyValueNull", "?"),
        arrayOf("propertyValueMeleeWeaponPowerSword", "Силовой меч"),
        arrayOf("propertyValueMeleeWeaponChainSword", "Цепной меч"),
        arrayOf("propertyValueMeleeWeaponChainAxe", "Цепной топор"),
        arrayOf("infoLabel", "Информация"),
        arrayOf("infoText", """
            Тип коллекции:
            Связный список ({0})
            
            Тип элементов:
            Космические десантники ({1})
            
            {2,number,integer} из {3,number,integer} принадлежат вам, {4}.
            
            Дата инициализации: {5, date, full}
        """.trimIndent()),
        arrayOf("mainApplicationSettingsLanguage", "Язык приложения:"),
        arrayOf("mainApplicationSettingsDemonstration", "Демонстрация:"),
        arrayOf("mainApplicationSettingsFormats", """
            Формат даты: {0, date, full}
            Формат чисел: {1, number}
            Формат сообщения: Сегодня, {2, date, full}, пользователь {3} создал {4, number, integer} {5, choice}.
        """.trimIndent()),
        arrayOf("mainApplicationAboutText", """
            Менеджер космических десантников, версия {0}
            Приложение создано в рамках {1, number, integer}-й лабораторной работы по дисциплине «{2}».
            Реализация выполнена на языке программирования {3} {4}
            Версия JDK: {5}
            Версия JavaFX: {6}
            
            Авторы приложения:
            * {7}
            * {8}
        """.trimIndent()),
        arrayOf("authorOne", "Вадим Константинович Чайкин"),
        arrayOf("authorTwo", "Даниил Андреевич Горинов"),
        arrayOf("mainApplicationAddIfMinCommand", "Добавить, если меньше"),
        arrayOf("mainApplicationRemoveGreaterCommand", "Удалить больших"),
        arrayOf("mainApplicationHistoryCommand", "История действий"),
        arrayOf("mainApplicationPrintFieldDescendingHealthCommand", "Здоровье в убывающем порядке"),
        arrayOf("mainApplicationExecuteScriptCommand", "Выполнить скрипт"),
    )
}