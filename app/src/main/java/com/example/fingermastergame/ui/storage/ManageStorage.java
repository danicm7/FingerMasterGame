package com.example.fingermastergame.ui.storage;

import android.content.Context;

import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.playerData.PlayerDataModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ManageStorage {
    final private Context context;
    final private String playerDataFileName;
    final private Gson gson = new Gson();
    final private File file;

    public ManageStorage(Context c){
        this.context = c;
        playerDataFileName = context.getResources().getString(R.string.player_data_storage_path);
        file = new File(context.getFilesDir(), playerDataFileName);
    }

    public void setPlayerData(PlayerDataModel playerDataModel){
        PlayerDataModel[] list = getAllPlayersData();
        ArrayList<PlayerDataModel> arrayList = arrayToArrayList(list);

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getName().equals(playerDataModel.getName())) {
                arrayList.remove(i);
                arrayList.add(playerDataModel);
            }
        }

        list = arrayListToArray(arrayList);
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
    public void saveNewPlayerData(PlayerDataModel playerDataModel){
        PlayerDataModel[] list = getAllPlayersData();
        ArrayList<PlayerDataModel> arrayList = arrayToArrayList(list);
        arrayList.add(playerDataModel);
        list = arrayListToArray(arrayList);

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

    private PlayerDataModel[] getAllPlayersData() {
        if (!file.exists()){
            return new PlayerDataModel[0];
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
            final PlayerDataModel[] data = gson.fromJson(contents, PlayerDataModel[].class);

            fis.close();
            inputStreamReader.close();
            reader.close();
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<PlayerDataModel> getAllPlayers(){
        return arrayToArrayList(getAllPlayersData());
    }

    private PlayerDataModel[] arrayListToArray(ArrayList<PlayerDataModel> playerDataModelArrayList){
        final int size= playerDataModelArrayList.size();
        return playerDataModelArrayList.toArray(new PlayerDataModel[size]);
    }

    private ArrayList<PlayerDataModel> arrayToArrayList (PlayerDataModel[] array){
        return new ArrayList<PlayerDataModel>(Arrays.asList(array));
    }
}
