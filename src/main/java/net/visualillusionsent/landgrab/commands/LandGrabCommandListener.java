package net.visualillusionsent.landgrab.commands;

import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.Command;
import net.visualillusionsent.minecraft.plugin.ModMessageReceiver;
import net.visualillusionsent.minecraft.plugin.canary.VisualIllusionsCanaryPlugin;
import net.visualillusionsent.minecraft.plugin.canary.VisualIllusionsCanaryPluginInformationCommand;

/**
 * Created by darkdiplomat on 10/17/14.
 */
public class LandGrabCommandListener extends VisualIllusionsCanaryPluginInformationCommand {

    public LandGrabCommandListener(VisualIllusionsCanaryPlugin plugin) {
        super(plugin);
    }

    @Command(
            aliases = {"landgrab"},
            permissions = {"landgrab.info"},
            description = "LandGrab Information Command",
            toolTip = "/landgrab",
            version = 2
    )
    public final void informational(MessageReceiver receiver, String[] args){
        super.sendInformation(receiver);
    }

    @Override
    protected void messageInject(ModMessageReceiver receiver) {
        // TODO Available/Used block area count thing
    }

    @Command(
            aliases = {"tool"},
            permissions = {"landgrab.claim."},
            description = "LandGrab tool toggle command",
            toolTip = "/landgrab tool",
            parent = "landgrab"
    )
    public final void tool(MessageReceiver receiver, String[] args){

    }

}
