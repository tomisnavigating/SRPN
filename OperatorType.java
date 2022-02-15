public enum OperatorType {

    /*
     * This is an enum which contains the available mathematical operator types and
     * their associated method names.
     * It is intended to be used when constructing a CommandOperator so that only
     * valid method names can be passed in.
     */

    ADD("add"),
    SUBTRACT("subtract"),
    MULTIPLY("multiply"),
    DIVIDE("divide"),
    MODULUS("mod"),
    POWER("pow");

    public final String name;

    OperatorType(String name) {
        this.name = name;
    }
}
