package com.google.android.systemui;

import android.app.AlarmManager;
import android.content.Context;

import com.android.systemui.res.R;
import com.android.systemui.qs.QsEventLogger;
import com.android.systemui.VendorServices;
import com.android.systemui.statusbar.phone.CentralSurfaces;

import com.google.android.systemui.ambientmusic.AmbientIndicationContainer;
import com.google.android.systemui.ambientmusic.AmbientIndicationService;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Lazy;

public class GoogleServices extends VendorServices {
    private final Context mContext;
    private final ArrayList<Object> mServices;
    private final CentralSurfaces mCentralSurfaces;
    private final AlarmManager mAlarmManager;
    private final QsEventLogger mUiEventLogger;

    @Inject
    public GoogleServices(Context context, AlarmManager alarmManager, CentralSurfaces centralSurfaces, QsEventLogger uiEventLogger) {
        super();
        mContext = context;
        mServices = new ArrayList<>();
        mAlarmManager = alarmManager;
        mCentralSurfaces = centralSurfaces;
        mUiEventLogger = uiEventLogger;
    }

    @Override
    public void start() {
        AmbientIndicationContainer ambientIndicationContainer = (AmbientIndicationContainer) mCentralSurfaces.getNotificationShadeWindowView().findViewById(R.id.ambient_indication_container);
        ambientIndicationContainer.initializeView(mContext, mCentralSurfaces, ambientIndicationContainer);
        addService(new AmbientIndicationService(mContext, ambientIndicationContainer, mAlarmManager));
    }

    private void addService(Object obj) {
        if (obj != null) {
            mServices.add(obj);
        }
    }
}
