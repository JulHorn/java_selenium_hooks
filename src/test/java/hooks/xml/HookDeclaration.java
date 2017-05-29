package hooks.xml;

import org.simpleframework.xml.Attribute;

/**
 * Represents a hook declaration of the Hooks.xml file.
 **/
public class HookDeclaration {

    /**
     * The full qualified class name of the hook.
     **/
    @Attribute(name = "HookPath")
    private String hookPath;

    /**
     * Returns the full qualified name of the hook.
     *
     * @return Returns the full qualified name of the hook.
     **/
    public String getHookQualifiedClassName() {
        return hookPath;
    }

    /**
     * Sets the hook qualified class name
     *
     * @param hookQualifiedClassName The qualified class name of the hook.
     **/
    public void setHookPath(String hookQualifiedClassName) {
        hookPath = hookQualifiedClassName;
    }
}
