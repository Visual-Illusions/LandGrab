package net.visualillusionsent.landgrab;

import com.google.common.base.Charsets;
import net.canarymod.plugin.Plugin;
import net.visualillusionsent.utils.PropertiesFile;

import java.util.UUID;

/**
 * Created by darkdiplomat on 10/17/14.
 */
public final class LandGrab extends Plugin{
    public static final UUID uuid = UUID.nameUUIDFromBytes("VIPlugin:LandGrab".getBytes(Charsets.UTF_8)); // Admin UUID
    public static final String claimAdminPerm = "landgrab.claim.administrator";

    @Override
    public boolean enable() {
        checkConfig();
        return false; // We arent a plugin yet
    }

    @Override
    public void disable() {

    }

    private void checkConfig(){
        PropertiesFile cfg = getConfig();
        cfg.getInt("starting-blocks", 100);
    }
}
