package com.nhn.commerce.repository

import com.nhn.commerce.model.Product
import org.apache.ibatis.annotations.*
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime

@Mapper
interface ProductRepository {
    @Select("SELECT * FROM product")
    fun findProductList(): List<Product>

    @Select("SELECT * FROM product WHERE product_no=#{productNo}")
    fun findProductById(productNo: Int): Product

    @Insert("INSERT INTO product(product_name, sale_price, register_ymdt) VALUES (#{productName},#{salePrice}, #{registerYmdt})")
    fun createProduct(productName: String = "product name", salePrice:Int = 0, registerYmdt: LocalDateTime)

    @Update("UPDATE product SET product_name=#{productName}, sale_price=#{salePrice}, update_ymdt=#{updateYmdt} WHERE product_no=#{productNo}")
    fun updateProduct(productNo:Int, productName: String = "product name", salePrice:Int = 0, updateYmdt: LocalDateTime)

    @Delete("DELETE FROM product WHERE product_no=#{productNo}")
    fun deleteProduct(productNo:Int)
}
