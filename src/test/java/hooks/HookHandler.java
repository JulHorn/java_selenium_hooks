package hooks;


import hooks.exception.HookException;
import hooks.xml.HookDeclaration;
import hooks.xml.HookDeclarations;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;

/**
 * This class handles the creation of the hook and offers the methods to execute them if the context scope is correct.
 **/
public class HookHandler {

    /**
     * Constructor.
     *
     * @param webDriver    The webDriver which can be used in the hooks.
     * @param hookFilePath The path to the file containing the hooks.
     **/
    public static synchronized void registerHooks(EventFiringWebDriver webDriver, String hookFilePath) {
        HookDeclarations hooks;

        hooks = HookHandler.loadHooks(hookFilePath);
        HookHandler.createHooks(hooks, webDriver);
    }

    /**
     * Transforms the qualified class names into the corresponding classes/objects.
     *
     * @param hooks The hooks, which should be registered to the webdriver.
     * @param webDriver The webdriver to which the hooks will be registered.
     **/
    private static void createHooks(HookDeclarations hooks, EventFiringWebDriver webDriver) {
        for (HookDeclaration hookDeclaration : hooks.getHooks()) {
            Object hook;

            try {
                hook = Class.forName(hookDeclaration.getHookQualifiedClassName()).newInstance();
            } catch (ClassNotFoundException e) {
                throw new HookException("Failed to find targetHookContext or hook.", e);
            } catch (InstantiationException | IllegalAccessException e) {
                throw new HookException("Failed to instantiate hook.", e);
            }

            webDriver.register((WebDriverEventListener) hook);
        }
    }

    /**
     * Loads the hooks from the Hooks.xml file.
     *
     * @param hookDeclarationFilePath to the file which contains the hooks.
     * @return The specified hooks in the Hooks.xml file.
     **/
    private static HookDeclarations loadHooks(String hookDeclarationFilePath) {
        File hookFile = new File(hookDeclarationFilePath);

        // Check that file is existing and load the file
        if (hookFile.exists() && hookFile.isFile()) {
            Serializer serializer = new Persister();
            HookDeclarations hooks;

            try {
                hooks = serializer.read(HookDeclarations.class, hookFile);
            } catch (Exception e) {
                throw new HookException("Hook declaration file seems to be corrupt in path: " + hookDeclarationFilePath, e);
            }

            return hooks;
        }

        throw new HookException("Failed to read the hook declaration file in the path " + hookDeclarationFilePath);
    }
}
