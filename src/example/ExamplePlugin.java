package example;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.net.Administration.*;
import mindustry.world.blocks.storage.*;

public class ExamplePlugin extends Plugin{

    public static boolean doShutdown = false;
    public static int is = 0;
    @Override
    public void init(){
        Events.on(GameOverEvent.class, event -> {
            if (doShutdown && is > 0) {
                Log.info("gameover w/ autoshutdown on, exiting in 10 seconds");
                Call.sendMessage("[accent]GG![] The server will shutdown in 10 seconds to free resources for others.\n[accent]Thank you for using io.community games![]");
                Timer.schedule(() -> {
                    Core.app.exit();
                }, 10);
            }
            is += 1;
        });
    }

    @Override
    public void registerServerCommands(CommandHandler handler){
        handler.register("autoshutdown", "Turn on the autoshutdown upon gameover event", args -> {
            doShutdown = !doShutdown;
            Log.info("Auto shutdown is now " + (doShutdown ? "enabled" : "disabled"));
        });
    }

    @Override
    public void registerClientCommands(CommandHandler handler){
        handler.<Player>register("info", "", "?", (args, player) -> {
            player.sendMessage("These [accent]games[] are hosted by the [accent]<[]io[accent]>[] community.\nWant to create your own for [accent]free[]? Visit [accent]https://discord.io.community");
        });
    }
}
