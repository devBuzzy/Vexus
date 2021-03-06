package pw.vexus.core.warp;

import com.google.common.collect.ImmutableList;
import net.cogzmc.core.modular.command.ArgumentRequirementException;
import net.cogzmc.core.modular.command.CommandException;
import net.cogzmc.core.modular.command.CommandMeta;
import net.cogzmc.core.modular.command.CommandPermission;
import net.cogzmc.core.player.CPlayer;
import pw.vexus.core.VexusCommand;
import pw.vexus.core.VexusCore;
import pw.vexus.core.commands.Confirmer;

@CommandMeta(description = "Deletes warps by name")
@CommandPermission("vexus.delwarp")
public final class DelWarpCommand extends VexusCommand {
    public DelWarpCommand() {
        super("delwarp");
    }

    @Override
    protected void handleCommand(CPlayer player, String[] args) throws CommandException {
        if (args.length == 0) throw new ArgumentRequirementException("You need to specify a warp!");
        final String arg = args[0];
        final WarpManager warpManager = VexusCore.getInstance().getWarpManager();
        ImmutableList<String> warps = warpManager.getWarps();
        if (!warps.contains(arg)) throw new ArgumentRequirementException("This warp does not exist!");
        Confirmer.confirm("Are you sure you want to delete the warp " + arg, player, (result, player1) -> {
            if (!result) return;
            warpManager.delWarp(arg);
            player1.sendMessage(VexusCore.getInstance().getFormat("del-warp", new String[]{"<warp>", arg}));
        });
    }
}
