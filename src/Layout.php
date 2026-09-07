<?php

declare(strict_types=1);

namespace MakeImageSquare;

final class Layout
{
    public const MODE_FIT = 'fit';
    public const MODE_FILL = 'fill';

    /**
     * @return array{
     *   canvas: float,
     *   contentWidth: float,
     *   contentHeight: float,
     *   offsetX: float,
     *   offsetY: float,
     *   padLeft: float,
     *   padRight: float,
     *   padTop: float,
     *   padBottom: float
     * }
     */
    public static function makeImageSquare(
        float $width,
        float $height,
        string $mode = self::MODE_FIT,
        ?float $canvasSize = null
    ): array {
        if (!is_finite($width) || !is_finite($height) || $width <= 0.0 || $height <= 0.0) {
            throw new \InvalidArgumentException('width and height must be positive numbers');
        }

        $canvas = $canvasSize !== null ? $canvasSize : max($width, $height);
        if (!is_finite($canvas) || $canvas <= 0.0) {
            throw new \InvalidArgumentException('canvas size must be a positive number');
        }

        if ($mode === self::MODE_FILL) {
            $scale = $canvas / min($width, $height);
            $contentWidth = $width * $scale;
            $contentHeight = $height * $scale;
            $offsetX = ($canvas - $contentWidth) / 2.0;
            $offsetY = ($canvas - $contentHeight) / 2.0;

            return [
                'canvas' => $canvas,
                'contentWidth' => $contentWidth,
                'contentHeight' => $contentHeight,
                'offsetX' => $offsetX,
                'offsetY' => $offsetY,
                'padLeft' => max(0.0, $offsetX),
                'padRight' => max(0.0, $canvas - $contentWidth - $offsetX),
                'padTop' => max(0.0, $offsetY),
                'padBottom' => max(0.0, $canvas - $contentHeight - $offsetY),
            ];
        }

        if ($mode !== self::MODE_FIT) {
            throw new \InvalidArgumentException("mode must be 'fit' or 'fill'");
        }

        $scale = $canvas / max($width, $height);
        $contentWidth = $width * $scale;
        $contentHeight = $height * $scale;
        $padX = ($canvas - $contentWidth) / 2.0;
        $padY = ($canvas - $contentHeight) / 2.0;

        return [
            'canvas' => $canvas,
            'contentWidth' => $contentWidth,
            'contentHeight' => $contentHeight,
            'offsetX' => $padX,
            'offsetY' => $padY,
            'padLeft' => $padX,
            'padRight' => $padX,
            'padTop' => $padY,
            'padBottom' => $padY,
        ];
    }

    /**
     * @return array{canvas: float, left: float, right: float, top: float, bottom: float}
     */
    public static function squarePadding(float $width, float $height, ?float $canvasSize = null): array
    {
        $layout = self::makeImageSquare($width, $height, self::MODE_FIT, $canvasSize);

        return [
            'canvas' => $layout['canvas'],
            'left' => $layout['padLeft'],
            'right' => $layout['padRight'],
            'top' => $layout['padTop'],
            'bottom' => $layout['padBottom'],
        ];
    }
}
