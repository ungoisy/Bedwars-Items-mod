package ungoisy.firstmod.init;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ungoisy.firstmod.Reference;
import ungoisy.firstmod.blocks.BlockBlastProofGlass;

public class ModBlocks {

    public static Block blast_proof_glass;
    public static Block blast_proof_glass_red;

    public static void init() {
        blast_proof_glass = new BlockBlastProofGlass("blast_proof_glass");
        blast_proof_glass_red = new BlockBlastProofGlass("blast_proof_glass_red");
    }

    public static void register() {
        registerBlock(blast_proof_glass);
        registerBlock(blast_proof_glass_red);
    }

    public static void registerRenders() {
        registerRender(blast_proof_glass);
        registerRender(blast_proof_glass_red);
    }

    public static void registerBlock(Block block) {
        GameRegistry.registerBlock(block, block.getUnlocalizedName().substring(5));
    }

    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
    }
}
