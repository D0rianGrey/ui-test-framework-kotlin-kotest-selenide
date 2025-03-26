package com.framework.config

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeOptions
import org.slf4j.LoggerFactory

/**
 * Класс для настройки конфигурации фреймворка
 */
object FrameworkConfig {
    private val logger = LoggerFactory.getLogger(FrameworkConfig::class.java)

    /**
     * Инициализация конфигурации Selenide и WebDriver
     */
    fun setup() {
        logger.info("Настройка конфигурации фреймворка")
        
        // Настройка WebDriverManager
        WebDriverManager.chromedriver().setup()
        
        // Настройка Selenide
        Configuration.browser = "chrome"
        Configuration.baseUrl = "https://www.saucedemo.com"
        Configuration.browserSize = "1920x1080"
        Configuration.timeout = 10000 // 10 секунд ожидания по умолчанию
        
        // Настройки для Chrome
        val options = ChromeOptions()
        options.addArguments("--no-sandbox")
        options.addArguments("--disable-dev-shm-usage")
        
        Configuration.browserCapabilities = options
        
        logger.info("Конфигурация фреймворка настроена успешно")
    }

    /**
     * Закрытие всех окон браузера и завершение работы драйвера
     */
    fun tearDown() {
        logger.info("Завершение работы WebDriver")
        Selenide.closeWebDriver()
    }
}