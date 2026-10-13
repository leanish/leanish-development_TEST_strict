package example.strict;

import org.apache.commons.lang3.StringUtils;

/** Compiles against the declared dependencies; the cases live in the build files. */
public final class Fixture {
    private Fixture() {
    }

    public static String label(String value) {
        return StringUtils.capitalize(value);
    }
}
