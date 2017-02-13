package ca.rbon.iostream;

/**
 * <p>CodeFlowError class.</p>
 *
 * @author fralalonde
 * @version $Id: $Id
 */
public class CodeFlowError extends RuntimeException {

    static final String FLUENT_SHOULD_PREVENT =
            "%s This should error should have been prevented by the fluent IoStream builder constraints." +
                    " Please create report this issue at https://github.com/fralalonde/iostream/issues";
        
    /**
     * <p>Constructor for CodeFlowError.</p>
     *
     * @param message a {@link java.lang.String} object.
     */
    public CodeFlowError(String message) {
        super(String.format(FLUENT_SHOULD_PREVENT, message));
    }
    
    /**
     * <p>Constructor for CodeFlowError.</p>
     *
     * @param format a {@link java.lang.String} format template.
     * @param arg an object to format 
     */
    public CodeFlowError(String format, Object arg) {
        super(String.format(FLUENT_SHOULD_PREVENT, String.format(format, arg)));
    }
    

}
