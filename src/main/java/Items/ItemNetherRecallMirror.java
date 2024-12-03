package Items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.item.IItemPropertyGetter;

public class ItemNetherRecallMirror extends Item {
	public ItemNetherRecallMirror(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		this.maxStackSize = 1;
        this.setCreativeTab(CreativeTabs.TOOLS);

        this.addPropertyOverride(new ResourceLocation("charged"), new IItemPropertyGetter() {
			@Override
            public float apply(ItemStack itemstack, World world, EntityLivingBase entity) {
            	NBTTagCompound tag = itemstack.getTagCompound();
            	if (tag == null || !tag.getBoolean("used")) {
            		return 0F;
            	} else {
            		return 1F;
            	}
            }
        });
	}
	
	@Override	
    public void addInformation(ItemStack stack, World player, List<String> list, ITooltipFlag whatisthis) {
        list.add("An upgraded version of the regular Recall Mirror.");
        list.add("It will teleport you to your bed / world spawn and back to where you used it.");
    }
	
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack itemstack = player.getHeldItem(hand);

        NBTTagCompound tag = itemstack.getTagCompound();
        if (tag == null) {
        	tag = new NBTTagCompound();
        }

		player.getCooldownTracker().setCooldown(this, 20);

		if (player.dimension != 0) {
            player.changeDimension(0);
		}

        if (!tag.getBoolean("used")) {
        	tag.setDouble("PosX", player.posX);
            tag.setDouble("PosY", player.posY);
            tag.setDouble("PosZ", player.posZ);

            BlockPos target = player.getBedSpawnLocation(world, player.getBedLocation(0), player.isSpawnForced(0));
			if (target == null) {
	        	target = world.getSpawnPoint();
	        }

	        player.setPositionAndUpdate(target.getX() + 0.5, target.getY() + 1, target.getZ() + 0.5);
            tag.setBoolean("used", true);
        } else {
        	player.setPositionAndUpdate(tag.getDouble("PosX"), tag.getDouble("PosY"), tag.getDouble("PosZ"));
        	tag.setBoolean("used", false);
        }

		world.playSound((EntityPlayer) null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.NEUTRAL, 1F, 1F);
        itemstack.setTagCompound(tag);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
}
