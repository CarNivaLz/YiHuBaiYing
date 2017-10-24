package android.example.com.yihubaiying.fragment.fragment_hongbaomap;

import android.support.v4.app.Fragment;

import static android.example.com.yihubaiying.fragment.fragment_hongbaomap.Fragment_HongBao.isInit;

/**
 * Created by carnivalnian on 2017/10/24.
 */

public abstract  class LazyFragment extends Fragment {

    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUsers){
        super.setUserVisibleHint(isVisibleToUsers);
        if(getUserVisibleHint()&&isInit){
            isVisible=true;
            onVisible();

        }else {
            isVisible=false;
            isInit=false;
            onInvisible();

        }
    }
    protected abstract void  lazyLoad();

    protected void onVisible(){
        lazyLoad();
    }

    protected void onInvisible(){

    }

}