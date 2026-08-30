# Architecture

## Packaging

Each BlueMap add-on compiles the shared Java sources into its own production
JAR. A consumer pins this repository as source, usually through a Git
submodule. This keeps class identity inside the consumer's BlueMap classloader
and avoids an installed library whose version BlueMap cannot constrain.

The standalone JAR, sources JAR, POM, and Gradle module metadata exist for
review and distribution. Server administrators do not install the standalone
JAR. The POM and module metadata declare no dependency.

## Pure model contract

`CtmTextureRole` contains the exact wire names `particle`, `empty`, `center`,
`vertical`, and `horizontal`. `CtmSelector` implements Athena 4.0.6's complete
three-boolean quadrant truth table.

`CtmConnections` fixes the bit order to up, down, left, right, then the four
diagonals. It accepts only masks from 0 through 255 and returns quadrants in
top-left, top-right, bottom-left, bottom-right order.

`CubeFace` contains the six world normals and face-local right/up bases. It
uses JDK records and integers only. None of these types reads a resource,
block, profile, renderer, or runtime service.

## Deferred behavior

The two current Athena emitter cohorts expose different test seams for
top-only rendering and culling. They also call `FaceLighting`, which serves
both Athena and the non-Athena CobbleFurnies statue emitter. Version 0.1 does
not move either class.

A later emitter extraction must keep its BlueMap 5.22 dependency explicit and
take host lighting through a tested adapter. It must not pull resource
admission, fallback, or consumer registration into this module.

The 2x2 and 3x3 giant selectors also stay local. Turning their dimensions into
parameters would create behavior that none of the current exact copies has.
