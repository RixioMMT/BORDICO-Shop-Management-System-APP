package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductInventoryRepository extends JpaRepository<ProductInventory, Long> {
    boolean existsByItemNameAndItemColorType(String itemName, String itemColorType);
    @Query("SELECT COUNT(i) FROM ProductInventory i WHERE i.itemName = :itemName AND i.itemColorType = :itemColorType AND i.isSold = false")
    Integer countAvailableItemsByItemNameAndItemColorType(@Param("itemName") String itemName, @Param("itemColorType") String itemColorType);
}