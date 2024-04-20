package com.example.fingermastergame.ui.manageFingers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fingermastergame.R;
import com.example.fingermastergame.ui.playerData.IssueModel;
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


public class CreateIssueController extends AppCompatActivity {
    private PlayerModel playerModel;
    private ImageButton /*plusButton, minusButton,*/ saveButton;
    private TextView /*numberTextView,*/ playerNameTextView, issueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playerModel = (PlayerModel) getIntent().getSerializableExtra(getString(R.string.player_data_model));
        setContentView(R.layout.activity_create_issue);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bindViews();
        configure();
        configureClicklisteners();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void configure() {
        this.playerNameTextView.setText(playerModel.getName().toUpperCase());
    }

    private void bindViews() {
        //this.plusButton = findViewById(R.id.manage_fingers_plus_button);
        //this.minusButton = findViewById(R.id.manage_fingers_minus_button);
        //this.numberTextView = findViewById(R.id.manage_fingers_number_textview);
        this.playerNameTextView = findViewById(R.id.manage_fingers_player_name_textview);
        this.saveButton = findViewById(R.id.manage_fingers_save_button);
        this.issueTextView = findViewById(R.id.manage_fingers_issue_textview);
    }

    private void configureClicklisteners() {
       /* plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int previousValue = Integer.parseInt(numberTextView.getText().toString());
                final int nextValue =previousValue+1;
                if (checkPlusButtons(nextValue)) {
                    numberTextView.setText(nextValue);
                }
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int previousValue = Integer.parseInt(numberTextView.getText().toString());
                final int nextValue = previousValue-1;
                if(checkPlusButtons(nextValue)){
                    numberTextView.setText(nextValue);
                }
            }
        });*/
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String issue = String.valueOf(issueTextView.getText());
                //final int fingers = Integer.parseInt(numberTextView.getText().toString());
                final IssueModel issueModel = new IssueModel(issue);
                final String playerName = playerModel.getName();

                if(issue.isBlank() || issue == null){
                    showToast(getBaseContext().getString(R.string.issue_empty));
                }else{
                    //updatePlayerData(playerName, fingers, issueModel);
                    updatePlayerData(playerName, 1, issueModel);
                    finish();
                }
            }
        });
        issueTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                final String text = charSequence.toString();
                    if(text.length() < 1 || text.isBlank()){
                        saveButton.setImageResource(R.drawable.lock_icon);
                    }else{
                        saveButton.setImageResource(R.drawable.save_icon);
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    private void updatePlayerData(String playerName, int fingers, IssueModel issueModel) {
        //save data
        playerModel.setIssue(issueModel);
        savePlayerData(playerModel);
    }

    private void savePlayerData(PlayerModel playerModel) {
        ArrayList<PlayerModel> arrayList = loadPlayers();
        final Gson gson = new Gson();
        final String playerDataFileName = getResources().getString(R.string.player_data_file_name);

        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).getName().equals(playerModel.getName())) {
                arrayList.remove(i);
                arrayList.add(playerModel);
            }
        }

        final PlayerModel[] list = ManageFingersUtils.arrayListToArray(arrayList);
        final String json = gson.toJson(list);

        try{
            FileOutputStream fos = openFileOutput(playerDataFileName, Context.MODE_PRIVATE);
            fos.write(json.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private ArrayList<PlayerModel> loadPlayers() {
        ArrayList<PlayerModel> playersList = new ArrayList<PlayerModel>();
        final File file = new File(getFilesDir(), getResources().getString(R.string.player_data_file_name));
        final Gson gson = new Gson();
        if (!file.exists()){
            return playersList;
        }
        try {
            final FileInputStream fis = openFileInput(file.getName());
            final InputStreamReader inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            final StringBuilder stringBuilder = new StringBuilder();
            final BufferedReader reader = new BufferedReader(inputStreamReader);
            final ManageFingersUtils utils =  ManageFingersUtils.getInstance();
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
            playersList = utils.arrayToArrayList(data);
            return playersList;
        } catch (IOException e) {
            e.printStackTrace();
            return playersList;
        }
    }
    private boolean checkPlusButtons(int currentValue) {
        final int maxValue = this.getResources().getInteger(R.integer.max_fingers_edit);
        final int minValue = this.getResources().getInteger(R.integer.min_fingers_edit);

        return currentValue <= maxValue && currentValue >= minValue;
    }

}