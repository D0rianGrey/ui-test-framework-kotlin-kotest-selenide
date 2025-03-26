package com.framework.utils

import org.slf4j.LoggerFactory
import java.util.Properties
import java.io.FileInputStream

/**
 * Менеджер тестовых данных
 */
object TestDataManager {
    private val logger = LoggerFactory.getLogger(TestDataManager::class.java)
    private val properties = Properties()

    init {
        try {
            val testDataFile = "src/test/resources/testdata.properties"
            properties.load(FileInputStream(testDataFile))
            logger.info("Тестовые данные загружены из $testDataFile")
        } catch (e: Exception) {
            logger.warn("Не удалось загрузить файл тестовых данных: ${e.message}")
        }
    }

    /**
     * Получение значения свойства по ключу
     *
     * @param key Ключ свойства
     * @param defaultValue Значение по умолчанию, если свойство не найдено
     * @return Значение свойства
     */
    fun getProperty(key: String, defaultValue: String = ""): String {
        val value = properties.getProperty(key, defaultValue)
        logger.debug("Получено свойство: $key = $value")
        return value
    }

    /**
     * Получение пользовательских данных для тестов
     */
    fun getStandardUser(): Pair<String, String> {
        return Pair(
            getProperty("user.standard.username", "standard_user"),
            getProperty("user.standard.password", "secret_sauce")
        )
    }

    /**
     * Получение заблокированного пользователя для тестов
     */
    fun getLockedUser(): Pair<String, String> {
        return Pair(
            getProperty("user.locked.username", "locked_out_user"),
            getProperty("user.locked.password", "secret_sauce")
        )
    }
}