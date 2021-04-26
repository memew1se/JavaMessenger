package messenger.requests;

import messenger.App;

public class BaseController {

    protected App application;

    public void setApplication(App application) {
        this.application = application;
    }

}
