package ca.rbon.iostream;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class CodeFlowErrorTest {

    @Test
    public void testString() {
        Assertions.assertThat(new CodeFlowError("popp").getMessage())
                .isEqualTo(
                        "popp This should error should have been prevented by the fluent IoStream builder constraints."
                                +
                                " Please create report this issue at https://github.com/fralalonde/iostream/issues");
    }

    @Test(expected = RuntimeException.class)
    public void testRuntime() {
        throw new CodeFlowError("popp");
    }

}
