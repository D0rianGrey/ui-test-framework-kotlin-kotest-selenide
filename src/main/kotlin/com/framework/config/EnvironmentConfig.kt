package com.framework.config

import org.slf4j.LoggerFactory
import java.util.Properties
import java.io.FileInputStream

/**
 * Класс для настройки параметров окружения
 */
object EnvironmentConfig {
    private val logger = LoggerFactory.getLogger(EnvironmentConfig::class.java)
    private val properties = Properties()

    init {
        try {
            // Сначала загружаем стандартные настройки
            val defaultEnvFile = "src/test/resources/environment.properties"
            properties.load(FileInputStream(defaultEnvFile))

            // Затем пытаемся загрузить настройки для конкретного окружения
            val env = System.getProperty("env", "local")
            val envFile = "src/test/resources/environment.$env.properties"
            try {
                properties.load(FileInputStream(envFile))
                logger.info("Загружены настройки окружения из $envFile")
            } catch (e: Exception) {
                logger.info("Файл настроек для окружения $env не найден, используются стандартные настройки")
            }
        } catch (e: Exception) {
            logger.warn("Не удалось загрузить файл настроек окружения: ${e.message}")
        }
    }

    /**
     * Получение значения свойства по ключу
     */
    fun getProperty(key: String, defaultValue: String = ""): String {
        // Сначала проверяем системные свойства (приоритет)
        val systemValue = System.getProperty(key)
        if (systemValue != null) {
            return systemValue
        }

        // Затем проверяем свойства из файла
        return properties.getProperty(key, defaultValue)
    }

    /**
     * Получение URL базового сайта
     */
    fun getBaseUrl(): String {
        return getProperty("baseUrl", "https://www.saucedemo.com")
    }

    /**
     * Проверка, запускать ли браузер в режиме headless
     */
    fun isHeadless(): Boolean {
        return getProperty("headless", "false").toBoolean()
    }

    /**
     * Получение размера окна браузера
     */
    fun getBrowserSize(): String {
        return getProperty("browserSize", "1920x1080")
    }

    /**
     * Получение типа браузера
     */
    fun getBrowserType(): String {
        return getProperty("browser", "chrome")
    }

    /**
     * Получение таймаута для Selenide
     */
    fun getTimeout(): Long {
        return getProperty("timeout", "10000").toLong()
    }
}