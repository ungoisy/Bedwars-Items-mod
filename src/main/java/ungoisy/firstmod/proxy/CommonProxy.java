package ungoisy.firstmod.proxy;

import net.minecraftforge.common.MinecraftForge;
import ungoisy.firstmod.events.*;

public class CommonProxy {
    public void registerRenders() {

    }


    public void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new FireballEvent());
        MinecraftForge.EVENT_BUS.register(new BridgeEggEvent());
        MinecraftForge.EVENT_BUS.register(new ModTntEvent());
    }
}
