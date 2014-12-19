package cz.raptors.dianotes.pages.logged.values.add;

import java.util.Date;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import cz.raptors.dianotes.models.GlucoseModel;



/**
 * The glucose panel, which is part of the add values panel.
 *
 * @author Jaroslav Strouhal
 * @param <T>
 * 
 * @see cz.raptors.wicketsample.pages.logged.values.AddValues
 */
public class Glucose
    extends Panel {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 4981983133398470837L;


	/**
     * Creates new instance of the glucose panel.
     * 
     * @param id wicket id
     */
    public Glucose(final String id) {
        super(id);
    }


    /**
     * Creates a new instance of the glucose panel, which will be setted up
     * by the model.
     * 
     * @param id wicket id
     * @param model the model
     */
    public Glucose(final String id, final IModel<?> model) {
    	 super(id, model);
    	Form<GlucoseModel> form = new Form<GlucoseModel>("addGlucoseForm"){
        	@Override
        	protected void onSubmit() {
        		GlucoseModel glucoseModel = getModelObject();
        		super.onSubmit();
        	}
        };
        form.setDefaultModel(new CompoundPropertyModel<>(new GlucoseModel()));
        form.add(new Label("inputGlucoseValueLabel", getString("ValuesPage.addValuesTab.glucoseValue")));
        form.add(new TextField<Number>("inputGlucoseValue").setRequired(true));
        form.add(new Label("dateAndTimeOfMeasureLabel", getString("ValuesPage.addValuesTab.dateAndTimeOfMeasure")));
        form.add(new TextField<Date>("inputDateAndTimeOfMeasure").setRequired(true));
        form.add(new Label("submitGlucose", getString("ValuesPage.addValuesTab.addValueGlucose")));
        add(form);
			
      
        init();
        
    }


	private void init() {
		
        

		}
}
