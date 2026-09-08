package io.github.ilovejackylee.makeimagesquare;

/** Padding edges only (fit mode). */
public final class SquarePadding {
    public final double canvas;
    public final double left;
    public final double right;
    public final double top;
    public final double bottom;

    public SquarePadding(double canvas, double left, double right, double top, double bottom) {
        this.canvas = canvas;
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }
}
