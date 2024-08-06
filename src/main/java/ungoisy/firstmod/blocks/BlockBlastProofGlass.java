package ungoisy.firstmod.blocks;

import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ungoisy.firstmod.FirstMod;
import ungoisy.firstmod.creativetabs.TabModItems;

public class BlockBlastProofGlass extends BlockGlass {

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }

    public BlockBlastProofGlass(String unlocalizedName) {
        super(Material.glass, false);
        this.setUnlocalizedName(unlocalizedName);
        this.blockResistance = 1000;
        this.blockHardness = 0.3F;
        this.stepSound = soundTypeGlass;
        this.setCreativeTab(FirstMod.mod_items);
    }
}
