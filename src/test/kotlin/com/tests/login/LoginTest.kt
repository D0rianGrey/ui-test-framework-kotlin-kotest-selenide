package com.tests.login

import com.framework.config.FrameworkConfig
import com.framework.pages.LoginPage
import io.kotest.core.Tag
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain

// Определение тегов
object LoginSuccess : Tag()
object LoginFailure : Tag()

class LoginTest : FunSpec({

    beforeSpec {
        FrameworkConfig.setup()
    }

    afterSpec {
        FrameworkConfig.tearDown()
    }

    test("successful login with standard user").config(tags = setOf(LoginSuccess)) {
        println("Выполняется тест: успешный логин")
        val loginPage = LoginPage().open() as LoginPage

        loginPage.isPageOpened() shouldBe true

        val productsPage = loginPage.login("standard_user", "secret_sauce")

        productsPage.isPageOpened() shouldBe true
    }

    test("failed login with incorrect password").config(tags = setOf(LoginFailure)) {
        println("Выполняется тест: неудачный логин")
        val loginPage = LoginPage().open() as LoginPage

        loginPage.enterUsername("standard_user")
        loginPage.enterPassword("wrong_password")
        loginPage.clickLogin()

        val errorMessage = loginPage.getErrorMessage()
        errorMessage shouldContain "Username and password do not match"
    }
})