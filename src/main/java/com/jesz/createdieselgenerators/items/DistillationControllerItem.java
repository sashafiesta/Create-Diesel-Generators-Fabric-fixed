package com.jesz.createdieselgenerators.items;

import com.jesz.createdieselgenerators.blocks.BlockRegistry;
import com.jesz.createdieselgenerators.blocks.entity.DistillationTankBlockEntity;
import com.simibubi.create.AllSoundEvents;
import com.simibubi.create.content.fluids.tank.FluidTankBlockEntity;
import io.github.fabricators_of_create.porting_lib.transfer.TransferUtil;
import io.github.fabricators_of_create.porting_lib.transfer.fluid.FluidTank;
import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class DistillationControllerItem extends Item {
    public DistillationControllerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(context.getLevel().getBlockEntity(context.getClickedPos()) instanceof FluidTankBlockEntity ftbe){
            ItemStack item = context.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
            BlockPos cPos = ftbe.getController();
            int width = ftbe.getControllerBE().getWidth();
            int height = ftbe.getControllerBE().getHeight();
            FluidStack fluidInTank = ftbe.getFluid(0);

            //FluidStack fluidInTank = ftbe.getCapability(ForgeCapabilities.FLUID_HANDLER).orElse(new FluidTank(0)).getFluidInTank(0).copy();
            for (int x = 0; x < width; x++) {
                for (int z = 0; z < width; z++) {
                    for (int y = 0; y < height; y++) {
                        if(item.getCount() == 0 && !context.getPlayer().isCreative())
                            break;
                        context.getLevel().setBlock(cPos.offset(x, y, z), BlockRegistry.DISTILLATION_TANK.getDefaultState(), 1);
                        context.getLevel().updateNeighborsAt(cPos.offset(x, y, z), BlockRegistry.DISTILLATION_TANK.getDefaultState().getBlock());
                        if(!context.getPlayer().isCreative())
                            item.shrink(1);
                    }
                }
            }
            AllSoundEvents.WRENCH_ROTATE.playAt(context.getLevel(), cPos.getX()+ (double) width /2, cPos.getY()+ (double) height /2, cPos.getZ()+ (double) width /2, 1f, 1f, false);
            for (int x = 0; x < width; x++) {
                for (int z = 0; z < width; z++) {
                    for (int y = 0; y < height; y++) {
                        if(context.getLevel().getBlockEntity(cPos.offset(x, y, z)) instanceof DistillationTankBlockEntity dtbe){
                            dtbe.updateVerticalMulti();
                            dtbe.updateConnectivity();
                            if(x == 0 && y == 0 && z == 0){
                                //IFluidHandler tank = dtbe.getCapability(ForgeCapabilities.FLUID_HANDLER).orElse(null);
                                Storage<FluidVariant> handler = TransferUtil.getFluidStorage(context.getLevel(), cPos, dtbe, null);
                                if(handler != null && fluidInTank.getType() != FluidVariant.blank()) {
                                    TransferUtil.insertFluid(handler, fluidInTank);
                                }

                                dtbe.updateTemperature();
                            }
                        }
                    }
                }
            }

            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }
}
