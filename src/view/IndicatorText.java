package view;

import controller.ElementControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Indicator;
import model.IndicatorType;
import utils.ElementObserver;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class IndicatorText implements ElementObserver {
    private ElementControl _ec;
    private String _name;
    private Pane _pane;
    private Text _textValue;

    public IndicatorText(String name) throws Exception {
        _name = name;
        _ec = ElementControl.getInstance();
        Parent cbase = FXMLLoader.load(getClass().getResource("../resources/fxml/indicator_text.fxml"));
        Pane lroot = (Pane) cbase;;
        Text textName = (Text)lroot.getChildren().get(0); // Text[id=indtxt-name]
        textName.setText(name.toUpperCase());
        _pane = lroot;
        NumberFormat formatter = new DecimalFormat("##,###.##");
        _textValue = (Text)lroot.getChildren().get(1); // Text[id=indtxt-value]
        String txt = formatter.format(_ec.getElement(_name).getValue());

        Indicator ind = (Indicator) _ec.getElement(_name);
        if(ind.getType() == IndicatorType.PERCENTAGE) {
            txt = txt + " %";
        }
        _textValue.setText(txt);

        /*
        if(_name == "argent disponible") {
            NumberFormat formatter = new DecimalFormat("##,###.##");
            this.setText(formatter.format(Integer.valueOf(_EC.getElement(_name).toString().replaceAll("[^\\d.]", ""))) + " €");
            this.setFont(new Font("Roboto Bold", 32.0));
            this.setFill(Color.BLACK);
        } else {
           this.setText(_EC.getElement(_name).get_name());
           this.setFont(new Font("Roboto", 17.0));
           this.setFill(Color.WHITE);
        }
        _EC.getElement(name).add(this);

         */
        _ec.getElement(name).add(this);
    }

    public Pane getPane() {
        return _pane;
    }

    @Override
    public void update() {
        NumberFormat formatter = new DecimalFormat("##,###.##");
        String txt = formatter.format(_ec.getElement(_name).getValue());

        Indicator ind = (Indicator) _ec.getElement(_name);
        if(ind.getType() == IndicatorType.PERCENTAGE) {
            txt = txt + " %";
        }
        _textValue.setText(txt);

/*
        if(_name == "argent disponible") {
            NumberFormat formatter = new DecimalFormat("##,###.##");
            this.setText(formatter.format(Integer.valueOf(_EC.getElement(_name).toString().replaceAll("[^\\d.]", ""))) + " €");
        } else {
            this.setText(_EC.getElement(_name).toString());
        }*/
    }
}
