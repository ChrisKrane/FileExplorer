package file_explorer.ui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertHandler {

    enum ValidAlerts {
        ERROR, 
        WARNING,
        INFORMATION, 
        CONFIRMATION;
    }
    
    //Creates an alert with the given parameters
    public Alert createAlert(String title, String header, String content, AlertType type) {
        if(!isValidType(type.name())) {
            throw new IllegalArgumentException("Invalid alert type.");
        }
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        return alert;
    }

    //Checks if the given type is a valid AlertType
    private boolean isValidType(String type) {
        for(ValidAlerts alertType : ValidAlerts.values()) {
            if(alertType.name().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
