package test;
import org.junit.rules.ExternalResource;

import datasources.PayRollDatabase;

public class DatabaseResource extends ExternalResource {
    protected PayRollDatabase instance;

    @Override
    public void before(){
        instance = PayRollDatabase.getInstance();
    }

    @Override
    public void after(){
        instance.clear();
    }

    public PayRollDatabase getInstance() {
        return instance;
    }
}