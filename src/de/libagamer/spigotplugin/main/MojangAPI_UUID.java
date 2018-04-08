package de.libagamer.spigotplugin.main;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MojangAPI_UUID {


    public static String getUuid(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/"+name;
        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(url));
            if(UUIDJson.isEmpty()) return "invalid name";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            String input = UUIDObject.get("id").toString();
            String output = parseUUID(input);
            return output;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }

    public static String getTrimmedUUID(String name){

        String url = "https://api.mojang.com/users/profiles/minecraft/"+name;
        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(url));
            if(UUIDJson.isEmpty()) return "invalid name";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            String output = UUIDObject.get("id").toString();
            return output;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return "error";

    }

    public static String getTexture(String uuid){

        String url = "https://sessionserver.mojang.com/session/minecraft/profile/"+uuid;
        try {
            String UUIDJson = IOUtils.toString(new URL(url));
            if(UUIDJson.isEmpty()) return "error";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            String output = UUIDObject.get("properties").toString();

            System.out.println(output);
            return output;

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return "error";

    }

    private static String parseUUID(String input){

        List<String> stringList = new ArrayList<>();

        for (int i = 0; i < 32; i++){

            if(i == 8 || i == 12 || i == 16 || i == 20){
                stringList.add("-");
                stringList.add(String.valueOf(input.charAt(i)));
            }
            else {
                stringList.add(String.valueOf(input.charAt(i)));
            }

        }

        String uuid = stringList.stream().collect(Collectors.joining(""));
        System.out.println(uuid);
        return uuid;

    }

}
