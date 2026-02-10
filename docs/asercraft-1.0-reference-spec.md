# AsserCraft 1.0 Reference Spec

This document captures the baseline feature set for a **Java voxel sandbox game** inspired by the Minecraft Java Edition **1.0.0 era** (Nov 18, 2011), adapted as implementation guidance for AsserCraft.

## 1.0-era context

Minecraft Java 1.0.0 introduced major systems including:

- Brewing
- Enchanting
- The End dimension
- Villager NPCs
- Ender Dragon boss encounter

## Blocks that must exist

These include both legacy (Alpha/Beta-era) and 1.0-era blocks.

### Core 1.0-era additions and key blocks

- Brewing Stand
- Cauldron
- Dragon Egg
- Enchantment Table
- End Portal
- End Portal Frame
- End Stone
- Nether Bricks
- Nether Brick Fence
- Nether Brick Stairs
- Nether Wart
- Mycelium
- Lily Pads

### Foundational world blocks

- Stone, Dirt, Grass, Sand, Gravel
- Ores (core ore set)
- Wood logs, Planks, Leaves

### Functional blocks

- Chest
- Furnace
- Crafting Table
- Doors
- Ladders
- Torches
- Glass

### Dimensional blocks

- Obsidian
- Glowstone
- Other Nether/End terrain blocks as needed

## Items that must exist

### Materials and building basics

- Wood logs
- Planks

### Tool sets

For each material tier: **wood, stone, iron, gold, diamond**

- Pickaxe
- Shovel
- Axe
- Sword

### Armor sets

For each material tier: **wood/stone not applicable**, but for AsserCraft parity include core armor tiers expected from the era:

- Iron
- Gold
- Diamond
- (Optional for progression parity: leather/chain if desired)

### Food

- Bread
- Pork
- Beef
- Chicken

### Brewing ingredients

- Blaze Rod
- Blaze Powder
- Nether Wart
- Ghast Tear
- Fermented Spider Eye

### Utility and misc.

- Bucket
- Compass
- Clock
- Lapis Lazuli (used for enchanting in simplified/forward-compatible rules)

## Mobs that must exist

### Passive / neutral

- Cow
- Pig
- Sheep
- Chicken
- Villager (if village structures are implemented)

### Hostile

- Zombie
- Skeleton
- Spider
- Creeper
- Slime
- Ghast
- Enderman (with limited block pickup)

### Boss

- Ender Dragon (End dimension encounter)

## Required mechanics and world features

Implement these before optimization/extras.

### World and blocks

- Chunk streaming
- Block registry
- Procedural terrain approximating early Java style
- Simple fluid simulation (water, lava)
- Block hardness and drop tables

### Player and inventory

- 36-slot inventory
- 9-slot hotbar
- Item pickup physics
- Block placement and breaking

### Crafting and progression

- 2×2 player crafting grid
- 3×3 crafting table grid
- Furnace smelting
- Brewing stand mechanics
- Enchanting table mechanics (simplified)

### Entities and AI

- FSM states: idle, wander, chase, attack
- Hostile spawning at night / low light
- Passive spawning on grass in daylight

### Dimensions

- Overworld
- Nether (portal + hell terrain)
- End (boss dimension)

### UI

- Main menu (world creation + settings)
- In-game HUD (health, hotbar)
- Inventory + crafting screens

## Canonical generation prompt block

Use this when prompting coding assistants:

```text
You are building a Java voxel sandbox game called AsserCraft 1.0 inspired by Minecraft Java Edition 1.0 release mechanics. You must implement all core systems in order:
1) Engine bootstrap with window, OpenGL rendering, game loop at 20 ticks per second.
2) Input and free camera, then 3D math utilities.
3) Chunk streaming and mesh generation with greedy mesher.
4) Block registry with all 1.0-era blocks: stone, dirt, grass, sand, gravel, wood/planks/leaves, ore blocks, functional blocks (crafting table, furnace, chest, doors, ladders, torches), Nether blocks (netherrack, nether bricks, soul sand, nether wart), End blocks (end stone, end portal).
5) Item registry with all 1.0-era tools, armor, food, utility items, brewing ingredients.
6) World generation seeded noise for terrain and ore distribution.
7) Fluids (water/lava) and lighting (skylight + block light).
8) Player movement, inventory, hotbar, block placing/breaking.
9) Crafting (2×2 and 3×3), smelting, brewing, enchanting.
10) Entities: passive mobs (cow, pig, sheep, chicken), hostile mobs (zombie, skeleton, spider, creeper, slime, ghast, enderman), and Ender Dragon boss. Entities must have AI states and death drops.
11) Spawning rules, difficulty settings, collision physics.
12) UI: menu screens, HUD, inventory screens, survival progression.
No copyrighted names or assets. Use data-driven definition
```
