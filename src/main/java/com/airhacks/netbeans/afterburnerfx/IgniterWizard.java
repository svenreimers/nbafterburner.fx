package com.airhacks.netbeans.afterburnerfx;

import org.netbeans.api.templates.TemplateRegistration;
import org.netbeans.api.maven.archetype.ArchetypeWizards;
import org.openide.WizardDescriptor;
import org.openide.util.NbBundle.Messages;
import static com.airhacks.netbeans.afterburnerfx.Bundle.*;

public class IgniterWizard {
    
    @TemplateRegistration(folder="Project/Maven2", position=100, displayName="#template.project.Igniter", iconBase="com/airhacks/netbeans/afterburnerfx/resources/maven_igniter_16.png", description="IgniterDescription.html")
    @Messages("template.project.Igniter=Afterburner.fx")
    public static WizardDescriptor.InstantiatingIterator<?> create() {
        return ArchetypeWizards.definedArchetype("com.airhacks", "igniter", "1.9.2", null, template_project_Igniter());
    }

    private IgniterWizard() {}

}
