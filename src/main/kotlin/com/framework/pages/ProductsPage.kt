package com.framework.pages

import com.codeborne.selenide.CollectionCondition
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.title
import org.openqa.selenium.By

/**
 * Page Object для страницы продуктов SauceDemo
 */
class ProductsPage : BasePage() {
    override val url: String = "/inventory.html"
    override val pageName: String = "Страница продуктов SauceDemo"

    // Локаторы элементов страницы
    private val productsTitle = `$`(".title")
    private val productItems = `$$`(".inventory_item")
    private val sortDropdown = `$`("[data-test='product_sort_container']")
    private val shoppingCartBadge = `$`(".shopping_cart_badge")
    private val shoppingCartLink = `$`(".shopping_cart_link")

    /**
     * Проверка, что страница продуктов открыта
     */
    override fun isPageOpened(): Boolean {
        return productsTitle.isDisplayed && productsTitle.has(text("Products")) &&
                title() == "Swag Labs"
    }

    /**
     * Получение списка всех продуктов
     */
    fun getProducts(): ElementsCollection {
        return productItems.shouldBe(CollectionCondition.sizeGreaterThan(0))
    }

    /**
     * Добавление продукта в корзину по его имени
     */
    fun addProductToCart(productName: String): ProductsPage {
        logger.info("Добавление продукта в корзину: $productName")

        productItems.find(text(productName))
            .find("[data-test^='add-to-cart']")
            .shouldBe(visible)
            .click()

        return this
    }

    /**
     * Сортировка продуктов
     */
    fun sortProductsBy(sortOption: String): ProductsPage {
        logger.info("Сортировка продуктов по: $sortOption")
        sortDropdown.selectOption(sortOption)
        return this
    }

    /**
     * Получение количества товаров в корзине
     */
    fun getCartItemsCount(): Int {
        return if (shoppingCartBadge.exists()) {
            shoppingCartBadge.text().toInt()
        } else {
            0
        }
    }

    /**
     * Переход в корзину
     */
    fun goToCart(): CartPage {
        logger.info("Переход в корзину")
        shoppingCartLink.click()
        return CartPage()
    }
}