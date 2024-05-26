package com.example.westpactechnicalassessment

import com.example.westpactechnicalassessment.adapter.RestApiRemote
import com.example.westpactechnicalassessment.domain.card.CardEngine
import com.example.westpactechnicalassessment.domain.msg.MessageManager
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CardEngineTest {
    private val engine = CardEngine(MessageManager(RestApiRemote()))

    @Test
    fun testGetCards() {
        runBlocking {
            val size = 20
            val result = engine.getCreditCards(size)
            Assert.assertNotNull(result) //Assume API always available
            Assert.assertEquals(size, result!!.size)
        }
    }
}