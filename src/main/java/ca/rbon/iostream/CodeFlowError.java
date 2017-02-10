package ca.rbon.iostream;

public class CodeFlowError extends RuntimeException {

    static final String FLUENT_SHOULD_PREVENT =
            "%s This should error should have been prevented by the fluent IoStream builder constraints." +
                    " Please create report this issue at https://github.com/fralalonde/iostream/issues";
        
    public CodeFlowError(String message) {
        super(String.format(FLUENT_SHOULD_PREVENT, message));
    }
    
}
