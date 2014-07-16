/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webql;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "qrCodeController")  
@ViewScoped  
public class QRCodeController implements Serializable {  
  
    private static final long serialVersionUID = 20120316L;  
    private String renderMethod;  
    private String text;  
    private String label;  
    private int mode;  
    private int size;  
    private String fillColor;  
     
  
    public QRCodeController() {  
        renderMethod = "canvas";  
        text = "http://primefaces-extensions.github.io/";  
        label = "PF-Extensions";  
        mode = 2;  
        fillColor = "7d767d";  
        size = 200;  
    }  
  
    public String getRenderMethod() {  
        return renderMethod;  
    }  
  
    public void setRenderMethod(String renderMethod) {  
        this.renderMethod = renderMethod;  
    }  
  
    public String getText() {  
        return text;  
    }  
  
    public void setText(String text) {  
        this.text = text;  
    }  
  
    public String getLabel() {  
        return label;  
    }  
  
    public void setLabel(String label) {  
        this.label = label;  
    }  
  
    public int getMode() {  
        return mode;  
    }  
  
    public void setMode(int mode) {  
        this.mode = mode;  
    }  
  
    public String getFillColor() {  
        return fillColor;  
    }  
  
    public void setFillColor(String fillColor) {  
        this.fillColor = fillColor;  
    }  
  
    public int getSize() {  
        return size;  
    }  
  
    public void setSize(int size) {  
        this.size = size;  
    }  
          
}  