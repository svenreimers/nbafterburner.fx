package com.airhacks.netbeans.afterburnerfx;

import java.awt.Component;
import java.io.IOException;
import java.util.*;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle;

/**
 * Wizard to create a new set of files for the Afterburner.fx architecture pattern
 *
 * @author Sven Reimers
 */
//@TemplateRegistration(folder = "javafx", displayName = "Afterburner.fx View", 
//        content={"resources/templates/FXMLView.java.template"}, scriptEngine = "freemarker")
public class AfterburnerFXTemplateWizardIterator implements WizardDescriptor.InstantiatingIterator<WizardDescriptor> {
    static String NAME = "PROP_NAME";
    static String JAVA_DIR = "PROP_JAVA_DIR";
    static String GENERATE_CSS = "PROP_CSS";
    static String GENERATE_BUNDLE = "PROP_BUNDLE";
    static String GENERATE_CONFIG = "PROP_CONFIGURATION";
        
    private WizardDescriptor wizard;
    private transient int index;
    private transient WizardDescriptor.Panel[] panels;

    public static WizardDescriptor.InstantiatingIterator<WizardDescriptor> create() {
        return new AfterburnerFXTemplateWizardIterator();
    }

    private AfterburnerFXTemplateWizardIterator() {
    }

    @Override
    public String name() {
        switch (index) {
            default:
            case 0:
                return NbBundle.getMessage(AfterburnerFXTemplateWizardIterator.class, "LBL_ConfigureAfterburnerPanel_Name"); // NOI18N
            // add further steps here
        }
    }

    @Override
    public void initialize(WizardDescriptor wizard) {
        this.wizard = wizard;

        Project project = Templates.getProject(wizard);
        if (project == null) {
            throw new IllegalStateException(
                    NbBundle.getMessage(AfterburnerFXTemplateWizardIterator.class,
                    "MSG_ConfigureAfterburnerPanel_Project_Null_Error")); // NOI18N
        }
        panels = createPanels(project);
        String[] steps = createSteps();
        for (int i = 0; i < panels.length; i++) {
            Component c = panels[i].getComponent();
            if (steps[i] == null) {
                // Default step name to component name of panel.
                // Mainly useful for getting the name of the target
                // chooser to appear in the list of steps.
                steps[i] = c.getName();
            }
            if (c instanceof JComponent) { // assume Swing components
                JComponent jc = (JComponent)c;
                // Step #.
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                // Step name (actually the whole list for reference).
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
            }
        }
    }

    private WizardDescriptor.Panel[] createPanels(Project project) {
        return new WizardDescriptor.Panel[]{
                    new ConfigureAfterburnerVisual.Panel(project),
                };
    }

    private String[] createSteps() {
        return new String[] {
            NbBundle.getMessage(AfterburnerFXTemplateWizardIterator.class,"LAB_AfterburnerFXStep0"),
            NbBundle.getMessage(AfterburnerFXTemplateWizardIterator.class,"LAB_AfterburnerFXStep1"),
        };
    }
    
    @Override
    public void uninitialize(WizardDescriptor wizard) {
    }

    @Override
    public Set instantiate() throws IOException, IllegalArgumentException {

        Map<String, String> params = new HashMap<String, String>();
//        if (controllerFullName != null) {
//            params.put("controller", controllerFullName); // NOI18N
//        }
//        if (cssFullName != null) {
//            //remove file extension from name
//            cssFullName = cssFullName.substring(0, cssFullName.length() - CSS_FILE_EXTENSION.length());
//            // normalize path
//            cssFullName = cssFullName.replace("\\", "/"); // NOI18N
//        
//            params.put("css", cssFullName); // NOI18N
//        }
//        if (cssName != null) {
//            //remove file extension from name
//            cssName = cssName.substring(0, cssName.length() - CSS_FILE_EXTENSION.length());
//        }

        Set set = new HashSet();
        
        FileObject dirFileObject = (FileObject) wizard.getProperty(JAVA_DIR);
        String name = (String) wizard.getProperty(NAME);
        
        FileObject viewTemplate = FileUtil.getConfigFile("Templates/javafx/FXMLView.java"); // NOI18N
        DataObject viewTemplateDataObject = DataObject.find(viewTemplate);
        FileObject presenterTemplate = FileUtil.getConfigFile("Templates/javafx/FXMLPresenter.java"); // NOI18N
        DataObject presenterTemplateDataObject = DataObject.find(presenterTemplate);
        FileObject fxmlTemplate = FileUtil.getConfigFile("Templates/javafx/FXML.fxml"); // NOI18N
        DataObject fxmlTemplateDataObject = DataObject.find(fxmlTemplate);
        FileObject bundleTemplate = FileUtil.getConfigFile("Templates/javafx/FXMLBundle.properties"); // NOI18N
        DataObject bundleTemplateDataObject = DataObject.find(bundleTemplate);
        FileObject configurationTemplate = FileUtil.getConfigFile("Templates/javafx/FXMLConfiguration.properties"); // NOI18N
        DataObject configurationTemplateDataObject = DataObject.find(configurationTemplate);
        FileObject cssTemplate = FileUtil.getConfigFile("Templates/javafx/FXMLCss.css"); // NOI18N
        DataObject cssTemplateDataObject = DataObject.find(cssTemplate);
            
        FileObject packageFolder = dirFileObject.createFolder(name.toLowerCase());
        DataFolder packageDataObject = DataFolder.findFolder(packageFolder); 
        
        DataObject viewObject = viewTemplateDataObject.createFromTemplate(packageDataObject, name+"View", params);
        set.add(viewObject.getPrimaryFile());
        DataObject presenterObject = presenterTemplateDataObject.createFromTemplate(packageDataObject, name+"Presenter", params);
        set.add(presenterObject.getPrimaryFile());
        DataObject fxmlObject = fxmlTemplateDataObject.createFromTemplate(packageDataObject, name.toLowerCase(), params);
        set.add(fxmlObject.getPrimaryFile());
        
        Boolean generateBundle = (Boolean) wizard.getProperty(GENERATE_BUNDLE);
        Boolean generateConfiguration = (Boolean) wizard.getProperty(GENERATE_CONFIG);
        Boolean generateCss = (Boolean) wizard.getProperty(GENERATE_CSS);

        if (generateBundle) {
            DataObject bundleObject = bundleTemplateDataObject.createFromTemplate(packageDataObject, name.toLowerCase(), params);
            set.add(bundleObject.getPrimaryFile());            
        }
        if (generateConfiguration) {
            DataObject configurationObject = configurationTemplateDataObject.createFromTemplate(packageDataObject, "configuration", params);
            set.add(configurationObject.getPrimaryFile());            
        }
        if (generateCss) {
            DataObject cssObject = cssTemplateDataObject.createFromTemplate(packageDataObject, name.toLowerCase(), params);
            set.add(cssObject.getPrimaryFile());            
        }
        
//        if (enabledController && dfController != null) {
//            assert controllerName.equals(targetNameController);
//            FileObject javaTemplate = FileUtil.getConfigFile("Templates/javafx/FXMLController.java"); // NOI18N
//            DataObject dJavaTemplate = DataObject.find(javaTemplate);
//            DataObject dobj2 = dJavaTemplate.createFromTemplate(dfController, controllerName);
//            set.add(dobj2.getPrimaryFile());
//        }
//
//        if (enabledCSS && dfCSS != null) {
//            FileObject cssTemplate = FileUtil.getConfigFile("Templates/javafx/FXML.css"); // NOI18N
//            DataObject dCSSTemplate = DataObject.find(cssTemplate);
//            DataObject dobj3 = dCSSTemplate.createFromTemplate(dfCSS, cssName);
//            set.add(dobj3.getPrimaryFile());
//        }

        return set;
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public WizardDescriptor.Panel<WizardDescriptor> current() {
        return panels[index];
    }

    @Override
    public boolean hasNext() {
        return index < panels.length - 1;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public void nextPanel() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
    }

    @Override
    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
    }

    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }

    static void setErrorMessage(String key, WizardDescriptor settings) {
        if (key == null) {
            settings.getNotificationLineSupport().clearMessages();
        } else {
            settings.getNotificationLineSupport().setErrorMessage(NbBundle.getMessage(AfterburnerFXTemplateWizardIterator.class, key));
        }
    }

    static void setInfoMessage(String key, WizardDescriptor settings) {
        if (key == null) {
            settings.getNotificationLineSupport().clearMessages();
        } else {
            settings.getNotificationLineSupport().setInformationMessage(NbBundle.getMessage(AfterburnerFXTemplateWizardIterator.class, key));
        }
    }
    
    
}
