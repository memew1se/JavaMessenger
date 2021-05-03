package messenger.controllers;

import messenger.App;

public class BaseController {

    protected App application;

    public void setApplication(App application) {
        this.application = application;
    }

    public App getApplication() {
        return application;
    }

}
