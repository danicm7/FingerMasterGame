package com.example.fingermastergame.storage;

import android.content.Context;
import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.playerData.PlayerModel;
import com.example.fingermastergame.ui.utils.ManageFingersUtils;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public final class ManageStorage {
    private static final Gson gson = new Gson();

    public static void setPlayerData(Context context, PlayerModel playerDataModel){
        final String playerDataFileName = context.getResources().getString(R.string.player_data_file_name);

        PlayerModel[] list = getAllPlayersData(context);
        ArrayList<PlayerModel> arrayList = ManageFingersUtils.arrayToArrayList(list);

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getName().equals(playerDataModel.getName())) {
                arrayList.remove(i);
                arrayList.add(playerDataModel);
            }
        }

        list = ManageFingersUtils.arrayListToArray(arrayList);
        final String json = gson.toJson(list);

        try{
            FileOutputStream fos = context.openFileOutput(playerDataFileName, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void saveNewPlayerData(Context context, PlayerModel playerDataModel){
        final String playerDataFileName = context.getResources().getString(R.string.player_data_file_name);

        PlayerModel[] list = getAllPlayersData(context);
        ArrayList<PlayerModel> arrayList = ManageFingersUtils.arrayToArrayList(list);
        arrayList.add(playerDataModel);
        list = ManageFingersUtils.arrayListToArray(arrayList);

        final String json = gson.toJson(list);

        try{
            FileOutputStream fos = context.openFileOutput(playerDataFileName, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<PlayerModel> loadPlayers(Context context){
        return ManageFingersUtils.arrayToArrayList(getAllPlayersData(context));
    }

    private static PlayerModel[] getAllPlayersData(Context context) {
        final String playerDataFileName = context.getResources().getString(R.string.player_data_file_name);
        final File file = new File(context.getFilesDir(), playerDataFileName);

        if (!file.exists() || file.length() == 0){
            return new PlayerModel[0];
        }
        try {
            FileInputStream fis = context.openFileInput(playerDataFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
            final String contents = stringBuilder.toString();
            final PlayerModel[] data = gson.fromJson(contents, PlayerModel[].class);

            fis.close();
            inputStreamReader.close();
            reader.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}