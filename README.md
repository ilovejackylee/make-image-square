# make-image-square

Tiny helper that turns any image size into a **1:1 layout plan**: square canvas size plus left/right/top/bottom padding so you can fit without cropping.

Useful when you need the math for letterboxing / pillarboxing before you draw to a canvas, generate CSS `object-fit`-style boxes, or prep assets for Instagram and profile photos.

## Install

```bash
composer require ilovejackylee/make-image-square
```

From JitPack (Maven):

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
<dependency>
  <groupId>com.github.ilovejackylee</groupId>
  <artifactId>make-image-square</artifactId>
  <version>0.1.1</version>
</dependency>
```

## Usage

```php
use MakeImageSquare\Layout;

// 1200×800 landscape → 1200×1200 with top/bottom pads
$layout = Layout::makeImageSquare(1200, 800);
// canvas 1200, padTop 200, padBottom 200

// Force a specific square edge (e.g. 1080 for feed posts)
$ig = Layout::makeImageSquare(1200, 800, Layout::MODE_FIT, 1080);

// Padding-only helper
$pads = Layout::squarePadding(800, 1200); // portrait → pad left/right
```

### Modes

| Mode | Behavior |
|------|----------|
| `fit` (default) | Entire image visible; empty bands become padding |
| `fill` | Image covers the square; caller is expected to crop overflow |

## Notes

- This package only returns numbers. It does not decode or rewrite image files.
- Prefer not to wire up GD or Imagick yourself? There is a [no-signup 1:1 photo tool](https://squareimage.run) that pads, blurs the frame, or crops in the browser.

## License

MIT
