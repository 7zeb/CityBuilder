public class CityBuilderHelpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage("CityBuilder Help:");
        sender.sendMessage("/fillerbuilding <template> - Spawn a filler building");
        sender.sendMessage("/citybuilderabout - Info about the plugin");
        sender.sendMessage("/citybuilderhelp  - This shows the help information of the plugin");
        return true;
    }
}
