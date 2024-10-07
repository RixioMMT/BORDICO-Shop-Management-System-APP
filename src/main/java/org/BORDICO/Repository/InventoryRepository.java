package org.BORDICO.Repository;

import org.BORDICO.Model.Entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    boolean existsByItemNameAndItemColorType(String itemName, String itemColorType);
    @Query("SELECT COUNT(i) FROM Inventory i WHERE i.itemName = :itemName AND i.itemColorType = :itemColorType AND i.isSold = false")
    Integer countAvailableItemsByItemNameAndItemColorType(@Param("itemName") String itemName, @Param("itemColorType") String itemColorType);
}