package com.westeroscraft.westerosblocks.blocks;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;

import com.westeroscraft.westerosblocks.WesterosBlockDef;
import com.westeroscraft.westerosblocks.WesterosBlockLifecycle;
import com.westeroscraft.westerosblocks.WesterosBlockFactory;

public class WCLeavesBlock extends LeavesBlock implements WesterosBlockLifecycle {

    public static class Factory extends WesterosBlockFactory {
        @Override
        public Block buildBlockClass(WesterosBlockDef def) {
            if (def.lightOpacity == WesterosBlockDef.DEF_INT) {
                def.lightOpacity = 1;
            }
        	BlockBehaviour.Properties props = def.makeProperties().noOcclusion().isSuffocating((state, reader, pos) -> false).isViewBlocking((state, reader, pos) -> false);
        	return def.registerRenderType(def.registerBlock(new WCLeavesBlock(props, def)), true, true);
        }
    }
    protected WesterosBlockDef def;
    private final boolean nodecay;
    public final boolean betterfoliage;
    public final boolean overlay;
    
    protected WCLeavesBlock(BlockBehaviour.Properties props, WesterosBlockDef def) {
        super(props);
        this.def = def;
        String typ = def.getType();
    	betterfoliage = (typ != null) && typ.contains("better-foliage");
    	overlay = (typ != null) && typ.contains("overlay");
    	nodecay = (typ != null) && typ.contains("no-decay");
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(7)).setValue(PERSISTENT, Boolean.valueOf(!nodecay)));
    }
    @Override
    public WesterosBlockDef getWBDefinition() {
        return def;
    }

    private static String[] TAGS = { "leaves" };
    @Override
    public String[] getBlockTags() {
    	return TAGS;
    }    
}
