# Pilot contract

The proposed first consumers are Chipped, Chisel, and CobbleFurnies. Factory
Blocks stays on its released implementation as the combined-test control.
No consumer migration is part of this repository build.

## Frozen baselines

| Consumer | Current main | Released source origin | Released add-on JAR SHA-256 |
| --- | --- | --- | --- |
| Chipped | `26df095af693d36c75b4d82738736157be3d1f9d` | `c474a82b6bfd1b4173d119cb1e053a5458167e4b` | `b43c238b764e068db4009ab16fc2af140b54d84feaf37bd6577602e1dc97fd21` |
| Chisel | `6553da70621f6039db5f0fb961c2843a1c36988d` | `f9131a5143062e2045cf26823aabb8628bb5d94d` | `053e048f9332094571b25b2edc5ddb9a172e1f89c0a65c2f7ceb05e4a946510e` |
| CobbleFurnies | `90ca220d0fa4d4dfd258fd54b14b8b2606a0975d` | `eea5407dbbd162cbe4dd8fc5bc247f6617cf5d98` | `2c9df027e4cd1b4f56856dcb05a65499b6ed1df3f8592e9d662ad59e477564a3` |
| Factory Blocks control | `667c601c92357b7e757aaae78393cbe45f531456` | `ad9ee2bcf0e2886ee88931f1eaf50ccb4b8a03bd` | `69f4f53022aac455a4bcc362dc09cbaf5b3f73cf108ccc154dffa8e238869302` |

All four profiles require ATMons 1.2.0, Minecraft 1.21.1, NeoForge 21.1.248,
Java 21, BlueMap commit `9be321df995a1103808621d529eb72773e719d4d`,
and exact Athena 4.0.6 JAR SHA-256
`43699885bbce3343916d4c5c4940cf0e3f9f6f02fdeb46e8655e121b42282ec5`.

## Consumer acceptance

Each consumer needs a reviewed pull request and a new version because the
package relocation changes its JAR and sources JAR. The migration may remove
only the four local model sources, add the pinned module source directory, and
change necessary imports. Emitters, profiles, validators, renderers, gallery
fixtures, and failure policies must stay unchanged.

The consumer gate must reject an uninitialized, dirty, staged, or wrong module
checkout. Archive comparison must account for every changed entry and prove
all unaffected entries byte-identical. The final JAR must contain the five
shared class entries once and no nested module.

After isolated tests pass, run the complete ATMons 1.2.0 combined suite twice
with all add-ons installed. Require all 51 activation markers, every gallery
assertion, no duplicate-class or linkage failure, and an unchanged Factory
Blocks control artifact.
