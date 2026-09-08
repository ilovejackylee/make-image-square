package io.github.ilovejackylee.makeimagesquare;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LayoutTest {
    @Test
    void landscapeFitLetterboxesTopAndBottom() {
        SquareLayout layout = Layout.makeImageSquare(1200, 800);
        assertEquals(1200, layout.canvas, 1e-9);
        assertEquals(200, layout.padTop, 1e-9);
        assertEquals(200, layout.padBottom, 1e-9);
        assertEquals(0, layout.padLeft, 1e-9);
    }

    @Test
    void portraitPaddingHelper() {
        SquarePadding pads = Layout.padding(800, 1200);
        assertEquals(1200, pads.canvas, 1e-9);
        assertEquals(200, pads.left, 1e-9);
        assertEquals(200, pads.right, 1e-9);
    }

    @Test
    void rejectsNonPositiveSize() {
        assertThrows(IllegalArgumentException.class, () -> Layout.makeImageSquare(0, 100));
    }
}
