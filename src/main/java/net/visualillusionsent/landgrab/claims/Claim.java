package net.visualillusionsent.landgrab.claims;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import net.canarymod.Canary;
import net.canarymod.api.PlayerReference;
import net.canarymod.database.exceptions.DatabaseWriteException;
import net.visualillusionsent.landgrab.claims.area.Area;
import net.visualillusionsent.landgrab.claims.area.Vertex;
import net.visualillusionsent.landgrab.database.ClaimDataAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by darkdiplomat on 10/17/14.
 */
public final class Claim {
    private UUID owner;
    private Area area;
    private ArrayListMultimap<UUID, Permission> permissions = ArrayListMultimap.create();
    private ArrayList<Protections> protections = Lists.newArrayList();

    public Claim(){
    }

    public final void setOwner(PlayerReference reference){
        this.owner = reference.getUUID();
    }

    public final void setArea(Area area){
        this.area = area;
    }

    public final boolean givePermission(PlayerReference reference, Permission permission){
        return permissions.put(reference.getUUID(), permission);
    }

    public final boolean removePermission(PlayerReference reference, Permission permission){
        return permissions.remove(reference, permission);
    }

    public final boolean hasPermission(PlayerReference reference, Permission permission){
        return isOwner(reference)|| (permissions.containsKey(reference.getUUID()) && permissions.get(reference.getUUID()).contains(permission));
    }

    public final boolean isOwner(PlayerReference reference){
        return reference.getUUID().equals(owner);
    }

    public final boolean save(){
        ClaimDataAccess data = new ClaimDataAccess();

        data.owner = owner.toString();
        data.origin = Vertex.serializeVertex(area.getOrigin());
        data.offset = Vertex.serializeVertex(area.getOffset());
        data.permissions = serializePermissions();

        try {
            HashMap<String, Object> filter = new HashMap<String, Object>();
            filter.put("owner", owner.toString());
            Canary.db().update(data, filter);
        }
        catch (DatabaseWriteException dbwex) {
            return false;
        }
        return true;
    }

    private List<String> serializePermissions(){
        ArrayList<String> serialPerms = new ArrayList<String>();
        for(UUID owner : permissions.keySet()){
            for(Permission permission : permissions.get(owner)) {
                serialPerms.add(String.format("%s|%s", owner.toString(), permission.name()));
            }
        }
        return serialPerms;
    }

}
