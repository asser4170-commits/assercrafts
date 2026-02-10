package com.assercraft.game;

import com.assercraft.block.BlockDefinition;
import com.assercraft.crafting.BrewingSystem;
import com.assercraft.crafting.CraftingSystem;
import com.assercraft.crafting.EnchantingSystem;
import com.assercraft.crafting.FurnaceSystem;
import com.assercraft.crafting.Recipe;
import com.assercraft.data.DataLoader;
import com.assercraft.dimension.DimensionManager;
import com.assercraft.engine.TickLoop;
import com.assercraft.entity.EntityDefinition;
import com.assercraft.entity.EntityInstance;
import com.assercraft.entity.SpawnSystem;
import com.assercraft.item.ItemDefinition;
import com.assercraft.player.Player;
import com.assercraft.registry.Registry;
import com.assercraft.ui.Hud;
import com.assercraft.ui.InventoryScreen;
import com.assercraft.ui.MainMenu;
import com.assercraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public final class AsserCraftGame {
    private final Registry<BlockDefinition> blockRegistry = new Registry<>();
    private final Registry<ItemDefinition> itemRegistry = new Registry<>();
    private final Registry<EntityDefinition> entityRegistry = new Registry<>();
    private final TickLoop tickLoop = new TickLoop();

    private final World world = new World(12345L);
    private final Player player = new Player();
    private final CraftingSystem crafting = new CraftingSystem();
    private final FurnaceSystem furnace = new FurnaceSystem();
    private final BrewingSystem brewing = new BrewingSystem();
    private final EnchantingSystem enchanting = new EnchantingSystem();
    private final SpawnSystem spawner = new SpawnSystem();
    private final DimensionManager dimensions = new DimensionManager();

    private final MainMenu mainMenu = new MainMenu();
    private final Hud hud = new Hud();
    private final InventoryScreen inventoryScreen = new InventoryScreen();
    private final List<EntityInstance> entities = new ArrayList<>();
    private final Random random = new Random(42L);
    private long tickCount;

    public void bootstrap() {
        DataLoader loader = new DataLoader();
        loader.loadBlocks(blockRegistry, "/data/assercraft/blocks/blocks.db");
        loader.loadItems(itemRegistry, "/data/assercraft/items/items.db");
        loader.loadEntities(entityRegistry, "/data/assercraft/entities/entities.db");

        crafting.addRecipe(new Recipe("planks", 1, 1, List.of("oak_log"), "oak_planks", 4));
        crafting.addRecipe(new Recipe("crafting_table", 2, 2,
                List.of("oak_planks", "oak_planks", "oak_planks", "oak_planks"),
                "crafting_table", 1));

        System.out.println(mainMenu.render());
        System.out.println("Loaded blocks: " + blockRegistry.size());
        System.out.println("Loaded items: " + itemRegistry.size());
        System.out.println("Loaded entities: " + entityRegistry.size());
        player.inventory().addItem("oak_log", 8);
    }

    public void runForTicks(int ticks) {
        tickLoop.runTicks(ticks, this::tick);
    }

    private void tick() {
        tickCount++;
        world.streamAround(player.chunkX(), player.chunkZ(), 2);

        boolean isNight = (tickCount / 24000L) % 2 == 1;
        int lightLevel = isNight ? 4 : 15;
        entities.addAll(spawner.spawnTick(isNight, lightLevel, true, this::createEntity));
        entities.forEach(e -> e.tick(random.nextDouble() * 30.0));
        entities.removeIf(e -> !e.isAlive());

        if (tickCount % 40 == 0) {
            player.move(1, 0, 0);
        }

        if (tickCount == 60) {
            furnace.smelt("iron_ore").ifPresent(output -> player.inventory().addItem(output, 1));
        }
        if (tickCount == 80) {
            brewing.brew("water_bottle", "nether_wart").ifPresent(p -> player.inventory().addItem(p, 1));
        }
        if (tickCount == 100) {
            String enchant = enchanting.enchant("iron_sword", 6, tickCount);
            System.out.println("Enchanted iron_sword with " + enchant);
        }
        if (tickCount == 140) {
            dimensions.travelToNether();
        }
        if (tickCount == 180) {
            dimensions.travelToEnd();
            spawnEnderDragon();
        }

        if (tickCount % 20 == 0) {
            System.out.println("Tick=" + tickCount
                    + " chunks=" + world.loadedChunkCount()
                    + " entities=" + entities.size()
                    + " dim=" + dimensions.current());
            System.out.println(hud.render(player));
        }
        if (tickCount % 100 == 0) {
            System.out.println(inventoryScreen.render(player.inventory()));
        }
    }

    private Optional<EntityInstance> createEntity(String id) {
        return entityRegistry.get(id).map(d -> new EntityInstance(d.id(), d.type(), d.health()));
    }

    private void spawnEnderDragon() {
        createEntity("ender_dragon").ifPresent(entities::add);
    }
}
