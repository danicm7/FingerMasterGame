package com.example.fingermastergame.ui.storage;

public class ManageStorage {
   /* final private Context context;
    final private String playerDataFileName;
    final private Gson gson = new Gson();
    final private File file;

   public ManageStorage(Context c){
        this.context = c;
        playerDataFileName = context.getResources().getString(R.string.player_data_storage_path);
        file = new File(context.getFilesDir(), playerDataFileName);
    }

    public void setPlayerData(PlayerModel playerDataModel){
        PlayerModel[] list = getAllPlayersData();
        ArrayList<PlayerModel> arrayList = arrayToArrayList(list);

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
    public void saveNewPlayerData(PlayerModel playerDataModel){
        PlayerModel[] list = getAllPlayersData();
        ArrayList<PlayerModel> arrayList = arrayToArrayList(list);
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

    private PlayerModel[] getAllPlayersData() {
        if (!file.exists()){
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

    public ArrayList<PlayerModel> getAllPlayers(){
        return arrayToArrayList(getAllPlayersData());
    }

    private PlayerModel[] arrayListToArray(ArrayList<PlayerModel> playerDataModelArrayList){
        final int size= playerDataModelArrayList.size();
        return playerDataModelArrayList.toArray(new PlayerModel[size]);
    }

    private ArrayList<PlayerModel> arrayToArrayList (PlayerModel[] array){
        return new ArrayList<PlayerModel>(Arrays.asList(array));
    }*/
}
