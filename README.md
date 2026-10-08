# Grimoire of Gaia 4 — Fabric 1.21.1

Fabric port of **Grimoire of Gaia 4 6.0.0-alpha.9** for Minecraft 1.21.1.

## Build

Requirements:

- Minecraft 1.21.1
- Java 21
- Gradle 8.12 or a compatible Gradle installation

Run:

```text
gradle build
```

The build pulls the upstream Gaia **1.21** source at a pinned revision, removes the NeoForge-only bootstrap, and applies the Fabric/Porting Lib compatibility layer.

## Included porting work

- Fabric mod entrypoints
- Porting Lib registry layer
- Entity attributes and spawn placements
- Client entity renderers and model layers
- Gaia configuration through Porting Lib Config
- Gaia friendly-state persistence without NeoForge Attachments
- Summon Staff persistent state without NeoForge persistent-data access
- Living drops and effect-cure hooks
- Optional Trinkets 3.10.0 support for Gaia accessories
- Common material tags used by Gaia
- Original Gaia resource/data files copied into the generated build

## Optional dependencies

**Trinkets 3.10.0** is optional. When installed, Gaia's Curios-style accessory items are registered as Trinkets.

**Patchouli** remains optional and is detected through Fabric Loader where the upstream code needs it.

## Important

This repository builds from the upstream source revision rather than redistributing the upstream artwork as a copied source tree. Gaia's code is published under CC0 according to the upstream project, while its art/assets remain under the original creator's rights.
