# ForceOP — Fabric 1.21.11

Server-side Fabric mod that adds:

- `/forceop <player>` — immediately grants OP to an online player.
- `/fop <player>` — alias.

## Install

1. Use Minecraft Java Edition **1.21.11**.
2. Install Fabric Loader and Fabric API for 1.21.11.
3. Put the built `forceop-1.0.0.jar` in the server's `mods` folder.
4. Restart the server.

## Important

The command deliberately has **no permission requirement**, so anyone who can run commands on the server can use it. Only install this on a server where that behavior is intended.

## Build from source

Requires Java 21 and Gradle. Fabric's 1.21.11 toolchain uses the remapping Loom plugin for obfuscated releases. The GitHub Actions workflow in this repository builds the mod automatically.

The compiled jar is produced in `build/libs/` and uploaded as a workflow artifact.
