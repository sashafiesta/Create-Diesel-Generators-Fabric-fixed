package com.jesz.createdieselgenerators.items;

import com.jesz.createdieselgenerators.config.ConfigRegistry;
import com.jesz.createdieselgenerators.entity.ChemicalSprayerProjectileEntity;
import com.jesz.createdieselgenerators.other.FuelTypeManager;
import com.simibubi.create.AllEnchantments;
import com.simibubi.create.content.equipment.armor.CapacityEnchantment;
import com.simibubi.create.foundation.item.CustomArmPoseItem;
import com.simibubi.create.foundation.utility.Lang;
import io.github.fabricators_of_create.porting_lib.enchant.CustomEnchantingBehaviorItem;
import io.github.fabricators_of_create.porting_lib.transfer.fluid.item.FluidHandlerItemStack;
import io.github.fabricators_of_create.porting_lib.fluids.FluidStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class ChemicalSprayerItem extends Item implements CustomArmPoseItem, CapacityEnchantment.ICapacityEnchantable, CustomEnchantingBehaviorItem, FluidStorageItem {
    boolean lighter;
    public ChemicalSprayerItem(Properties properties, boolean lighter) {
        super(properties.stacksTo(1));
        this.lighter = lighter;
    }
    public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, level, components, tooltipFlag);
        if(stack.getTag() != null) {
            CompoundTag tankCompound = stack.getTag().getCompound("Fluid");

            FluidStack fStack = FluidStack.loadFluidStackFromNBT(tankCompound);
            if(fStack.isEmpty()){
                components.add(Component.translatable("createdieselgenerators.tooltip.empty").withStyle(ChatFormatting.GRAY));
                return;
            }
            var v = EnchantmentHelper.getItemEnchantmentLevel(AllEnchantments.CAPACITY.get(), stack);
            components.add(Lang.fluidName(fStack).component().withStyle(ChatFormatting.GRAY).append(" ").append(Lang.number(fStack.getAmount()/81).style(ChatFormatting.GOLD).component()).append(Component.translatable("create.generic.unit.millibuckets").withStyle(ChatFormatting.GOLD)).append(Component.literal(" / ")).append(Lang.number(ConfigRegistry.TOOL_CAPACITY.get() + v*ConfigRegistry.TOOL_CAPACITY_ENCHANTMENT.get()).style(ChatFormatting.GRAY).component()).append(Component.translatable("create.generic.unit.millibuckets").withStyle(ChatFormatting.GRAY)));
            return;
        }
        components.add(Component.translatable("createdieselgenerators.tooltip.empty").withStyle(ChatFormatting.GRAY));

    }
    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.NONE;
    }

    @Override
    public HumanoidModel.ArmPose getArmPose(ItemStack stack, AbstractClientPlayer player, InteractionHand hand) {
        if (!player.swinging) {
            return HumanoidModel.ArmPose.CROSSBOW_HOLD;
        }
        return null;
    }
    @Override
    public int getBarColor(ItemStack stack) {
        return 0xEFEFEF;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stackInHand = player.getItemInHand(hand);
        if(stackInHand.getTag()!= null) {
            FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(stackInHand.getTag().getCompound("Fluid"));
            if (fluidStack.getAmount() != 0)
                player.startUsingItem(hand);
        }

        return super.use(level, player, hand);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) { return true; }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        if(enchantment == AllEnchantments.CAPACITY.get())
            return true;
        return false;
    }
    @Override
    public void onUseTick(Level level, LivingEntity player, ItemStack stack, int count) {
        if(stack.getTag()!= null) {
            FluidStack fluidStack = FluidStack.loadFluidStackFromNBT(stack.getTag().getCompound("Fluid"));
            if (!fluidStack.isEmpty()) {
                if(!level.isClientSide) {
                    if (count % 2 == 0) {
                        ChemicalSprayerProjectileEntity projectile = ChemicalSprayerProjectileEntity.spray(level, fluidStack, (FuelTypeManager.getGeneratedSpeed(fluidStack.getFluid()) != 0 && lighter) || fluidStack.getFluid().isSame(Fluids.LAVA), fluidStack.getFluid().isSame(Fluids.WATER));
                        projectile.setPos(player.position().add(0, 1.5f, 0));
                        projectile.shootFromRotation(player, player.getXRot() + new Random().nextFloat(-5, 5), player.getYRot() + new Random().nextFloat(-5, 5), 0.0f, 1.0f, 1.0f);
                        level.addFreshEntity(projectile);
                        fluidStack.setAmount(fluidStack.getAmount() - 1*81);

                    }
                    if (!(player instanceof Player p && p.isCreative()) && count % 25 == 0)
                        fluidStack.writeToNBT(stack.getTag().getCompound("Fluid"));
                }
            }
        }
        super.onUseTick(level, player, stack, count);
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 1000;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        if(stack.getTag() != null) {
            CompoundTag tankCompound = stack.getTag().getCompound("Fluid");
            FluidStack fStack = FluidStack.loadFluidStackFromNBT(tankCompound);
            return !fStack.isEmpty();
        }
        return false;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        if(stack.getTag() == null)
            return 0;
        CompoundTag tankCompound = stack.getTag().getCompound("Fluid");

        var v = EnchantmentHelper.getItemEnchantmentLevel(AllEnchantments.CAPACITY.get(), stack);

        return Math.round(13 * Mth.clamp(FluidStack.loadFluidStackFromNBT(tankCompound).getAmount()/81/(
                (float)ConfigRegistry.TOOL_CAPACITY.get() + v*ConfigRegistry.TOOL_CAPACITY_ENCHANTMENT.get()
        ), 0, 1));
    }

    @Override
    public Storage<FluidVariant> getFluidStorage(ItemStack stack, ContainerItemContext context) {
        return new FluidHandlerItemStack(context, ConfigRegistry.TOOL_CAPACITY.get()*81);
    }
}
