# Notice

The four production classes first appear in the MIT-licensed BlueMap Chipped
Add-on at commit `ea72b4335dc9cabe1800212ed11fc76a510ed55c`. Their frozen
release snapshot comes from commit
`c474a82b6bfd1b4173d119cb1e053a5458167e4b`.
The module changes their package declarations. It also gives `CubeFace` a
consumer-neutral Javadoc sentence. No executable behavior changes.

[`provenance/origins.json`](provenance/origins.json) records the immutable
origin paths, source hashes, transformations, and the four matching portfolio
consumers. Frozen Chipped sources under `src/test/java/io/github/janguenter/bluemap/chipped/model`
act as byte-exact test oracles. They are absent from production and sources
archives.
