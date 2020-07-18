package cc.fatenetwork.kbase.utils.chat;

import net.minecraft.server.v1_8_R3.*;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

public class ChatUtil{
	public static String getName(net.minecraft.server.v1_8_R3.ItemStack stack){
		NBTTagCompound nbttagcompound;
		if(stack.getTag() != null && stack.getTag().hasKeyOfType("display", 10) && (nbttagcompound = stack.getTag() .getCompound("display")).hasKeyOfType("Name", 8)){
			return nbttagcompound.getString("Name");
		}
		return stack.getItem().a(stack) + ".name";
	}

	public static Trans localFromItem(ItemStack stack){
		PotionType type;
		Potion potion;
		if(stack.getType() == Material.POTION && stack.getData().getData() == 0 && (potion = Potion.fromItemStack(stack)) != null && (type = potion.getType()) != null && type != PotionType.WATER){
			String effectName = (potion.isSplash() ? "Splash " : "") + WordUtils.capitalizeFully(type.name().replace('_', ' ')) + " L" + potion.getLevel();
			return ChatUtil.fromItemStack(stack).append(" of " + effectName);
		}
		return ChatUtil.fromItemStack(stack);
	}

	public static Trans fromItemStack(ItemStack stack){
		net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(stack);
		NBTTagCompound tag = new NBTTagCompound();
		nms.save(tag);
		return new Trans(ChatUtil.getName(nms)).setColor(ChatColor.getByChar(nms.u().e.e().charAt(0))).setHover(HoverAction.SHOW_ITEM, new ChatComponentText(tag.toString()));
	}

	public static void reset(IChatBaseComponent text){
		ChatModifier modifier = text.getChatModifier();
		modifier.setChatHoverable(null);
		modifier.setChatClickable(null);
		modifier.setBold(Boolean.valueOf(false));
		modifier.setColor(EnumChatFormat.RESET);
		modifier.setItalic(Boolean.valueOf(false));
		modifier.setRandom(Boolean.valueOf(false));
		modifier.setStrikethrough(Boolean.valueOf(false));
		modifier.setUnderline(Boolean.valueOf(false));
	}

	public static void send(CommandSender sender, IChatBaseComponent text){
		if(sender instanceof Player){
			Player player = (Player) sender;
			PacketPlayOutChat packet = new PacketPlayOutChat(text);
			EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
			entityPlayer.playerConnection.sendPacket(packet);
		}else{
			sender.sendMessage(text.c());
		}
	}
}
