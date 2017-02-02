package dummy.ui.ResearchComponent;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	protected AbstractOrderedLayout paintUI()
	{
		final HorizontalLayout trial = new  HorizontalLayout();
		
	
		final TextField txtName = new TextField("Employee Name");
		final TextField txtAddress = new TextField("Address");
		final TextField txtHobby = new TextField("Hobby");
		
		
		txtName.setRequired(true);
		txtName.setIcon(FontAwesome.USER);
		txtName.addValidator(new NullValidator("Name is mandatory field",false));
		
		txtAddress.addValidator(new StringLengthValidator("Number of characters exceeded",5,15,true));
		txtAddress.setIcon(FontAwesome.HOME);
		
		txtHobby.setIcon(FontAwesome.BEER);
		
		trial.addComponents(txtName,txtAddress,txtHobby);
		trial.setMargin(true);
		trial.setSpacing(true);
		return trial;
		
	}
	
	protected GridLayout paintGridLayout()
	{
		GridLayout layout_grid = new GridLayout(4,4);
		layout_grid.setSpacing(true);
		layout_grid.setMargin(true);
		
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			{
				layout_grid.addComponent(new Button("Button"+i+j), j, i);
				
			}
		}
		
		return layout_grid;
	}
	
	

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
        });
        
        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        //layout.addComponent(paintUI());
       // layout.addComponent(paintGridLayout());
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
