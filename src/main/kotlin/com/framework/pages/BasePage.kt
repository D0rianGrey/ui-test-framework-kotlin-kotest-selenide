package com.framework.pages

import com.codeborne.selenide.Selenide
import org.slf4j.LoggerFactory

/**
 * Базовый класс для всех Page Objects
 */
abstract class BasePage {
    protected val logger = LoggerFactory.getLogger(this::class.java)

    /**
     * URL страницы (относительный путь)
     */
    abstract val url: String

    /**
     * Имя страницы для логирования
     */
    abstract val pageName: String

    /**
     * Открывает страницу и возвращает экземпляр текущего Page Object
     */
    fun open(): BasePage {
        logger.info("Открытие страницы: $pageName")
        Selenide.open(url)
        return this
    }

    /**
     * Проверяет, открыта ли текущая страница
     */
    abstract fun isPageOpened(): Boolean
}