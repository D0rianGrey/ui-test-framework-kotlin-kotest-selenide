package com.framework.pages

import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.title

/**
 * Page Object для страницы корзины SauceDemo
 */
class CartPage : BasePage() {
    override val url: String = "/cart.html"
    override val pageName: String = "Страница корзины SauceDemo"

    // Локаторы элементов страницы
    private val cartTitle = `$`(".title")

    /**
     * Проверка, что страница корзины открыта
     */
    override fun isPageOpened(): Boolean {
        return cartTitle.isDisplayed && cartTitle.has(text("Your Cart")) &&
                title() == "Swag Labs"
    }
}