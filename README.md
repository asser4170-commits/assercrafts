# AsserCraft (Java)

AsserCraft is a Java voxel sandbox prototype targeting Minecraft Java 1.0-inspired systems.

## Included in this scaffold

- 20 TPS fixed tick loop
- Seeded chunk streaming world generation
- Data-driven registries for blocks, items, entities
- Inventory (36 slots, 9-slot hotbar)
- Crafting, furnace smelting, brewing, and simplified enchanting
- Entity AI FSM (idle/wander/chase/attack) and spawn rules
- Dimension flow (Overworld/Nether/End) with Ender Dragon spawn
- Simple text UI abstractions (main menu, HUD, inventory)
- Block placement/breaking API with hardness/drop integration
- Item-drop entities with pickup physics
- Basic fluid spread simulation and skylight estimation
- Difficulty-aware hostile spawning and combat damage
- Simple physics pass for player-ground collision stabilization
- Executable crafting workflow via inventory + recipe matching
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
=======
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-9heqsw
- Block placement/breaking API with hardness/drop integration
- Item-drop entities with pickup physics
- Basic fluid spread simulation and skylight estimation
=======
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main
 >>>>>>> main

## Run (Maven)

```bash
mvn test
mvn -q exec:java -Dexec.mainClass=com.assercraft.engine.Game
```

## Run (no Maven dependency resolution)

```bash
javac --release 17 -d out $(find src/main/java -name '*.java')
java -cp out:src/main/resources com.assercraft.engine.Game
```
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
=======
 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-iqjpk1
 >>>>>>> main


## Web prototype (playable in browser)

```bash
cd web
python3 -m http.server 8080
# open http://localhost:8080
```

 <<<<<<< codex/implement-core-systems-for-assercraft-1.0-rhft67
Features: movement (WASD), break/place blocks with mouse, hotbar (1-9), day/night cycle, hostile mobs/combat, and dimension toggle (F).


## AsserCraft Classic 1.0 (LWJGL) — Phase 1 status

Implemented foundation:
- GLFW window creation (OpenGL 3.3 Core)
- Mouse capture / cursor lock
- 20 TPS fixed update loop + uncapped render loop
- FPS counter in window title
- Basic Vec3 / Mat4 math classes
- Basic shader system and placeholder shader files

Entry point:
```bash
mvn -q exec:java -Dexec.mainClass=com.assercraft.classic.engine.ClassicMain
```
=======
Features: movement (WASD), break/place blocks with mouse, hotbar (1-9), day/night cycle, and dimension toggle (F).
=======
 >>>>>>> main
 >>>>>>> main
