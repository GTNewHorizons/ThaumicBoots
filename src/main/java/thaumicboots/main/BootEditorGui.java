package thaumicboots.main;

import java.util.ArrayList;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import com.cleanroommc.modularui.ModularUI;
import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.api.widget.IWidget;
import com.cleanroommc.modularui.drawable.Rectangle;
import com.cleanroommc.modularui.drawable.UITexture;
import com.cleanroommc.modularui.factory.GuiData;
import com.cleanroommc.modularui.factory.SimpleGuiFactory;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.ModularScreen;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.utils.MathUtils;
import com.cleanroommc.modularui.value.sync.DoubleSyncValue;
import com.cleanroommc.modularui.value.sync.GenericSyncValue;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widgets.ButtonWidget;
import com.cleanroommc.modularui.widgets.ItemDisplayWidget;
import com.cleanroommc.modularui.widgets.SliderWidget;
import com.cleanroommc.modularui.widgets.layout.Flow;
import com.cleanroommc.modularui.widgets.textfield.TextFieldWidget;

import thaumicboots.main.utils.VersionInfo;

public class BootEditorGui implements IGuiHolder<GuiData> {

    private static final SimpleGuiFactory GUI = new SimpleGuiFactory("mui:boot_editor", BootEditorGui::new);

    @Override
    public ModularScreen createScreen(GuiData data, ModularPanel mainPanel) {
        return new ModularScreen(ModularUI.ID, mainPanel);
    }

    @Override
    public ModularPanel buildUI(GuiData data, PanelSyncManager syncManager, UISettings settings) {
        ItemStack itemStack = data.getPlayer().getCurrentArmor(0);

        syncManager.syncValue("display_item", GenericSyncValue.forItem(() -> itemStack, null));
        syncManager.syncValue(
                "speed_boost",
                new DoubleSyncValue(
                        () -> itemStack.stackTagCompound.getDouble("speed"),
                        speed -> itemStack.stackTagCompound.setDouble("speed", Math.round(speed * 100) / 100.0)));
        syncManager.syncValue(
                "jump_boost",
                new DoubleSyncValue(
                        () -> itemStack.stackTagCompound.getDouble("jump"),
                        jump -> itemStack.stackTagCompound.setDouble("jump", Math.round(jump * 100) / 100.0)));
        syncManager.syncValue("charge", new DoubleSyncValue(() -> itemStack.stackTagCompound.getDouble("charge")));

        return ModularPanel.defaultPanel("boot_editor").coverChildren().child(
                Flow.col().coverChildren().childPadding(2).rightRel(1f).top(4).children(createToggleButtons(itemStack)))
                .child(
                        Flow.col().topRel(0f).leftRel(0f).coverChildren().crossAxisAlignment(Alignment.CrossAxis.START)
                                .childPadding(4).margin(7).child(IKey.str("Modulation Control").asWidget())
                                .child(createRow("speed_boost")).child(createRow("jump_boost")));
    }

    private ArrayList<IWidget> createToggleButtons(ItemStack itemStack) {
        ArrayList<IWidget> buttons = new ArrayList<>();

        final String[] NBTKeys = { "omni", "inertiacancelling", "step" };

        for (int i = 0; i < 3; i++) {
            String toggleNBTKey = NBTKeys[i];

            buttons.add(new ButtonWidget<>().size(16, 16).onMousePressed(button -> {
                if (!itemStack.stackTagCompound.getBoolean(toggleNBTKey)
                        || !itemStack.stackTagCompound.hasKey(toggleNBTKey)) {
                    itemStack.stackTagCompound.setBoolean(toggleNBTKey, true);
                } else {
                    itemStack.stackTagCompound.setBoolean(toggleNBTKey, false);
                }
                return true;
            }).overlay(
                    UITexture.builder().location(VersionInfo.ModID, "gui/OIS.png").imageSize(24 * 3, 24)
                            .subAreaXYWH(24 * i, 0, 24, 24).build()));
        }

        return buttons;
    }

    private Flow createRow(String syncKey) {
        final int SIZE = 28;

        return Flow.row().coverChildren().childPadding(0).height(SIZE)
                .child(new ItemDisplayWidget().syncHandler("display_item").size(SIZE, SIZE))
                .child(
                        new SliderWidget().syncHandler(syncKey).width(SIZE * 4).bounds(0, 1).stopper(0.01f)
                                .overlay(new Rectangle().color(0xFF0000)) // wanna do a custom texture later
                ).child(
                        new TextFieldWidget().syncHandler(syncKey).size(SIZE, SIZE).setTextAlignment(Alignment.CENTER)
                                .setNumbersDouble((a) -> Math.round(MathUtils.clamp(a, 0, 1) * 100) / 100d));
    }

    public static class Command extends CommandBase {

        @Override
        public String getCommandName() {
            return "bootEditor";
        }

        @Override
        public String getCommandUsage(ICommandSender sender) {
            return "/bootEditor";
        }

        @Override
        public void processCommand(ICommandSender sender, String[] args) {
            if (sender instanceof EntityPlayerMP entityPlayerMP && !entityPlayerMP.capabilities.isCreativeMode) {
                GUI.open(entityPlayerMP);
            } else {
                throw new CommandException("Player must be creative mode!");
            }
        }
    }
}
