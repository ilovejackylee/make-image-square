package io.github.ilovejackylee.makeimagesquare;

/** How the source image is placed on the square canvas. */
public enum SquareMode {
    /** Entire image visible; empty bands become padding. */
    FIT,
    /** Image covers the square; caller is expected to crop overflow. */
    FILL
}
