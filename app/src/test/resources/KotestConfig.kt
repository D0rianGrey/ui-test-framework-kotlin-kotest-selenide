package com.tests.base

import com.framework.config.FrameworkConfig
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.spec.IsolationMode

object KotestConfig : AbstractProjectConfig() {
    override val isolationMode = IsolationMode.InstancePerLeaf
    
    override suspend fun beforeProject() {
        println("=== ЗАПУСК ТЕСТОВ НАЧАЛСЯ ===")
        FrameworkConfig.setup()
    }
    
    override suspend fun afterProject() {
        println("=== ЗАВЕРШЕНИЕ ТЕСТОВ ===")
        FrameworkConfig.tearDown()
    }
}