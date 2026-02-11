# AsserCraft 1.0 Reference Specification

This document captures the core Minecraft Java Edition 1.0-inspired requirements to use as a development target for AsserCraft.

## Release Context

- Baseline inspiration: Java Edition **1.0.0** (Nov 18, 2011)
- Key gameplay pillars:
  - Brewing
  - Enchanting
  - The End dimension
  - Villagers (if structures are implemented)
  - Ender Dragon boss encounter

## Block Coverage (1.0-era)

### Required special/feature blocks

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
- Nether Wart (crop)
- Mycelium
- Lily Pad

### Required core building/terrain blocks

- Stone
- Dirt
- Grass
- Sand
- Gravel
- Common ores
- Wood logs
- Wood planks
- Leaves

### Required functional blocks

- Chest
- Furnace
- Crafting Table
- Doors
- Ladders
- Torches
- Glass

### Required dimensional blocks

- Obsidian
- Glowstone
- Netherrack
- Soul Sand

## Item Coverage (1.0-era)

### Materials and building

- Wood logs
- Wood planks

### Tool progression

- Pickaxe (wood, stone, iron, gold, diamond)
- Shovel (wood, stone, iron, gold, diamond)
- Axe (wood, stone, iron, gold, diamond)
- Sword (wood, stone, iron, gold, diamond)

### Armor progression

- Full armor sets for: leather/wood-style early equivalent, stone-tier equivalent (if modeled), iron, gold, diamond

### Foods

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

### Utility/misc

- Bucket
- Compass
- Clock
- Lapis Lazuli (for enchanting integration)

## Entity Coverage

### Passive/neutral

- Cow
- Pig
- Sheep
- Chicken
- Villager (if villages/structures are enabled)

### Hostile

- Zombie
- Skeleton
- Spider
- Creeper
- Slime
- Ghast
- Enderman (with limited block interaction)

### Boss

- Ender Dragon (End dimension encounter)

## Required Gameplay Systems

### World and blocks

1. Chunk streaming
2. Block registry
3. Procedural terrain in a classic early-Java style
4. Water and lava fluid behavior (simplified acceptable initially)
5. Block hardness and drop tables

### Player and inventory

1. 36-slot inventory
2. 9-slot hotbar
3. Item pickup physics
4. Block placement and block breaking

### Crafting and progression

1. 2x2 player crafting
2. 3x3 crafting table recipes
3. Furnace smelting
4. Brewing stand interactions
5. Enchanting table interactions (simplified acceptable initially)

### Entities and AI

1. FSM states at minimum: idle, wander, chase, attack
2. Hostile spawning at night / low light
3. Passive spawning on grass in daylight

### Dimensions

1. Overworld
2. Nether (portal + hell terrain)
3. End (boss arena)

### UI

1. Main menu with world creation/settings
2. In-game HUD (health + hotbar)
3. Inventory/crafting interface screens

## Implementation Priority Checklist

Implement in this order:

1. Engine bootstrap (window, rendering, fixed 20 TPS game tick)
2. Input + free camera + 3D math utilities
3. Chunk streaming + greedy meshing
4. Block registry for all required 1.0-era blocks
5. Item registry for required tools, armor, food, brewing, utility items
6. Seeded world generation and ore distribution
7. Fluids + skylight/block-light model
8. Player interaction loop (movement/inventory/place/break)
9. Crafting, smelting, brewing, enchanting
10. Mobs + AI + drops including Ender Dragon
11. Spawn rules, difficulty, collision/physics
12. UI flow: menu -> gameplay HUD -> inventory/progression

## Prompt Template for Coding Agents

Use this prompt text directly when bootstrapping implementation work:

> You are building a Java voxel sandbox game called AsserCraft 1.0 inspired by Minecraft Java Edition 1.0 release mechanics. You must implement all core systems in order:
> 1) Engine bootstrap with window, OpenGL rendering, game loop at 20 ticks per second.
> 2) Input and free camera, then 3D math utilities.
> 3) Chunk streaming and mesh generation with greedy mesher.
> 4) Block registry with all 1.0-era blocks: stone, dirt, grass, sand, gravel, wood/planks/leaves, ore blocks, functional blocks (crafting table, furnace, chest, doors, ladders, torches), Nether blocks (netherrack, nether bricks, soul sand, nether wart), End blocks (end stone, end portal).
> 5) Item registry with all 1.0-era tools, armor, food, utility items, brewing ingredients.
> 6) World generation seeded noise for terrain and ore distribution.
> 7) Fluids (water/lava) and lighting (skylight + block light).
> 8) Player movement, inventory, hotbar, block placing/breaking.
> 9) Crafting (2x2 and 3x3), smelting, brewing, enchanting.
> 10) Entities: passive mobs (cow, pig, sheep, chicken), hostile mobs (zombie, skeleton, spider, creeper, slime, ghast, enderman), and Ender Dragon boss. Entities must have AI states and death drops.
> 11) Spawning rules, difficulty settings, collision physics.
> 12) UI: menu screens, HUD, inventory screens, survival progression.
> No copyrighted names or assets. Use data-driven definitions where practical.
