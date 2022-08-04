package com.nhn.commerce.controller

import com.nhn.commerce.service.ProductService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.slf4j.LoggerFactory

@Controller
class ProductController(
    private val productService: ProductService,
) {
    private val log = LoggerFactory.getLogger(javaClass)
    @GetMapping("/product")
    fun getProductList(model: Model): String {
        log.error("LogNCrash Error Test")
        model.addAttribute("productList", productService.findProductList())
        return "product"
    }


    // TODO (상품 상세 조회 기능 + Exception 처리)
    @GetMapping("/product/{productNo}")
    fun getProductPage(model: Model, @PathVariable("productNo") productNo:Int): String {
        model.addAttribute("product", productService.findProductById(productNo))
        return "product_detail"
    }

    // TODO (상품 추가 기능)
    @GetMapping("/product/add")
    fun addProductPage():String {
        return "add"
    }

    @PostMapping("/product/add")
    fun addProduct(productName: String, salePrice: Int): String{
        // 확장함수
        fun Int.isPositive(): Boolean = this > 0
        fun Int.isNotPositive(): Boolean = !isPositive()

        // 양수가 아닌 salePrice 예외처리
        if(salePrice.isNotPositive()){
            throw Exception("가격을 양수로 입력해주세요.")
        }

        productService.createProduct(productName, salePrice)
        return "redirect:/product"
    }

    // TODO (상품 수정 기능 + Exception 처리)
    @GetMapping("/product/update/{productNo}")
    fun updateProductPage(model: Model, @PathVariable("productNo") productNo:Int):String{
        model.addAttribute("product", productService.findProductById(productNo))
        return "update"
    }

    @PostMapping("/product/update")
    fun updateProduct(productNo:Int, productName: String, salePrice: Int):String{
        productService.updateProduct(productNo, productName, salePrice)
        return "redirect:/product"
    }

    // TODO (상품 삭제 기능 + Exception 처리)
    @GetMapping("/product/delete")
    fun deleteProduct(productNo:Int):String{
        productService.deleteProduct(productNo)
        return "redirect:/product"
    }
}
