package com.example.shoppinglistproject.data

import com.example.shoppinglistproject.domain.ShopItem
import com.example.shoppinglistproject.domain.ShopListRepository

object ShopListRepositoryImpl:ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementId = 0

    init {
        for (i in 0 until 10){
            val item = ShopItem("Nane $i", i, true)
            addShopItem(item)
        }
    }
    override fun addShopItem(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID) {
            shopItem.id = autoIncrementId++
        }
            shopList.add(shopItem)
        }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
       val oldElement = getShopItem(shopItem.id)
        shopList.remove(shopItem)
        addShopItem(shopItem)
    }

    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }
}