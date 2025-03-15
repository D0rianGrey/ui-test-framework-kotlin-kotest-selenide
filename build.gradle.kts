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
    
    // Selenide - для UI-тестирования
    implementation("com.codeborne:selenide:6.19.1")
    
    // WebDriverManager для управления драйверами браузеров
    implementation("io.github.bonigarcia:webdrivermanager:5.6.2")
    
    // Логирование
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.4.14")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}