package au.edu.uow.assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
TileView.imageTileViewList{

    private static ArrayList<Integer> tileArray;
    private static int index = 12;
    private GameModel gameModel;
    private  TileView[] tempTileView;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TypedArray temptypedaray = getResources().obtainTypedArray(R.array.temptypedaray);
        tileArray = new ArrayList<Integer>(3);
        tileArray.add(R.drawable.baldhill);
        tileArray.add(R.drawable.cathedral);
        tileArray.add(R.drawable.lake);

        score = (TextView)findViewById(R.id.score);
        gameModel = new GameModel(index, tileArray);

        Log.v("", gameModel.imageDescription());

        // array of list
        tempTileView = new TileView[] {
                (TileView) findViewById(R.id.l1),
                (TileView) findViewById(R.id.l2),
                (TileView) findViewById(R.id.l3),
                (TileView) findViewById(R.id.l4),
                (TileView) findViewById(R.id.l5),
                (TileView) findViewById(R.id.l6),
                (TileView) findViewById(R.id.l7),
                (TileView) findViewById(R.id.l8),
                (TileView) findViewById(R.id.l9),
                (TileView) findViewById(R.id.l10),
                (TileView) findViewById(R.id.l11),
                (TileView) findViewById(R.id.l12)
        };


        for ( int i = 0; i < index; i++)
        {
            // retrive and set image
            tempTileView[i].image = gameModel.getImageID(i);
            tempTileView[i].interfaceVariable = i;
            tempTileView[i].coverStoredImage();
        }
    }

    public void onClick (View view)
    {
        for ( int i = 0; i < index; i++)
        {
            if (view == tempTileView[i])
            {
                didSelectTile(tempTileView[i]);
                Log.v("", tempTileView[i].toString());
                tempTileView[i].revealStoredImage();
            }
        }
    }

    public void didSelectTile(TileView tempTileView)
    {
        gameModel.pushTileIndex(tempTileView.interfaceVariable);
        gameModel.setturnIdentification();
    }

    public void gameDidComplete (GameModel gameModel)
    {
        // alert dialog for the game score
        AlertDialog tempAlertGameScore = new AlertDialog.Builder(MainActivity.this).create();

        tempAlertGameScore.setTitle("Game Score = "); // to show game score
        tempAlertGameScore.setMessage("" + gameModel.getgScore()); // to show game message

        // game button
        tempAlertGameScore.setButton(AlertDialog.BUTTON_NEUTRAL, "Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        tempAlertGameScore.show(); // function to show score

        tileArray = new ArrayList<Integer>(3);
        tileArray.add(R.drawable.lake);
        tileArray.add(R.drawable.baldhill);
        tileArray.add(R.drawable.cathedral);
        gameModel.reset(index, tileArray);
    }

    public void didMatchTile(GameModel tempGameModel, int temptilenum, int pointToPreviousTile)
    {
        tempTileView[temptilenum].tempHideTileView();
        tempTileView[pointToPreviousTile].tempHideTileView();
    }

    public void didFailToMatchTile(GameModel tempGameModel, int temptilenum, int pointToPreviousTile)
    {
        tempTileView[temptilenum].coverStoredImage();
        tempTileView[pointToPreviousTile].coverStoredImage();
    }

    public void scoreDidUpdate(GameModel tempgamemodel, float tempNewScore) {
        gameModel.updategScore(tempNewScore);
        score.setText("Game Score" + gameModel.getgScore());
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.tempmenu, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem tempitem)
    {
        int tempID = tempitem.getItemId();

        switch (tempitem.getItemId())
        {
            case R.id.l1:
                return true;
            case R.id.l2:
                return true;
            case R.id.l3:
                return true;
            case R.id.l4:
                return true;
            case R.id.l5:
                return true;
            case R.id.l6:
                return true;
            case R.id.l7:
                return true;
            case R.id.l8:
                return true;
            case R.id.l9:
                return true;
            case R.id.l10:
                return true;
            case R.id.l11:
                return true;
            case R.id.l12:
                return true;
            default:
            return super.onOptionsItemSelected(tempitem);
        }

    }

}
