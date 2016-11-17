package betterwithmods.items.tools;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Purpose:
 *
 * @author Tyler Marshall
 * @version 11/14/16
 */
public class ItemSoulforgeArmor extends ItemArmor {
    private static final ArmorMaterial SOULFORGED_STEEL = EnumHelper.addArmorMaterial("soulforged_steel", "",45, new int[]{4, 7, 9, 4}, 25, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);

    public ItemSoulforgeArmor(EntityEquipmentSlot equipmentSlotIn) {
        super(SOULFORGED_STEEL, 2, equipmentSlotIn);
    }
}