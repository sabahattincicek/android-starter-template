package com.template.androidstarter.core.common.result

import app.cash.turbine.test
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Unit tests for [asResource].
 */
class ResourceTest {

    /** A successful flow emits Loading first, then one Success per value. */
    @Test
    fun asResource_emitsLoadingThenSuccess() = runTest {
        flowOf(1, 2).asResource().test {
            assertEquals(Resource.Loading, awaitItem())
            assertEquals(Resource.Success(1), awaitItem())
            assertEquals(Resource.Success(2), awaitItem())
            awaitComplete()
        }
    }

    /** An upstream exception ends the flow with an Error that carries the cause. */
    @Test
    fun asResource_emitsErrorOnException() = runTest {
        val exception = IllegalStateException("boom")
        flow<Int> { throw exception }.asResource().test {
            assertEquals(Resource.Loading, awaitItem())
            val error = awaitItem()
            assertTrue(error is Resource.Error)
            error as Resource.Error
            assertEquals("boom", error.message)
            assertSame(exception, error.cause)
            awaitComplete()
        }
    }
}
