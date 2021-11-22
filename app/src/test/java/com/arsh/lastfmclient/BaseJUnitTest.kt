package com.arsh.lastfmclient

import org.junit.After
import org.junit.Before

abstract class BaseJUnitTest {

    abstract fun start()
    abstract fun stop()

    @Before
    fun setup() {
        InstantTaskExecutorRule.start()
        start()
    }

    @After
    fun tearDown() {
        stop()
        InstantTaskExecutorRule.tearDown()
    }
}