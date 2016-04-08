package au.edu.uow.assignment1;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Uer on 4/6/2016.
 */
public class TileView extends LinearLayout {

    public int image; // image
    ImageView tempImageView; // image view
    public int interfaceVariable; // tile variables

    public TileView(Context tempContext, AttributeSet tempAttributesSet)
    {
        super(tempContext, tempAttributesSet);
        LinearLayout tempLinearLayout = new LinearLayout(tempContext);
        LinearLayout.LayoutParams tempLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);

        tempImageView = new ImageView(tempContext);
        addView(tempImageView, tempLayoutParams);
        setOnClickListener(tempListener);
    }

    View.OnClickListener tempListener = new View.OnClickListener()
    {
        public void onClick(View view)
        {
            image = R.drawable.baldhill;
            tempImageView.setImageResource(image);
        }
    };

    // function to reveal the stored image
    public void revealStoredImage()
    {
        tempImageView.setImageResource(image);
    }

    // function to cover the stored image
    public void coverStoredImage()
    {
        tempImageView.setImageResource(R.drawable.question);
    }

    // function to hide the TileView image if two tiles successfully matched
    public void tempHideTileView()
    {
        tempImageView.setImageDrawable(null);//setImageDrawable(null);
    }

    public interface imageTileViewList
    {
        void didSelectTile(TileView tileView);
    }
}
