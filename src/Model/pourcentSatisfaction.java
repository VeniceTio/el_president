package Model;

import View.IndicPanelDyn;

import java.util.Map;

public class pourcentSatisfaction implements AbstractFormula {
    public long getPoint(long value, String chaine){
        long nbPoint = 0;
        if (chaine=="r"){
            if (value>0){
                if (value>10000){
                    if(value>50000){
                        if(value>100000){
                            if(value>1000000){
                                nbPoint = 32;
                            }
                            else {
                                nbPoint = 16;
                            }
                        }
                        else {
                            nbPoint = 8;
                        }
                    }
                    else {
                        nbPoint = 4;
                    }
                }
                else {
                    nbPoint = 2;
                }
            }
        }
        return nbPoint;
    }
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {
        long dif;
        if(indicator.getFormula().get(lever) =="3"){
            if (lever.getOldValue() < lever.getValue()) {
                dif = ((lever.getValue() / 3) - (lever.getOldValue() / 3));
                indicator.setValue(indicator.getValue() + dif);
            }
            else {
                dif = (lever.getOldValue() / 3) - (lever.getValue() / 3);
                indicator.setValue(indicator.getValue() - dif);
            }
        }
        else{

            long oldValue = getPoint(lever.getOldValue(),indicator.getFormula().get(lever));
            long IndicValue = getPoint(lever.getValue(),indicator.getFormula().get(lever));
            if (oldValue < IndicValue) {
                indicator.setValue(indicator.getValue()+(IndicValue-oldValue));
            }
            else{
                indicator.setValue(indicator.getValue()-(oldValue-IndicValue));
            }
        }
        //System.out.println(oldValue);
        if (indicator.getValue()>100){
            indicator.setValue(100);
        }
    }

    @Override
    public void updateByLevers(Indicator indicator) {
        indicator.setValue(0);
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            //System.out.println(mapEntry);
            if (mapEntry.getValue()=="r"){                                        //à modifier par des courbes
                if (((AbstractElement) mapEntry.getKey()).getValue()>0){
                    if (((AbstractElement) mapEntry.getKey()).getValue()>10000){
                        if(((AbstractElement) mapEntry.getKey()).getValue()>50000){
                            if(((AbstractElement) mapEntry.getKey()).getValue()>100000){
                                if(((AbstractElement) mapEntry.getKey()).getValue()>1000000){
                                    indicator.setValue(indicator.getValue() + 33);
                                }
                                else {
                                    indicator.setValue(indicator.getValue() + 16);
                                }
                            }
                            else {
                                indicator.setValue(indicator.getValue() + 8);
                            }
                        }
                        else {
                            indicator.setValue(indicator.getValue() + 4);
                        }
                    }
                    else {
                        indicator.setValue(indicator.getValue() + 2);
                    }
                }
            }
            else if(mapEntry.getValue() =="3"){
                indicator.setValue(indicator.getValue() + (((AbstractElement)mapEntry.getKey()).getValue()/3));

            }
            else if (mapEntry.getValue() =="25"){
                long rapport = ((AbstractElement) mapEntry.getKey()).getValue();
                System.out.println("##########");
                System.out.println("#   "+rapport+"   #");
                System.out.println("##########");
                if (rapport<11){
                    indicator.setValue(indicator.getValue() + (100/3));
                }
                else if (rapport<21){
                    indicator.setValue(indicator.getValue() + ((-rapport+110)/3));
                }
                else if (rapport<31){
                    indicator.setValue(indicator.getValue() + ((-4*rapport+170)/3));
                }
                else{
                    indicator.setValue(indicator.getValue() + ((-5*rapport+200)/3));
                }
            }
        }
        if (indicator.getValue()>100){
            indicator.setValue(100);
        }
        if (indicator.getValue()<0){
            indicator.setValue(0);
        }
    }
}
