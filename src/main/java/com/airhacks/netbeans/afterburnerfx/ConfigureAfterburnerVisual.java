/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.netbeans.afterburnerfx;

import java.awt.Component;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.util.ChangeSupport;
import org.openide.util.Exceptions;
import org.openide.util.HelpCtx;
import org.openide.util.NbBundle;

/**
 *
 * @author sven
 */
public class ConfigureAfterburnerVisual extends javax.swing.JPanel {

    private FileObject preselectedDirectory;
    private final Panel observer;

    /**
     * Creates new form NewJPanel
     */
    public ConfigureAfterburnerVisual(Panel observer, Project project) {
        this.observer = observer;
        initComponents();
        setName(NbBundle.getMessage(ConfigureAfterburnerVisual.class, "TXT_ViewName")); // NOI18N        
        viewNameTextField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                update(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update(e);
            }

            private void update(DocumentEvent e) {
                try {
                    targetPackageTextField.setText(preselectedDirectory.getPath() + "/" + e.getDocument().getText(0, e.getDocument().getLength()).toLowerCase());
                    fireChange();
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        });
    }
    

    private void fireChange() {
        this.observer.fireChangeEvent();
    }
    

    public String getViewName() {
        return viewNameTextField.getText();
    }

    public boolean isCssNeeded() {
        return generateCssCheckBox.isSelected();
    }

    public boolean isBundleNeeded() {
        return generateBundleCheckBox.isSelected();
    }

    public boolean isConfigurationNeeded() {
        return generateConfigurationFile.isSelected();
    }

    void initValues(FileObject template, FileObject preselectedDirectory) {
        this.preselectedDirectory = preselectedDirectory;
        targetPackageTextField.setText(preselectedDirectory.getPath());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewNameLabel = new javax.swing.JLabel();
        viewNameTextField = new javax.swing.JTextField();
        generateCssCheckBox = new javax.swing.JCheckBox();
        generateBundleCheckBox = new javax.swing.JCheckBox();
        targetPackageLabel = new javax.swing.JLabel();
        targetPackageTextField = new javax.swing.JTextField();
        generateConfigurationFile = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(viewNameLabel, org.openide.util.NbBundle.getMessage(ConfigureAfterburnerVisual.class, "ConfigureAfterburnerVisual.viewNameLabel.text")); // NOI18N

        viewNameTextField.setText(org.openide.util.NbBundle.getMessage(ConfigureAfterburnerVisual.class, "ConfigureAfterburnerVisual.viewNameTextField.text")); // NOI18N
        viewNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewNameTextFieldActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(generateCssCheckBox, org.openide.util.NbBundle.getMessage(ConfigureAfterburnerVisual.class, "ConfigureAfterburnerVisual.generateCssCheckBox.text")); // NOI18N
        generateCssCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateCssCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(generateBundleCheckBox, org.openide.util.NbBundle.getMessage(ConfigureAfterburnerVisual.class, "ConfigureAfterburnerVisual.generateBundleCheckBox.text")); // NOI18N
        generateBundleCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateBundleCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(targetPackageLabel, org.openide.util.NbBundle.getMessage(ConfigureAfterburnerVisual.class, "ConfigureAfterburnerVisual.targetPackageLabel.text")); // NOI18N

        targetPackageTextField.setEditable(false);
        targetPackageTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                targetPackageTextFieldActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(generateConfigurationFile, org.openide.util.NbBundle.getMessage(ConfigureAfterburnerVisual.class, "ConfigureAfterburnerVisual.generateConfigurationFile.text")); // NOI18N
        generateConfigurationFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateConfigurationFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generateCssCheckBox)
                    .addComponent(generateBundleCheckBox)
                    .addComponent(generateConfigurationFile))
                .addGap(0, 345, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(viewNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewNameTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(targetPackageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(targetPackageTextField)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewNameLabel)
                    .addComponent(viewNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(generateCssCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generateBundleCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(generateConfigurationFile)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(targetPackageLabel)
                    .addComponent(targetPackageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(139, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_viewNameTextFieldActionPerformed

    private void generateCssCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateCssCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generateCssCheckBoxActionPerformed

    private void generateBundleCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateBundleCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generateBundleCheckBoxActionPerformed

    private void targetPackageTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_targetPackageTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_targetPackageTextFieldActionPerformed

    private void generateConfigurationFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateConfigurationFileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_generateConfigurationFileActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox generateBundleCheckBox;
    private javax.swing.JCheckBox generateConfigurationFile;
    private javax.swing.JCheckBox generateCssCheckBox;
    private javax.swing.JLabel targetPackageLabel;
    private javax.swing.JTextField targetPackageTextField;
    private javax.swing.JLabel viewNameLabel;
    private javax.swing.JTextField viewNameTextField;
    // End of variables declaration//GEN-END:variables
    // Private innerclasses ----------------------------------------------------

    static class Panel implements WizardDescriptor.Panel<WizardDescriptor>, WizardDescriptor.FinishablePanel<WizardDescriptor> {

        private ConfigureAfterburnerVisual component;
        private final ChangeSupport changeSupport = new ChangeSupport(this);
        private WizardDescriptor settings;

        public Panel(Project project) {
            component = new ConfigureAfterburnerVisual(this, project);
        }
        
        @Override
        public Component getComponent() {
            return component;
        }

        @Override
        public HelpCtx getHelp() {
            return null;
        }

        @Override
        public void readSettings(WizardDescriptor settings) {
            this.settings = settings;
            // Try to preselect a folder
            FileObject preselectedFolder = Templates.getTargetFolder(settings);
            // Init values
            component.initValues(Templates.getTemplate(settings), preselectedFolder);

            // XXX hack, TemplateWizard in final setTemplateImpl() forces new wizard's title
            // this name is used in NewFileWizard to modify the title
            Object substitute = component.getClientProperty("NewFileWizard_Title"); // NOI18N
            if (substitute != null) {
                settings.putProperty("NewFileWizard_Title", substitute); // NOI18N
            }
        }

        @Override
        public void storeSettings(WizardDescriptor settings) {
            Object value = settings.getValue();
            if (WizardDescriptor.PREVIOUS_OPTION.equals(value)
                    || WizardDescriptor.CANCEL_OPTION.equals(value)
                    || WizardDescriptor.CLOSED_OPTION.equals(value)) {
                return;
            }
            settings.putProperty(AfterburnerFXTemplateWizardIterator.JAVA_DIR, Templates.getTargetFolder(settings));
            settings.putProperty(AfterburnerFXTemplateWizardIterator.NAME, component.getViewName());
            settings.putProperty(AfterburnerFXTemplateWizardIterator.GENERATE_BUNDLE, component.isBundleNeeded());
            settings.putProperty(AfterburnerFXTemplateWizardIterator.GENERATE_CONFIG, component.isConfigurationNeeded());
            settings.putProperty(AfterburnerFXTemplateWizardIterator.GENERATE_CSS, component.isCssNeeded());
            settings.putProperty("NewFileWizard_Title", null); // NOI18N
        }

        @Override
        public boolean isValid() {
            if (component.getViewName() == null || component.getViewName().isEmpty()) {
                AfterburnerFXTemplateWizardIterator.setInfoMessage("WARN_ConfigureAfterburnerViewPanel_Provide_View_Name", settings); // NOI18N
                return false;
            }
            
            // Clean up messages
            settings.getNotificationLineSupport().setErrorMessage(null);
            return true;
        }

        @Override
        public void addChangeListener(ChangeListener l) {
            changeSupport.addChangeListener(l);
        }

        @Override
        public void removeChangeListener(ChangeListener l) {
            changeSupport.removeChangeListener(l);
        }

        private void fireChangeEvent() {
            changeSupport.fireChange();
        }

        @Override
        public boolean isFinishPanel() {
            return true;
        }
    }

}