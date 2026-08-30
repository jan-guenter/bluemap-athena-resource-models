# Releasing

Releases require a clean reviewed commit and an annotated tag named exactly
`v<module_version>`.

1. Run `clean check verifyPublication` with Gradle 9.4.0 and 9.6.1 on Java 21.
2. Build with Gradle 9.6.1 twice from clean state and compare the production
   JAR, sources JAR, POM, and module metadata byte for byte.
3. Inspect both archives and confirm the origin and archive-boundary checks
   passed.
4. Confirm the POM and module metadata contain no dependency.
5. Merge the reviewed version commit.
6. Create and push an annotated `v<module_version>` tag at that commit.
7. Let the release workflow build, compare, upload, attest, publish to Maven,
   download for comparison, and publish the draft.

The workflow can resume only against the same immutable annotated tag. A
successful module build does not authorize a consumer update or deployment.
