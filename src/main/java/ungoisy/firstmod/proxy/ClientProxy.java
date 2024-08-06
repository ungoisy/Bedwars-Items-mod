package ungoisy.firstmod.proxy;

import ungoisy.firstmod.init.ModBlocks;
import ungoisy.firstmod.init.ModItems;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenders() {
        ModItems.registerRenders();
        ModBlocks.registerRenders();
    }


}
