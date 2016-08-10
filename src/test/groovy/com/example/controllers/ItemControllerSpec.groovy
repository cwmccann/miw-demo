package com.example.controllers

import com.example.models.Item
import com.example.util.rest.BusinessException
import com.example.util.rest.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.AuthenticationException
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Tests for Item Controller
 */
@ContextConfiguration
@SpringBootTest
class ItemControllerSpec extends Specification {

    @Autowired
    ItemController controller

    def "Controller Context Load"() {
        expect:
        controller != null
    }

    def "List all items"() {
        when: "Get some items"
        def items = controller.get()

        then: "Make sure we have some items"
        items.size() > 0
    }

    def "Get an item"() {
        when: "Get some items"
        def items = controller.get()
        def anItem = items.first()
        def anItemFromController = controller.getOne(anItem.id)

        then: "Make sure we have the same item"
        anItem.id == anItemFromController.id
        anItem.name == anItemFromController.name
        anItem.price == anItemFromController.price
    }

    def "Get a nonexistent item"() {
        when: "Get some items"
        controller.getOne(-99)

        then: "Make sure an exception was thrown"
        thrown ResourceNotFoundException
    }

    @WithMockUser
    def "Buy an item"() {
        when: "Get some items and buy one that still has stock left"
        def items = controller.get()
        def anItem = items.find{ i -> i.quantity > 0} as Item
        def boughtItem = controller.buy(anItem.id)

        then: "Make sure we have bought the item"
        anItem.id == boughtItem.id
        anItem.name == boughtItem.name
        anItem.price == boughtItem.price
        anItem.quantity == boughtItem.quantity + 1
    }

    def "Buy an item without security"() {
        when: "buy one without any security applied"
        controller.buy(1)

        then: "Make sure an authentication exception was thrown"
        thrown AuthenticationException
    }

    @WithMockUser
    def "Try to buy an out of stock item"() {
        when: "Get some items and buy one that doesn't have any stock left"
        def items = controller.get()
        def anItem = items.find{ i -> i.quantity == 0} as Item
        controller.buy(anItem.id)

        then: "Make sure we didn't buy it"
        thrown BusinessException
    }
}
