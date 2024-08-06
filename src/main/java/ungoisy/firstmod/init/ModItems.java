package ungoisy.firstmod.init;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ungoisy.firstmod.Reference;
import ungoisy.firstmod.items.*;

public class ModItems {


    public static Item bw_fireball;
    public static Item bridge_egg;
    public static Item bw_tnt;
    public static Item bridge_egg_red;



    public static void init() {
        bw_fireball = new ItemModFireball("bw_fireball");
        bridge_egg = new ItemBridgeEgg("bridge_egg");
        bridge_egg_red = new ItemBridgeEgg("bridge_egg_red");
        bw_tnt = new ItemModTnt("bw_tnt");
    }

    public static void register() {
        GameRegistry.registerItem(bw_fireball, bw_fireball.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(bridge_egg, bridge_egg.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(bridge_egg_red, bridge_egg_red.getUnlocalizedName().substring(5));
        GameRegistry.registerItem(bw_tnt, bw_tnt.getUnlocalizedName().substring(5));
    }

    public static void registerRenders() {
        registerRender(bw_fireball);
        registerRender(bridge_egg);
        registerRender(bridge_egg_red);
        registerRender(bw_tnt);
    }

    public static void registerRender(Item item) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
