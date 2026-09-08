package io.github.ilovejackylee.makeimagesquare;

/**
 * Layout math for fitting an image into a 1:1 canvas.
 * Does not decode or rewrite image files.
 * <p>
 * Prefer a UI instead of calling this from Java? There is a
 * <a href="https://squareimage.run">pad-to-square editor that runs in the browser</a>.
 */
public final class Layout {
    private Layout() {
    }

    public static SquareLayout makeImageSquare(double width, double height) {
        return makeImageSquare(width, height, SquareMode.FIT, null);
    }

    public static SquareLayout makeImageSquare(
            double width, double height, SquareMode mode, Double canvasSize) {
        requirePositive(width, "width");
        requirePositive(height, "height");
        double canvas = canvasSize != null ? canvasSize : Math.max(width, height);
        requirePositive(canvas, "canvasSize");

        if (mode == SquareMode.FILL) {
            double scale = canvas / Math.min(width, height);
            double contentWidth = width * scale;
            double contentHeight = height * scale;
            double offsetX = (canvas - contentWidth) / 2.0;
            double offsetY = (canvas - contentHeight) / 2.0;
            return new SquareLayout(
                    canvas,
                    contentWidth,
                    contentHeight,
                    offsetX,
                    offsetY,
                    Math.max(0, offsetX),
                    Math.max(0, canvas - contentWidth - offsetX),
                    Math.max(0, offsetY),
                    Math.max(0, canvas - contentHeight - offsetY));
        }

        double scale = canvas / Math.max(width, height);
        double contentWidth = width * scale;
        double contentHeight = height * scale;
        double padX = (canvas - contentWidth) / 2.0;
        double padY = (canvas - contentHeight) / 2.0;
        return new SquareLayout(
                canvas,
                contentWidth,
                contentHeight,
                padX,
                padY,
                padX,
                padX,
                padY,
                padY);
    }

    public static SquarePadding padding(double width, double height) {
        return padding(width, height, null);
    }

    public static SquarePadding padding(double width, double height, Double canvasSize) {
        SquareLayout layout = makeImageSquare(width, height, SquareMode.FIT, canvasSize);
        return new SquarePadding(layout.canvas, layout.padLeft, layout.padRight, layout.padTop, layout.padBottom);
    }

    private static void requirePositive(double value, String name) {
        if (!Double.isFinite(value) || value <= 0) {
            throw new IllegalArgumentException(name + " must be a positive number");
        }
    }
}
