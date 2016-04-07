package au.edu.uow.assignment1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
TileView.TileViewListener{

    private static int index = 12;
    private static ArrayList<Integer> tileArray;
    private GameModel gameModel;
    private  TileView[] tempTileView;
    private TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tileArray = new ArrayList<Integer>(3);
        tileArray.add(R.drawable.lake);
        tileArray.add(R.drawable.baldhill);
        tileArray.add(R.drawable.cathedral);

        score = (TextView)findViewById(R.id.score);
        gameModel = new GameModel(index, tileArray);

        Log.v("", gameModel.imageDescription());

        // array of list
        tempTileView = new TileView[]
                {
                        (TileView) findViewById(R.id.11),
                        (TileView) findViewById(R.id.12),
                        (TileView) findViewById(R.id.13),
                        (TileView) findViewById(R.id.14),
                        (TileView) findViewById(R.id.15),
                        (TileView) findViewById(R.id.16),
                        (TileView) findViewById(R.id.17),
                        (TileView) findViewById(R.id.18),
                        (TileView) findViewById(R.id.19),
                        (TileView) findViewById(R.id.110),
                        (TileView) findViewById(R.id.111),
                        (TileView) findViewById(R.id.112)
        };

        for ( int i = 0; i < index; i++)
        {
            // retrive and set image
            tempTileView[i]
        }
    }
}
