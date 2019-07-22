
package com.shannan.nakollaol.domain.functional

import com.shannan.nakollaol.UnitTest
import com.shannan.nakollaol.domain.functional.Either.Left
import com.shannan.nakollaol.domain.functional.Either.Right
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldEqualTo
import org.junit.Test
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
class EitherTest : UnitTest() {

    @Test fun `Either Right should return correct type`() {
        val result = Right("ironman")

        result shouldBeInstanceOf Either::class.java
        result.isRight shouldBe true
        result.isLeft shouldBe false
        result.either({},
                { right ->
                    right shouldBeInstanceOf String::class.java
                    right shouldEqualTo "ironman"
                })
    }

    @Test
    fun `Either Left should return correct type`() {
        val result = Left("ironman")

        result shouldBeInstanceOf Either::class.java
        result.isLeft shouldBe true
        result.isRight shouldBe false
        result.either(
                { left ->
                    left shouldBeInstanceOf String::class.java
                    left shouldEqualTo "ironman"
                }, {})
    }
}