package ungoisy.firstmod.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TabModItems extends CreativeTabs {


    public TabModItems(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return Items.bed;
    }
}
