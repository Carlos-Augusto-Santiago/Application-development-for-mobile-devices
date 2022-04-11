package com.example.myapplication;

import android.os.*;
import androidx.fragment.app.Fragment;
import android.view.*;
public class Tab4 extends Fragment {
    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);
    }
    @Override
    public View onCreateView(LayoutInflater li, ViewGroup vg, Bundle bn) {
        Lienzo l = new Lienzo(this);
        setContentView(l);
        return li.inflate(R.layout.tab4, vg, false);
    }
}
