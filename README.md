# BlueMap Athena resource models

This Java 21 source module contains the exact five-role Athena CTM selector,
connection bit order, and cube-face UV basis shared by four BlueMap add-ons.
Version `0.1.0-alpha.1` has four public types in
`io.github.janguenter.bluemap.resource.athena.model`:

- `CtmTextureRole` names the five installed texture roles;
- `CtmSelector` selects one role for a connection quadrant;
- `CtmConnections` maps the exact unsigned eight-bit connection mask to four
  quadrants; and
- `CubeFace` defines the face-local basis used to sample neighbors and place
  UVs.

The module has no BlueMap, Minecraft, NeoForge, Athena, or other production
dependency. It is neither a BlueMap add-on nor a NeoForge mod. Its archives
contain no descriptor, entrypoint, service registration, `module-info`, mod
metadata, or nested JAR.

## Consumer model

BlueMap gives add-ons separate classloaders and no dependable installed
library version contract. Pin this repository at an exact commit, then compile
its `src/main/java` directory into each consumer's production JAR. Do not copy
`bluemap-athena-resource-models-*.jar` to the BlueMap packs directory and do
not nest it inside a consumer.

For a consumer that pins the module at
`modules/bluemap-athena-resource-models`, the source-set wiring is:

```groovy
sourceSets {
    main.java.srcDir 'modules/bluemap-athena-resource-models/src/main/java'
}
```

The consumer must verify its gitlink and checkout before compilation. Its JAR
audit must admit exactly one copy of the five resulting class entries,
including `CubeFace$Vec`, and reject a nested module JAR.

## Build

Use Java 21 and Gradle 9.4.0 or 9.6.1. The repository has no wrapper because
CI checks both versions.

```bash
gradle --no-daemon clean check verifyPublication
```

`check` runs the frozen-origin transformation checks, exhaustive differential
tests, contract tests, Checkstyle, and exact archive inventories.
`verifyPublication` also checks that the POM and Gradle module metadata have
the expected identity and no production dependency. Release builds use
Gradle 9.6.1 because its version appears in Gradle module metadata.

## Deliberate exclusions

Athena quad emission, BlueMap host lighting, top-only rendering, culling,
giant-texture dimensions, installed-resource admission, artifact profiles,
allowlists, and fallback stay in the consumers. Those behaviors are not one
exact four-consumer contract.
