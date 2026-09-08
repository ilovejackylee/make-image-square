package io.github.ilovejackylee.makeimagesquare;

/** Square canvas plus content placement. */
public final class SquareLayout {
    public final double canvas;
    public final double contentWidth;
    public final double contentHeight;
    public final double offsetX;
    public final double offsetY;
    public final double padLeft;
    public final double padRight;
    public final double padTop;
    public final double padBottom;

    public SquareLayout(
            double canvas,
            double contentWidth,
            double contentHeight,
            double offsetX,
            double offsetY,
            double padLeft,
            double padRight,
            double padTop,
            double padBottom) {
        this.canvas = canvas;
        this.contentWidth = contentWidth;
        this.contentHeight = contentHeight;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.padLeft = padLeft;
        this.padRight = padRight;
        this.padTop = padTop;
        this.padBottom = padBottom;
    }
}
