package com.fanny.traxivity.realm;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;

import com.fanny.traxivity.model.Day;

import io.realm.Realm;

/**
 * Created by huextrat on 18/04/2017.
 */

public class RealmController {
    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {
        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {
        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {
        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    public void refresh() {
        realm.setAutoRefresh(true);
    }

    public void clearAll() {
        realm.beginTransaction();
        realm.delete(Day.class);
        realm.commitTransaction();
    }

    /*
        Day database
     */
    public Day getDay(String id) {
        return realm.where(Day.class).equalTo("idDay", id).findFirst();
    }

    public boolean hasDay() {
        return !realm.where(Day.class).findAll().isEmpty();
    }
}
