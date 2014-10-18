package net.visualillusionsent.landgrab;

import net.canarymod.hook.player.BlockLeftClickHook;
import net.canarymod.hook.player.BlockRightClickHook;
import net.canarymod.plugin.PluginListener;
import net.visualillusionsent.landgrab.claims.area.Vertex;

/**
 * Created by darkdiplomat on 10/17/14.
 */
public class LandGrabHookListener implements PluginListener {

    public final void interaction(BlockRightClickHook hook){
        Vertex working = new Vertex(hook.getBlockClicked());

    }

    public final void destruction(BlockLeftClickHook hook){

    }
}
