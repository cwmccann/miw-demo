package com.example.repositories

import com.example.models.Item
import org.springframework.data.repository.CrudRepository

/**
 * Standard repo for Items
 */
interface ItemRepository extends CrudRepository<Item, Long> {

    /**
     * Gets a single Item by Id
     * @param id
     * @return
     */
    Item findOneById(Long id)
}
