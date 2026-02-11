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
