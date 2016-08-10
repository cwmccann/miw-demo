package com.example.controllers

import com.example.models.Item
import com.example.repositories.ItemRepository
import com.example.util.rest.BusinessException
import com.example.util.rest.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.transaction.Transactional

/**
 * Created by chris on 09/08/16.
 */
@RestController
@RequestMapping("/api/items")
class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * Gets a list of all the items the Gilded Rose carries
     * @return
     */
    @RequestMapping()
    Iterable<Item> get() {
        return itemRepository.findAll()
    }

    /**
     * Gets a single item
     * @param itemId
     * @return
     */
    @RequestMapping("{itemId}")
    Item getOne(@PathVariable long itemId) {
        def item = itemRepository.findOneById(itemId)

        if (item == null) throw new ResourceNotFoundException("Item not found")

        return item
    }

    /**
     * Decrements the quantity field and returns the item
     * @param itemId
     * @return
     */
    @Transactional
    @RequestMapping(value ="{itemId}/buy", method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    Item buy(@PathVariable long itemId) {
        def item = itemRepository.findOneById(itemId)
        if (item == null) throw new ResourceNotFoundException("Item not found")

        if (item.quantity > 0) {
            item.quantity = item.quantity - 1
            itemRepository.save(item)
            return item
        } else {
            throw new BusinessException("Item is out of stock")
        }

    }

}
