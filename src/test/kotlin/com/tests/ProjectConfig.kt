package com.tests

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.descriptors.Descriptor
import io.kotest.core.filter.SpecFilter
import io.kotest.core.filter.TestFilter
import io.kotest.core.filter.SpecFilterResult
import io.kotest.core.filter.TestFilterResult
import kotlin.reflect.KClass

class ProjectConfig : AbstractProjectConfig() {

    val specFilter = object : SpecFilter {
        override fun filter(kclass: KClass<*>): SpecFilterResult {
            println("Обнаружен класс тестов: ${kclass.simpleName}")
            return SpecFilterResult.Include
        }
    }

    val testCaseFilter = object : TestFilter {
        override fun filter(descriptor: Descriptor): TestFilterResult {
            // В новых версиях Kotest структура Descriptor может отличаться
            println("Обнаружен тест: ${descriptor.id.value}")
            // Доступ к тегам зависит от версии Kotest
            // В новых версиях может быть descriptor.tags.names
            return TestFilterResult.Include
        }
    }
}