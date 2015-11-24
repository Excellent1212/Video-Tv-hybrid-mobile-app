package com.hitherejoe.vineyard.test.common;

import android.content.Context;

import com.hitherejoe.vineyard.VineyardApplication;
import com.hitherejoe.vineyard.data.DataManager;
import com.hitherejoe.vineyard.data.remote.VineyardService;
import com.hitherejoe.vineyard.test.common.injection.component.DaggerDataManagerTestComponent;
import com.hitherejoe.vineyard.test.common.injection.component.TestComponent;
import com.hitherejoe.vineyard.test.common.injection.module.DataManagerTestModule;

/**
 * Extension of DataManager to be used on a testing environment.
 * It uses DataManagerTestComponent to inject dependencies that are different to the
 * normal runtime ones. e.g. mock objects etc.
 * It also exposes some helpers like the DatabaseHelper or the Retrofit service that are helpful
 * during testing.
 */
public class TestDataManager extends DataManager {

    public TestDataManager(Context context) {
        super(context);
    }

    @Override
    protected void injectDependencies(Context context) {
        TestComponent testComponent = (TestComponent)
                VineyardApplication.get(context).getComponent();
        DaggerDataManagerTestComponent.builder()
                .testComponent(testComponent)
                .dataManagerTestModule(new DataManagerTestModule(context))
                .build()
                .inject(this);
    }

    public VineyardService getVineyardService() {
        return mVineyardService;
    }

}
