package com.tests.base

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.open
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SimpleTest : FunSpec({
    test("должен открыть сайт SauceDemo") {
        // Открываем сайт SauceDemo
        open("https://www.saucedemo.com/")
        
        // Проверяем, что в заголовке страницы присутствует слово "Swag Labs"
        Selenide.title() shouldBe "Swag Labs"
    }
})