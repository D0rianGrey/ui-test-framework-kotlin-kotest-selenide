package com.framework.utils

import com.codeborne.selenide.Screenshots
import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import org.openqa.selenium.OutputType
import org.slf4j.LoggerFactory
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Утилиты для создания скриншотов
 */
object ScreenshotUtils {
    private val logger = LoggerFactory.getLogger(ScreenshotUtils::class.java)
    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")

    /**
     * Директория для сохранения скриншотов
     */
    private val screenshotsDir = "build/screenshots"

    init {
        // Создаем директорию для скриншотов при инициализации класса
        Files.createDirectories(Paths.get(screenshotsDir))
    }

    /**
     * Создает скриншот текущего состояния страницы
     *
     * @param name Базовое имя файла скриншота
     * @return Путь к созданному файлу скриншота
     */
    fun takeScreenshot(name: String): String {
        logger.info("Создание скриншота: $name")

        if (!WebDriverRunner.hasWebDriverStarted()) {
            logger.warn("WebDriver не запущен, скриншот не может быть создан")
            return ""
        }

        val timestamp = LocalDateTime.now().format(dateTimeFormatter)
        val fileName = "${name}_$timestamp.png"
        val filePath = "$screenshotsDir/$fileName"

        try {
            val screenshot = Selenide.screenshot(OutputType.FILE)
            screenshot?.copyTo(File(filePath), overwrite = true)
            logger.info("Скриншот сохранен: $filePath")
            return filePath
        } catch (e: Exception) {
            logger.error("Ошибка при создании скриншота: ${e.message}")
            return ""
        }
    }

    /**
     * Создает скриншот при ошибке теста
     *
     * @param testName Имя теста
     * @return Путь к созданному файлу скриншота
     */
    fun takeErrorScreenshot(testName: String): String {
        return takeScreenshot("error_${testName.replace(" ", "_")}")
    }
}