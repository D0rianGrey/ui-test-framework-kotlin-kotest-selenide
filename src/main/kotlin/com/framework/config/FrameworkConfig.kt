package com.framework.config

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
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

        // Загрузка настроек из конфигурации окружения
        val baseUrl = EnvironmentConfig.getBaseUrl()
        val browserType = EnvironmentConfig.getBrowserType()
        val browserSize = EnvironmentConfig.getBrowserSize()
        val timeout = EnvironmentConfig.getTimeout()
        val headless = EnvironmentConfig.isHeadless()

        logger.info("Параметры запуска: browser=$browserType, headless=$headless, baseUrl=$baseUrl")

        // Настройка драйвера
        when (browserType.lowercase()) {
            "chrome" -> {
                WebDriverManager.chromedriver().setup()
                val options = ChromeOptions()
                options.addArguments("--no-sandbox")
                options.addArguments("--disable-dev-shm-usage")
                if (headless) {
                    options.addArguments("--headless")
                }
                Configuration.browserCapabilities = options
            }
            "firefox" -> {
                WebDriverManager.firefoxdriver().setup()
                val options = FirefoxOptions()
                if (headless) {
                    options.addArguments("-headless")
                }
                Configuration.browserCapabilities = options
            }
            else -> {
                logger.warn("Неизвестный тип браузера: $browserType, используется Chrome")
                WebDriverManager.chromedriver().setup()
            }
        }

        // Основные настройки Selenide
        Configuration.browser = browserType
        Configuration.baseUrl = baseUrl
        Configuration.browserSize = browserSize
        Configuration.timeout = timeout
        Configuration.pageLoadTimeout = 30000
        Configuration.screenshots = true
        Configuration.savePageSource = false

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