package com.mlcorrea.stackeruser

import io.mockk.MockKAnnotations
import org.junit.rules.TestRule

/**
 * Created by manuel on 27/07/19
 */
class InjectMocksRule {

    companion object {
        fun create(testClass: Any) = TestRule { statement, _ ->
            MockKAnnotations.init(testClass)
            statement
        }
    }
}
