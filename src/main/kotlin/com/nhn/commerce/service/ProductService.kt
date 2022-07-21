package com.nhn.commerce.service

import com.nhn.commerce.model.Product
import com.nhn.commerce.repository.ProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun findProductList(): List<Product> = productRepository.findProductList()

    // 상품 상세 조회
    fun findProductById(productNo : Int): Product = productRepository.findProductById(productNo)

    // 상품 추가
    fun createProduct(productName: String, salePrice: Int) = productRepository.createProduct(productName, salePrice, registerYmdt = LocalDateTime.now())

    // 상품 수정
    fun updateProduct(productNo: Int, productName: String, salePrice: Int) = productRepository.updateProduct(productNo, productName, salePrice, updateYmdt = LocalDateTime.now())

    // 상품 삭제
    fun deleteProduct(productNo: Int) = productRepository.deleteProduct(productNo)
}
