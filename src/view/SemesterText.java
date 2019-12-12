package view;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import model.Semestre;
import utils.ElementObserver;

public class SemesterText extends Text implements ElementObserver {
    public SemesterText(String txt) {
        super(txt);
        this.setFont(new Font("Roboto Bold", 25));
        this.setFill(Color.WHITE);
        this.setX(69);
        this.setStyle("-fx-text-alignment: center;");
        this.setY(50);
    }

    @Override
    public void update() {
        this.setText(String.valueOf(Semestre.getInstance().getSemestre()));
        if(Semestre.getInstance().getSemestre() > 99) {
            this.setX(53);
        }
        else if(Semestre.getInstance().getSemestre() > 9) {
            this.setX(61);
        }
    }
}
