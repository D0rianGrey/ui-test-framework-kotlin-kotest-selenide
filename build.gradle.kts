plugins {
    kotlin("jvm") version "1.9.22"
    id("io.kotest") version "0.4.10"
}

group = "com.framework"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Kotlin стандартная библиотека
    implementation(kotlin("stdlib"))

    // Kotest - фреймворк для тестирования
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.kotest:kotest-framework-engine:5.8.0") // Добавлено

    // Selenide - для UI-тестирования
    implementation("com.codeborne:selenide:6.19.1")

    // WebDriverManager для управления драйверами браузеров
    implementation("io.github.bonigarcia:webdrivermanager:5.6.2")

    // Логирование
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.14")
}

tasks.register<Test>("loginSuccessTests") {
    useJUnitPlatform {
        includeTags("LoginSuccess")
    }
    description = "Runs login success tests"
    group = "verification"
}

// Задача для запуска тестов неудачного логина
tasks.register<Test>("loginFailureTests") {
    useJUnitPlatform {
        includeTags("LoginFailure")
    }
    description = "Runs login failure tests"
    group = "verification"
}

tasks.test {
    useJUnitPlatform()

    systemProperty("kotest.tags", System.getProperty("kotest.tags"))
    systemProperty("kotest.filter.tags", System.getProperty("kotest.filter.tags"))

    // Вывод информации о тестах
    testLogging {
        events("passed", "skipped", "failed", "standardOut", "standardError")
        showExceptions = true
        showStackTraces = true
        showCauses = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }

    // Тесты должны продолжаться даже если один падает
    failFast = false

    // Принудительно запустить все тесты даже если не изменились
    outputs.upToDateWhen { false }
}

kotlin {
    jvmToolchain(17)
}