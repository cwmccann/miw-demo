package com.example.config

import com.example.models.Item
import com.example.repositories.ItemRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Seeds the database with default data
 * Could be fancier if needed.
 */
@Service
@Slf4j
class DatabaseSeeder implements InitializingBean {

    @Autowired
    ItemRepository itemRepository

    @Override
    void afterPropertiesSet() throws Exception {
        log.debug "Running Database Seeder"

        itemRepository.save([
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        ])
    }
}




