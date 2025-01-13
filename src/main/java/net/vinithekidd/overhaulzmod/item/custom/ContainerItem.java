package net.vinithekidd.overhaulzmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ContainerItem extends Item {
    private final TagKey<Item> acceptedItemsTag;
    private final int maxCapacity;

    public ContainerItem(Properties properties, TagKey<Item> acceptedItemsTag, int maxCapacity) {
        super(properties);
        this.acceptedItemsTag = acceptedItemsTag;
        this.maxCapacity = maxCapacity;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack boxStack = player.getItemInHand(usedHand); // O recipiente (ItemStack)
        boolean isMainHand = (usedHand == InteractionHand.MAIN_HAND);
        ItemStack altHandStack = player.getItemInHand(isMainHand ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND); // O stack na outra mão

        if (!level.isClientSide) {
            CompoundTag tag = boxStack.getOrCreateTag(); // Obtemos o NBT da caixa de itens
            int storedCount = tag.getInt("StoredCount"); // Quantidade armazenada no recipiente atualmente

            if (tag.contains("StoredItem")) {
                // Já temos um item armazenado na caixa
                String storedItemType = tag.getString("StoredItem"); // Qual o item armazenado (tipo)

                if (altHandStack.isEmpty()) {
                    // 1. ESVAZIA O RECIPIENTE CASO A MÃO ALTERNATIVA ESTEJA VAZIA:
                    int leftoverItems = addItemsToInventory(player, storedItemType, storedCount);

                    if (leftoverItems == 0) {
                        // Limpamos o NBT caso todos os itens tenham sido movidos ao inventário
                        tag.remove("StoredItem");
                        tag.remove("StoredCount");
                        tag.remove("stored_item"); // REMOVE "stored_item"
                    } else {
                        // Atualizamos o "StoredCount" caso tenha itens restantes
                        tag.putInt("StoredCount", leftoverItems);
                    }

                    return InteractionResultHolder.sidedSuccess(boxStack, level.isClientSide);

                } else if (altHandStack.is(ForgeRegistries.ITEMS.getValue(new ResourceLocation(storedItemType)))) {
                    // 2. ADICIONA MAIS ITENS SE O MESMO TIPO JÁ ESTÁ NA CAIXA:
                    int remainingCapacity = maxCapacity - storedCount; // Calcula o quanto ainda cabe no recipiente

                    if (remainingCapacity > 0) {
                        int amtToAdd = Math.min(altHandStack.getCount(), remainingCapacity);
                        tag.putInt("StoredCount", storedCount + amtToAdd); // Atualiza o número armazenado
                        altHandStack.shrink(amtToAdd); // Remove os itens da mão alternativa

                        // Verifica e atualiza explicitamente o valor de "stored_item"
                        if (storedItemType.equals("overhaulzmod:box_of_nails")) {
                            tag.putInt("stored_item", 1); // nails
                        } else if (storedItemType.equals("overhaulzmod:box_of_screws")) {
                            tag.putInt("stored_item", 2); // screws
                        }

                        return InteractionResultHolder.sidedSuccess(boxStack, level.isClientSide);
                    }
                }
            } else if (!altHandStack.isEmpty() && altHandStack.is(acceptedItemsTag)) {
                // 3. ARMAZENAMENTO INICIAL (RECIPIENTE ESTAVA VAZIO):
                int maxToAdd = Math.min(altHandStack.getCount(), maxCapacity); // Quantos itens podem ser adicionados
                tag.putString("StoredItem", ForgeRegistries.ITEMS.getKey(altHandStack.getItem()).toString()); // Tipo do item
                tag.putInt("StoredCount", maxToAdd); // Armazena a quantidade inicial
                altHandStack.shrink(maxToAdd); // Remove os itens da mão alternativa do jogador

                // Adiciona o valor inicial de "stored_item"
                if (altHandStack.getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation("overhaulzmod:box_of_nails"))) {
                    tag.putInt("stored_item", 1); // nails
                } else if (altHandStack.getItem() == ForgeRegistries.ITEMS.getValue(new ResourceLocation("overhaulzmod:box_of_screws"))) {
                    tag.putInt("stored_item", 2); // screws
                }

                return InteractionResultHolder.sidedSuccess(boxStack, level.isClientSide);
            }
        }

        return InteractionResultHolder.pass(boxStack); // Se nenhuma condição for atendida
    }

    private int addItemsToInventory(Player player, String itemType, int totalAmount) {
        // Obtém o item baseado no tipo fornecido
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemType));
        if (item == null) {
            // Retorna o total caso o item não seja válido
            return totalAmount;
        }

        int remaining = totalAmount;

        // Loop pelos slots do inventário do jogador
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);

            // Verifica se o slot está vazio
            if (stack.isEmpty()) {
                // Adiciona itens ao slot vazio
                int amountToAdd = Math.min(remaining, item.getMaxStackSize());
                player.getInventory().setItem(i, new ItemStack(item, amountToAdd));
                remaining -= amountToAdd;

            } else if (stack.getItem() == item && stack.getCount() < stack.getMaxStackSize()) {
                // Verifica se é o mesmo item e se há espaço no stack
                int spaceLeft = stack.getMaxStackSize() - stack.getCount();
                int amountToAdd = Math.min(spaceLeft, remaining);
                stack.grow(amountToAdd); // Adiciona a quantidade ao stack
                remaining -= amountToAdd;
            }

            // Se todo o restante foi adicionado, finaliza o loop
            if (remaining <= 0) {
                break;
            }
        }

        // Retorna o que sobrou (caso não tenha espaço suficiente no inventário)
        return remaining;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);

        CompoundTag tag = stack.getTag();

        if (tag != null && tag.contains("StoredItem")) {
            String storedItemType = tag.getString("StoredItem");
            int storedCount = tag.getInt("StoredCount");

            Item storedItem = ForgeRegistries.ITEMS.getValue(new ResourceLocation(storedItemType));
            if (storedItem != null) {
                tooltip.add(Component.literal("A box of " + storedItem.getDescription().getString())
                        .withStyle(ChatFormatting.GRAY));
                // Texto: "x/y"
                tooltip.add(Component.literal(storedCount + "/" + this.maxCapacity)
                        .withStyle(ChatFormatting.YELLOW));
            }

        } else {
            // Caso o recipiente esteja vazio
            tooltip.add(Component.literal("Just an empty box...")
                    .withStyle(ChatFormatting.GRAY));
            tooltip.add(Component.literal("0/" + this.maxCapacity)
                    .withStyle(ChatFormatting.YELLOW));
        }
    }
}