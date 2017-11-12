package android.example.com.yihubaiying.adapter;

import android.app.Dialog;
import android.content.Context;
import android.example.com.yihubaiying.R;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Thingvellir on 2017/11/2.
 */

public class ContactItem {

    private int userImageId;
    private String name;
    private int telImageId;

    public int getUserImageId() {
        return userImageId;
    }

    public void setUserImageId(int userImageId) {
        this.userImageId = userImageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTelImageId() {
        return telImageId;
    }

    public void setTelImageId(int telImageId) {
        this.telImageId = telImageId;
    }

    public ContactItem(int userImageId, String name, int telImageId) {
        this.userImageId = userImageId;
        this.name = name;
        this.telImageId = telImageId;
    }

    /**
     * Created by carnivalnian on 2017/11/1.
     */

    public static class FullScreenDialog extends DialogFragment {

        private View view;
        private Context context;

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            this.context = context;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyDialog);
            if (null == view) {
                view = LayoutInflater.from(context).inflate(R.layout.dialog_default, null);
            }
            builder.setView(view);
            return builder.create();
        }

        public void setContentView(View view) {
            this.view = view;
        }

        public View getContentView() {
            return view;
        }

        private int gravity = Gravity.CENTER;

        public void setGravity(int gravity) {
            this.gravity = gravity;
        }

        @Override
        public void onResume() {
            super.onResume();
            Window mWindow = getDialog().getWindow();
            WindowManager.LayoutParams mLayoutParams = mWindow.getAttributes();
            mLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mLayoutParams.gravity =gravity;
            mWindow.setAttributes(mLayoutParams);
        }
    }
}
