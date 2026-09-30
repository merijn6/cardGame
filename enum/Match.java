enum Match {
    VALUE(4),
    FUNCTION(3),
    SHAPE(2),
    COLOR(1),
    NOT(0);

    private final Integer matchInt;

    Match(Integer matchInt) {
        this.matchInt = matchInt;
    }

    public Integer getMatchInt() {
        return this.matchInt;
    }
}


