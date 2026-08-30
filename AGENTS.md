# Agent guide for BlueMap Athena resource models

Read this file, `README.md`, `docs/ARCHITECTURE.md`, and
`provenance/origins.json` before changing production code.

## Scope

Version `0.1.0-alpha.1` contains only `CtmTextureRole`, `CtmSelector`,
`CtmConnections`, and `CubeFace` in
`io.github.janguenter.bluemap.resource.athena.model`. Do not add emitters,
lighting, giant-texture selection, resource discovery, profiles, activation,
BlueMap adapters, mod behavior, entrypoints, or installed shared-library
behavior.

Consumers compile this repository's production source into their own add-on
JAR. They do not install or load the standalone module JAR on a server.

## Origin contract

The four production files first appear at BlueMap Chipped Add-on commit
`ea72b4335dc9cabe1800212ed11fc76a510ed55c`. The frozen release snapshot is
commit `c474a82b6bfd1b4173d119cb1e053a5458167e4b`. Production changes only the
package declarations and the consumer-specific `CubeFace` Javadoc.
`verifyOriginSources` checks the frozen source bytes and each declared
transformation from `provenance/origins.json`.

Do not change behavior during extraction. A later behavior change needs a new
version, focused tests, a consumer review, and new integration evidence.

## Required gates

Use the shared lock so Gradle builds remain serial:

```bash
flock /tmp/bluemap-gradle.lock \
  gradle-9.4.0 --no-daemon clean check verifyPublication
flock /tmp/bluemap-gradle.lock \
  gradle-9.6.1 --no-daemon clean check verifyPublication
```

Before a release, build the four publication files twice with Gradle 9.6.1
and compare every byte. Inspect the JAR and sources JAR. Run
`actionlint .github/workflows/*.yml` after workflow changes.

Never commit build output, credentials, generated release files, consumer
JARs, or pack evidence. A release needs a reviewed version commit and an
annotated `v<module_version>` tag.
