package com.tests.base

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.Selenide.open
import com.framework.config.FrameworkConfig
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SimpleTest : FunSpec({
    
    beforeSpec {
        println("ЗАПУСК ТЕСТОВ: Инициализация Selenide")
        FrameworkConfig.setup()
    }
    
    afterSpec {
        println("ЗАВЕРШЕНИЕ ТЕСТОВ: Закрытие браузера")
        FrameworkConfig.tearDown()
    }
    
    test("должен открыть сайт SauceDemo") {
        println("ТЕСТ: Открываем сайт SauceDemo")
        open("https://www.saucedemo.com/")
        
        println("ТЕСТ: Проверяем заголовок")
        val pageTitle = Selenide.title()
        println("ТЕСТ: Полученный заголовок: $pageTitle")
        
        pageTitle shouldBe "Swag Labs"
    }
})