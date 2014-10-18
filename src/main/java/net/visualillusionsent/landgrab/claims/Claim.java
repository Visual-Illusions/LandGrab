package net.visualillusionsent.landgrab.claims;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import net.canarymod.Canary;
import net.canarymod.api.PlayerReference;
import net.canarymod.database.exceptions.DatabaseWriteException;
import net.visualillusionsent.landgrab.LandGrab;
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
    private ArrayList<Protection> protections = Lists.newArrayList();

    public Claim(){}

    public Claim(ClaimDataAccess data){
        this.owner = UUID.fromString(data.owner);
        this.area = new Area(Vertex.deserializeVertex(data.origin), Vertex.deserializeVertex(data.offset));
        deserializePermissions(data.permissions);
        deserializeProtections(data.protections);
    }

    public final void setOwner(PlayerReference reference){
        this.owner = reference.getUUID();
    }

    public final void setOwner(UUID uuid) {
        this.owner = uuid;
    }

    public final Area getArea(){
        return this.area;
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
        return isOwner(reference) || reference.hasPermission(LandGrab.claimAdminPerm) || (permissions.containsKey(reference.getUUID()) && permissions.get(reference.getUUID()).contains(permission));
    }

    public final UUID getOwner(){
        return this.owner;
    }

    public final boolean isOwner(PlayerReference reference){
        return reference.getUUID().equals(owner);
    }

    public final ClaimDataAccess getDataAccess(){
        ClaimDataAccess data = new ClaimDataAccess();

        data.owner = owner.toString();
        data.origin = Vertex.serializeVertex(area.getOrigin());
        data.offset = Vertex.serializeVertex(area.getOffset());
        data.permissions = serializePermissions();
        data.protections = serializeProtections();

        return data;
    }

    private List<String> serializePermissions(){
        ArrayList<String> serialPerms = Lists.newArrayList();
        for(UUID owner : permissions.keySet()){
            for(Permission permission : permissions.get(owner)) {
                serialPerms.add(String.format("%s|%s", owner.toString(), permission.name()));
            }
        }
        return serialPerms;
    }

    private void deserializePermissions(List<String> perms){
        for(String serial : perms){
            String[] deserial = serial.split("\\|");
            permissions.put(UUID.fromString(deserial[0]), Permission.valueOf(deserial[1]));
        }
    }

    private List<String> serializeProtections(){
        ArrayList<String> serialProtections = Lists.newArrayList();
        for(Protection protection : protections){
            serialProtections.add(protection.name());
        }
        return serialProtections;
    }

    private void deserializeProtections(List<String> protect){
        for(String serial : protect){
            protections.add(Protection.valueOf(serial));
        }
    }

}
