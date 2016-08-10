package com.example.repositories

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification


/**
 * Tests for Item Repo
 *
 * depends on the default seed data
 */
@ContextConfiguration
@SpringBootTest
class ItemRepositorySpec extends Specification {

    @Autowired
    ItemRepository repo

    def "Repo Context Load"() {
        expect:
        repo != null
    }

    def "List all items"() {
        when: "Get some items"
        def items = repo.findAll()

        then: "Make sure we have some items"
        items.size() > 0
    }

    def "Get an item"() {
        when: "Get an item"

        def item = repo.findOneById(1);

        then: "Make sure we have one"
        item  != null
    }

    def "Get a nonexistent item"() {
        when: "Get an item"

        def item = repo.findOneById(-100);

        then: "Make sure we don't have one"
        item == null
    }


}
