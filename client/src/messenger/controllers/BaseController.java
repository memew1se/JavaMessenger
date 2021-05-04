package messenger.controllers;

import messenger.App;

/**
 * Super class for other controllers
 */
public class BaseController {

    protected App application;

    /**
     * Sets application
     *
     * @param application the application
     */
    public void setApplication(App application) {
        this.application = application;
    }

    /**
     * @return Gets application
     */
    public App getApplication() {
        return application;
    }

}
