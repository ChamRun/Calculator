import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Controller {

    boolean readyToReset = false;

    @FXML
    public Text screen;

    @FXML
    public void write(ActionEvent e){
        addToScreen(((Button) e.getSource()).getText());
    }

    @FXML
    public void backSpace(){
        screen.setText(screen.getText().substring(0, screen.getText().length() - 1));

        if (screen.getText().equals(""))
            screen.setText("0");
    }

    @FXML
    public void sin(){
            screen.setText(String.valueOf(Math.sin(Double.parseDouble(screen.getText()))).substring(0, 13));
            readyToReset = true;
    }


    @FXML
    public void onPic(Event mouseEvent){
        System.out.println(mouseEvent);
    }

    @FXML
    public void cos(){
        screen.setText(String.valueOf(Math.cos(Double.parseDouble(screen.getText()))).substring(0, 13));
        readyToReset = true;
    }

    @FXML
    public void tan(){
        screen.setText(String.valueOf(Math.tan(Double.parseDouble(screen.getText()))).substring(0, 13));
        readyToReset = true;
    }

    @FXML
    public void cot(){
        screen.setText(String.valueOf(1 / Math.tan(Double.parseDouble(screen.getText()))).substring(0, 13));
        readyToReset = true;
    }

    @FXML
    public void clear(){
        screen.setText("0");
    }

    @FXML
    public void calculate(){
        String result = "";
        try {
            result = (new ScriptEngineManager()).getEngineByName("JavaScript")
                    .eval(screen.getText()).toString();
        } catch (ScriptException e) {
            e.printStackTrace();
        }

        if (13 < result.length()){
            result = result.substring(0, 13);
        }

        screen.setText(result);
        readyToReset = true;
    }

    public void addToScreen(String number) {
        if (readyToReset){
            screen.setText("0");
            readyToReset = false;
        }

        if (screen.getText().equals("0"))
            screen.setText(number);
        else if (screen.getText().length() < 26)
            screen.setText(screen.getText() + number);
    }


}
