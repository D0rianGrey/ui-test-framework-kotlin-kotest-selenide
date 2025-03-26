package com.framework.pages

import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.title
import org.openqa.selenium.By

/**
 * Page Object для страницы логина SauceDemo
 */
class LoginPage : BasePage() {
    override val url: String = "/"
    override val pageName: String = "Страница логина SauceDemo"

    // Локаторы элементов страницы
    private val usernameField = `$`(By.id("user-name"))
    private val passwordField = `$`(By.id("password"))
    private val loginButton = `$`(By.id("login-button"))
    private val errorMessage = `$`("[data-test='error']")

    /**
     * Проверка, что страница логина открыта
     */
    override fun isPageOpened(): Boolean {
        return usernameField.isDisplayed && passwordField.isDisplayed &&
                loginButton.isDisplayed && title() == "Swag Labs"
    }

    /**
     * Метод для ввода имени пользователя
     */
    fun enterUsername(username: String): LoginPage {
        logger.info("Ввод имени пользователя: $username")
        usernameField.shouldBe(visible).setValue(username)
        return this
    }

    /**
     * Метод для ввода пароля
     */
    fun enterPassword(password: String): LoginPage {
        logger.info("Ввод пароля")
        passwordField.shouldBe(visible).setValue(password)
        return this
    }

    /**
     * Метод для нажатия на кнопку логина
     */
    fun clickLogin(): ProductsPage {
        logger.info("Нажатие на кнопку логина")
        loginButton.shouldBe(visible).click()
        return ProductsPage()
    }

    /**
     * Комплексный метод для выполнения логина
     */
    fun login(username: String, password: String): ProductsPage {
        enterUsername(username)
        enterPassword(password)
        return clickLogin()
    }

    /**
     * Получение текста ошибки, если она отображается
     */
    fun getErrorMessage(): String {
        return errorMessage.shouldBe(visible).text()
    }
}