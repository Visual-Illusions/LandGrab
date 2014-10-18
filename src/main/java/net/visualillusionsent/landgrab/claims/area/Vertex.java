package net.visualillusionsent.landgrab.claims.area;

import net.canarymod.ToolBox;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.world.blocks.Block;

/**
 * Created by darkdiplomat on 10/17/14.
 */
public class Vertex {
    private final int x, z;

    public Vertex(int x, int z){
        this.x = x;
        this.z = z;
    }

    public Vertex(Block block){
        this.x = block.getX();
        this.z = block.getZ();
    }

    public Vertex(Entity entity){
        this.x = ToolBox.floorToBlock(entity.getX());
        this.z = ToolBox.floorToBlock(entity.getZ());
    }

    public final int getX(){
        return x;
    }

    public final int getZ(){
        return z;
    }

    @Override
    public final boolean equals(Object object) {
        return this == object || (object instanceof Vertex && x == ((Vertex)object).x && z == ((Vertex)object).z);
    }

    public final String toString(){
        return String.format("Vertex[X: %d, Z: %d]", x, z);
    }

    public static String serializeVertex(Vertex vertex) throws VertexSerializationException {
        if(vertex == null){
            throw new VertexSerializationException("Vertex was null");
        }
        return String.format("%d|%d", vertex.getX(), vertex.getZ());
    }

    public static Vertex deserializeVertex(String vertex){
        if(!vertex.matches("\\d+\\|\\d+")){
            throw new VertexDeserializationException("Invalid vertex serialized string (Given: '"+vertex+"' Expecting: 'integer|integer'");
        }
        String[] deserial = vertex.split("\\|");
        return new Vertex(Integer.valueOf(deserial[0]), Integer.valueOf(deserial[1]));
    }
}
