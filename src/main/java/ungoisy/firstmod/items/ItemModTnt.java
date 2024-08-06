package ungoisy.firstmod.items;

import net.minecraft.item.Item;
import ungoisy.firstmod.FirstMod;

public class ItemModTnt extends Item {
    public ItemModTnt(String unlocalizedName) {
        this.setUnlocalizedName(unlocalizedName);
        this.setCreativeTab(FirstMod.mod_items);
    }
}
