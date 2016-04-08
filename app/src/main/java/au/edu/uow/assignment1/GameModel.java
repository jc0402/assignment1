package au.edu.uow.assignment1;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Uer on 4/6/2016.
 */
public class GameModel {
    public class TileData
    {
        public Drawable image; // image object
        public int imageID; // image identifier

        public TileData(int tempImageID)
        {
            imageID = tempImageID;
        }

        public String imageDescription()
        {
            return Integer.toString(imageID);
        }
    }

    private int lastTileTapped; // index of last tile tapped
    private int secondLastTileTapped; // index of the second last tile tapped
    public ArrayList<TileData> tileArray; //  array of TileData structures to represent initial game state
    private Boolean turnIdentification; // to check whether is first turn or second turn
    private int counterNumMatchTile; // the counter for the number of matched tiles to determine when game complete
    View.OnClickListener interfaceReferenceHandler; // interface reference
    private float gScore; //to keep track game score

    public GameModel(int tempNumofTile, ArrayList<Integer> tempImageArray)
    {
        turnIdentification = true;
        counterNumMatchTile = 0;
        tileArray = new ArrayList<TileData>(tempNumofTile);

        Log.v("", "Constructor");

        reset(tempNumofTile, tempImageArray); // function to reset all the game variables
    }

    //reset function
    // function for setting up the initial game state
    public void reset(int resetTempNumofTile, ArrayList<Integer> tempImageArray)
    {
        lastTileTapped = -1;
        secondLastTileTapped = -1;
        counterNumMatchTile = 0; // NULL
        gScore = 0; // NULL

        Log.v("", "Game Reset");

        int countofImage = 0; // the image counter
        int i = 0; // increment variables

        while (i <= resetTempNumofTile - 1)
        {
            tileArray.add(new TileData(tempImageArray.get(countofImage)));
            i++;

            tileArray.add(new TileData(tempImageArray.get(countofImage)));
            i++;

            countofImage++;

            // to check the array size
            if (countofImage == tempImageArray.size())
            {
                countofImage = 0;
            }
        }

        shuffle(tileArray); // function to shuffle the image in the array
    }

    //Shuffle function
    // Shuffle Code Segment Reference
    //http://www.programcreek.com/2012/02/java-method-to-shuffle-an-int-array-with-random-order/
    public void shuffle(ArrayList<TileData> tileArray)
    {
        Random tempRandomShuffle = new Random();
        int randomSwap;

        for ( int i = 0; i < tileArray.size(); i++)
        {
            randomSwap = tempRandomShuffle.nextInt(i + 1 );
            TileData tempswappingImageArray = tileArray.get(randomSwap);
            tileArray.set(randomSwap, tileArray.get(i));
            tileArray.set(i, tempswappingImageArray);
        }
    }

    // pushTileIndex function
    public void pushTileIndex(int tempPushTileIndex)
    {
        if (lastTileTapped == -1)
        {
            lastTileTapped = tempPushTileIndex;
        }

        else
        {
            secondLastTileTapped = lastTileTapped;
            lastTileTapped = tempPushTileIndex;
        }

        // to check the whether is first turn and assign score to the match or dismatch image
        if (turnCheckedTrue())
        {
            if (tileArray.get(lastTileTapped).imageID == tileArray.get(secondLastTileTapped).imageID)
            {
                gScore += 200; // a image match adds 200 points to the game score
            }

            else
            {
                gScore -= 100;
            }
        }
    }

    // function for Description
    public String imageDescription()
    {
        String tempDescription = "Game Description: ";

        for(int i = 0; i < tileArray.size(); i++)
        {
            tempDescription += tileArray.get(i).imageDescription();
            tempDescription += "\n ...";
        }

        return tempDescription;
    }

    public float getgScore()
    {
        return gScore;
    }

    // function to return the last tile tapped
    public int getLastTileTapped()
    {
        return lastTileTapped;
    }

    // function set last tile tapped
    public void setLastTileTapped(int templastTileTapped)
    {
        lastTileTapped = templastTileTapped;
    }
    // function to return the second last tile tapped
    public int getSecondLastTileTapped()
    {
        return secondLastTileTapped;
    }

    // function to set second last tile tapped
    public void setSecondLastTileTapped(int tempSecondLastTileTapped)
    {
        secondLastTileTapped = tempSecondLastTileTapped;
    }
    // function get Image ID
    public int getImageID(int temptileImageID)
    {
        return tileArray.get(temptileImageID).imageID;
    }

    public TileData tempGetTileData(int i)
    {
        return tileArray.get(i);
    }

    public boolean turnCheckedTrue()
    {
        return turnIdentification;
    }

    public void setturnIdentification()
    {
        if (turnIdentification)
        {
            turnIdentification = true; // first turn
        }

        else
        {
            turnIdentification = false;
        }
    }

    // function to check match count
    public void counterOfMatchImage()
    {
        counterNumMatchTile++;
    }

    // function to update the game score
    public void updategScore(float tempgScore)
    {
        gScore = tempgScore; // update new game score
    }

    public interface delegateMethods
    {
        void gameDidComplete(GameModel tempGameModel);
        void didMatchTile(GameModel tempGameModel, int temptilenum, int pointToPreviousTile);
        void didFailToMatchTile(GameModel tempGameModel, int temptilenum, int pointToPreviousTile);
        void scoreDidUpdate(GameModel tempgamemodel, float tempNewScore);
    }

}
