package de.libagamer.spigotplugin.main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ColorCodeParser {

    private static String output;

    public static String parseColors(String input){

        if(!input.contains("&")){
            output = input;
        }
        else {

            List<String> outputList = new ArrayList<>();

            String[] splittedInput = input.split("&");

            for(int i = 1; i < splittedInput.length; i++){

                char color = splittedInput[i].charAt(0);

                String splittedPart = splittedInput[i].substring(1, splittedInput[i].length());

                switch (color){
                    case '0': outputList.add(ChatColor.BLACK.toString() + splittedPart); break;
                    case '1': outputList.add(ChatColor.DARK_BLUE.toString() + splittedPart); break;
                    case '2': outputList.add(ChatColor.DARK_GREEN.toString() + splittedPart); break;
                    case '3': outputList.add(ChatColor.DARK_AQUA.toString() + splittedPart); break;
                    case '4': outputList.add(ChatColor.DARK_RED.toString() + splittedPart); break;
                    case '5': outputList.add(ChatColor.DARK_PURPLE.toString() + splittedPart); break;
                    case '6': outputList.add(ChatColor.GOLD.toString() + splittedPart); break;
                    case '7': outputList.add(ChatColor.GRAY.toString() + splittedPart); break;
                    case '8': outputList.add(ChatColor.DARK_GRAY.toString() + splittedPart); break;
                    case '9': outputList.add(ChatColor.BLUE.toString() + splittedPart); break;

                    case 'a': outputList.add(ChatColor.GREEN.toString() + splittedPart); break;
                    case 'b': outputList.add(ChatColor.AQUA.toString() + splittedPart); break;
                    case 'c': outputList.add(ChatColor.RED.toString() + splittedPart); break;
                    case 'd': outputList.add(ChatColor.LIGHT_PURPLE.toString() + splittedPart); break;
                    case 'e': outputList.add(ChatColor.YELLOW.toString() + splittedPart); break;
                    case 'f': outputList.add(ChatColor.WHITE.toString() + splittedPart); break;

                    case 'k': outputList.add(ChatColor.MAGIC.toString() + splittedPart); break;
                    case 'm': outputList.add(ChatColor.STRIKETHROUGH.toString() + splittedPart); break;
                    case 'n': outputList.add(ChatColor.UNDERLINE.toString() + splittedPart); break;
                    case 'l': outputList.add(ChatColor.BOLD.toString() + splittedPart); break;
                    case 'o': outputList.add(ChatColor.ITALIC.toString() + splittedPart); break;
                    case 'r': outputList.add(ChatColor.RESET.toString() + splittedPart); break;


                    default: outputList.add(splittedPart);
                }
            }
            output = outputList.stream().collect(Collectors.joining(""));




        }
        return output;

    }
}
