package br.com.caelum.notasfiscais.util;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

public class ComponentResolver {

    public UIComponent findComponent(UIComponent component, String id) {

        if (component.getId().equals(id)) {
            return component;
        }

        UIComponent bingo = null;

        for (UIComponent filho : component.getChildren()) {
            bingo = this.findComponent(filho, id);
            if (bingo != null)
                return bingo;
        }

        return null;


    }

    public String getSubmittedValue(String idComponente) {
        UIViewRoot arvore = FacesContext.getCurrentInstance().getViewRoot();
        UIComponent componente = this.findComponent(arvore, idComponente);
        UIInput input = (UIInput) componente;
        return (String) input.getSubmittedValue();
    }

}
