package Items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemPortalScroll extends Item {
	public ItemPortalScroll(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		this.maxStackSize = 40;
		this.setCreativeTab(CreativeTabs.TOOLS);
	}

	@Override
    public void addInformation(ItemStack itemstack, World world, List<String> list, ITooltipFlag whatisthis) {
        list.add("Returns you to your bed / world spawn.");
        list.add("NOTE: Single use.");
    }

	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

		player.getCooldownTracker().setCooldown(this, 20);

        if (player.dimension != 0) {
        	player.changeDimension(0);
        }

		BlockPos target = player.getBedSpawnLocation(world, player.getBedLocation(0), player.isSpawnForced(0));
        if (target == null) {
        	target = world.getSpawnPoint();
        }

        player.setPositionAndUpdate(target.getX() + 0.5, target.getY() + 1, target.getZ() + 0.5);
        world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.NEUTRAL, 1F, 1F);
        itemstack.shrink(1);

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}